package koreanre.batch;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableBatchProcessing
@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class DataMigValidateApplication {
	private static Logger logger = LoggerFactory.getLogger(DataMigValidateApplication.class);
	
	public static void main(String[] args) {
		int exit = SpringApplication.exit(SpringApplication.run(DataMigValidateApplication.class, args));
		logger.info("exitCode={}", exit);
		
	}
	
	@Value("${spring.batch.job.names:NONE}")
	private String jobNames;
	
	
//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource.target")
//	@Primary
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
}
