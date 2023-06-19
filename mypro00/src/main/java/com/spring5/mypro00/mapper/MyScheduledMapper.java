package com.spring5.mypro00.mapper;

import java.util.List;

import com.spring5.mypro00.domain.MyBoardAttachFileVO;

public interface MyScheduledMapper {
	
	//하루 전 이전의 첨부파일 목록 조회
	public List<MyBoardAttachFileVO> selectAttachFilesBeforeOneDay1() ;
	
	//하루 전의 첨부파일 목록 조회
	public List<MyBoardAttachFileVO> selectAttachFilesBeforeOneDay2() ;
	
	//과제
	public List<String> selectDirs() ;

}
