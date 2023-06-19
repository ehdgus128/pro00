package com.spring5.mypro00.mapperhr;

import java.util.HashMap;
import java.util.List;

public interface MyEmpMapper {
	
	public List<HashMap<String, String>> selectEmpList() ;
	
	public List<HashMap<String, String>> selectHiredEmpCnt() ;
	
	


}
