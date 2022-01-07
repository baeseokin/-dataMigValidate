package koreanre.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import koreanre.batch.dto.BizTb;


@Repository("dataMigValidateTargetDAO")
public class DataMigValidateTargetDAO {
	
	@Autowired
	@Qualifier("targetSqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	private final static String NAMESPACE = "koreanre.batch.dao.DataMigValidateTargetDAO.";
	
	public BizTb selectBizTb(BizTb bizTb) throws Exception{
		return sqlSession.selectOne(NAMESPACE +"selectBizTb", bizTb);
	};
}