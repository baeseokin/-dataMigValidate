package koreanre.batch.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import koreanre.batch.dto.BizTb;
import koreanre.batch.dto.ValidateResult;
import koreanre.batch.support.JobSupport;
import koreanre.batch.util.Incrementer;

@Configuration
@ConditionalOnProperty(name = "job.name", havingValue = SimpleJob.JOB_NAME)
public class SimpleJob extends JobSupport {

	private static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

	public static final String JOB_NAME = "SimpleJob";
	public static final String SCHEMA_NAME = "PMAINDB";
	public static final String TABLE_NAME = "BIZ_TB";
	Date nowDate = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm"); 
	String execDate = dateFormat.format(nowDate);


	@Bean(name = JOB_NAME)
	public Job run(Step simpleStep) throws Exception {

		return jobBuilderFactory.get(JOB_NAME)
				.start(simpleStep)
				.incrementer(new Incrementer())
				.build();
	}

	@Bean(name = JOB_NAME + "step")
	@JobScope
	public Step simpleStep(MyBatisCursorItemReader<BizTb> simpleItemReader, 
							ItemProcessor<BizTb, ValidateResult> simpleItemProcess,
							MyBatisBatchItemWriter<ValidateResult> simpleItemWriter) throws Exception {
		
		return stepBuilderFactory.get(JOB_NAME + "_step")
								.<BizTb, ValidateResult>chunk(chunkSize)
								.reader(simpleItemReader)
								.processor(simpleItemProcess)
								.writer(simpleItemWriter)
								.build();
	}

	@Bean
	@StepScope
	public MyBatisCursorItemReader<BizTb> simpleItemReader()
			throws Exception {
		MyBatisCursorItemReader<BizTb> myBatisCursorItemReader = new MyBatisCursorItemReader<BizTb>();
		myBatisCursorItemReader.setSqlSessionFactory(sourceSqlSessionFactory);
		myBatisCursorItemReader.setQueryId("koreanre.batch.dao.DataMigValidateSourceDAO.selectBizTb");
		return myBatisCursorItemReader;
	}

	@Bean
	public ItemProcessor<BizTb, ValidateResult> simpleItemProcess() {
		return item -> {
			//logger.info("ItemProcessor  -----  source item :{}", item);
			BizTb retItem = dataMigValdateService.selectBizTb(item);
			//logger.info("ItemProcessor  -----  target item :{}", retItem);
			return validate(item, retItem);
		};
	}

	private ValidateResult validate(BizTb item, BizTb retItem) {
		boolean isEqual = item.equals(retItem);
		
		if(retItem == null || (retItem != null && !isEqual)) {
			ValidateResult result = new ValidateResult();
			
			result.setValDate(execDate);
			result.setSchemaNm(SCHEMA_NAME);
			result.setTableNm(TABLE_NAME);
			result.setStatus("FAILED");
			result.setSrcValue(item.toString());
			result.setTgtValue((retItem!=null?retItem.toString():""));
			logger.error("ItemProcessor  -----  result is false :{}", result);
			
			return result;
		}else {
			return null;
		}
	}
	
	

	@Bean
	public MyBatisBatchItemWriter<ValidateResult> simpleItemWriter() throws Exception {
		MyBatisBatchItemWriter<ValidateResult> myBatchItemWriter = new MyBatisBatchItemWriter<ValidateResult>();
		myBatchItemWriter.setSqlSessionFactory(targetSqlSessionFactory);
		myBatchItemWriter.setStatementId("koreanre.batch.dao.DataMigValidateTargetDAO.insertValidateResult");
		return myBatchItemWriter;
	}

}
