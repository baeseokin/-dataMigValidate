package koreanre.batch;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration	
public class PostSqlConfig {

	@Primary
	@Bean(name = "targerDS")
    @ConfigurationProperties(prefix = "spring.datasource.target")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

	@Primary
	@Bean(name ="targetSqlSessionFactory")
	public SqlSessionFactory targetSqlSessionFactory(@Qualifier("targerDS") DataSource dataSource) throws Exception{
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		Resource[] mapperRes = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/TargetMapper.xml");
		Resource configLocationRes = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
		sessionFactory.setMapperLocations(mapperRes);
		sessionFactory.setConfigLocation(configLocationRes);
		
		return sessionFactory.getObject();
	}
	
	@Bean(name ="targetSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate targetSqlSessionTemplate(SqlSessionFactory targetSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(targetSqlSessionFactory);
	}
}
