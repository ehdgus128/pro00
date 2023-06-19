package com.spring5.mypro00.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.mypro00.mapperhr.MyEmpMapper;


@Service
public class MyEmpService {
	
	private MyEmpMapper myEmpMapper ;

	public MyEmpService(MyEmpMapper myEmpMapper) {
		this.myEmpMapper = myEmpMapper;
	}

	//직원 목록
	@Transactional
	public List<HashMap<String, String>> getEmpList() {
		
		myEmpMapper.selectHiredEmpCnt() ;
		
		return myEmpMapper.selectEmpList();
	}
	
	@Transactional
	public List<HashMap<String, String>> getHiredEmpCnt() {
		
		return myEmpMapper.selectHiredEmpCnt();
	}
	
	

}
