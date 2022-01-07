package koreanre.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import koreanre.batch.dao.DataMigValidateTargetDAO;
import koreanre.batch.dto.BizTb;

@Service
public class DataMigValdateService {
	
	@Autowired
	@Qualifier("dataMigValidateTargetDAO")
	private DataMigValidateTargetDAO targetrDAO;
	
	public BizTb selectBizTb(BizTb bizTb){
		BizTb result = null;
		try {
			result = targetrDAO.selectBizTb(bizTb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
