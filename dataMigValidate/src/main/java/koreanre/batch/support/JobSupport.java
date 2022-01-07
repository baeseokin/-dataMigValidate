package koreanre.batch.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import koreanre.batch.service.DataMigValdateService;

@Component
public class JobSupport {
	@Autowired 
	public JobBuilderFactory jobBuilderFactory;
	@Autowired 
	public StepBuilderFactory stepBuilderFactory;

	@Autowired 
	@Qualifier("sourceSqlSessionFactory")
	public SqlSessionFactory sourceSqlSessionFactory;
	
	@Autowired
	@Qualifier("targetSqlSessionFactory")
	public SqlSessionFactory targetSqlSessionFactory;
	
	@Autowired
	public DataMigValdateService dataMigValdateService;
	
	public int chunkSize;
	public int poolSize;
	
	@Value("${chunkSize:10}")
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	@Value("${poolSize:10}")
	public void setPoolSizee(int poolSize) {
		this.poolSize = poolSize;
	}		
	
	public TaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(poolSize);
		executor.setThreadNamePrefix("multi-thread-");
		executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
		executor.initialize();
				
		return executor;
	}	


}
