package com.spring5.mypro00.common.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring5.mypro00.domain.MyBoardAttachFileVO;
import com.spring5.mypro00.mapper.MyScheduledMapper;

@Component
public class ClearMyUploadFileRepo {

	private MyScheduledMapper myScheduledMapper ;
	
	public ClearMyUploadFileRepo(MyScheduledMapper myScheduledMapper) {
		
		this.myScheduledMapper = myScheduledMapper ;
	}
	
	//하루 전 폴더 문자열 생성 메서드
	private String getBeforeOneDayPathName() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
		
		Calendar calendar = Calendar.getInstance() ;
		calendar.add(Calendar.DATE, -1) ; //오늘 -1 날짜로 변경
		
		String beforeOneDayPathName = simpleDateFormat.format(calendar.getTime()) ;
		
		return beforeOneDayPathName ;
//		return beforeOneDayPathName.replace("/", File.separator) ;
	}
	
	//
	@Scheduled(cron = "0 35 14 * * *")
	public void clearNeedlessFiles() {
		
		String uploadFileRepoDir = "C:/myupload" ;
		System.out.println("오늘 날짜: " + new Date()) ;
		
		List<MyBoardAttachFileVO> doNotDeleteFileList 
			= myScheduledMapper.selectAttachFilesBeforeOneDay1() ;
		
//		List<String> doNotDeleteFileDirList 
//			= myScheduledMapper.selectDirs();
		
		if(doNotDeleteFileList == null) {
			System.out.println("=========첨부파일이 없습니다.=========");

			//하루 전 날짜경로가 저장된 파일 객체
			File beforeOneDay = Paths.get(uploadFileRepoDir + "/" + getBeforeOneDayPathName())
									 .toFile();
			
			//삭제해야하는 파일 목록 생성
			File[] needlessFileArray
				= beforeOneDay
					.listFiles();

			//파일 삭제
			if(needlessFileArray == null) {
				
				System.out.println("=========삭제할 파일이 없습니다.=========");
				return ;
			
			}else {
				System.out.println("=========다음의 파일들이 삭제됩니다.=========");
				
				for(File needlessFile : needlessFileArray) {
					System.out.println(needlessFile.getAbsolutePath());
					
					needlessFile.delete() ;
				}
			}
			
			
		}else {
			List<Path> doNotDeleteFilePathList 
			= doNotDeleteFileList // List<MyBoardAttachFileVO> 타입
				.stream() // -> Stream<MyBoardAttachFileVO> 로 변환
				.map(attachFileVO -> Paths.get(attachFileVO.getRepoPath() + "/" +
											   attachFileVO.getUploadPath() + "/" +
											   attachFileVO.getUuid() + "_" +
											   attachFileVO.getFileName())) // -> Stream<Path> 로 변환
				.collect(Collectors.toList()) ; // -> List<Path>로 변환
		
			doNotDeleteFileList // List<MyBoardAttachFileVO> 타입
				.stream() 		// -> Stream<MyBoardAttachFileVO> 로 변환
				.filter(attachFileVO -> attachFileVO.getFileType().equals("I")) // Stream에 이미지파일()
				.map(attachFileVO -> Paths.get(attachFileVO.getRepoPath() + "/" +
											   attachFileVO.getUploadPath() + "/s_" +
											   attachFileVO.getUuid() + "_" +
											   attachFileVO.getFileName())) // -> Stream<Path> 로 변환
				.forEach(thumbnailFilePath -> doNotDeleteFilePathList.add(thumbnailFilePath));
			//자동 실행 시, 콘솔에 지우면 안되는 파일 목록 표시
			System.out.println("=============================");
			doNotDeleteFilePathList
			.forEach(doNotDeleteFilePath -> System.out.println(doNotDeleteFilePath));
			System.out.println("=============================");
	
			//하루 전 날짜경로가 저장된 파일 객체
			File beforeOneDay = Paths.get(uploadFileRepoDir + "/" + getBeforeOneDayPathName())
									 .toFile();
			
			//삭제해야하는 파일 목록 생성
			File[] needlessFileArray
				= beforeOneDay
					.listFiles(
							eachFile 
								-> doNotDeleteFilePathList
									.contains(eachFile.toPath()) == false);
			
			//파일 삭제
			if(needlessFileArray == null) {
				
				System.out.println("=========삭제할 파일이 없습니다.=========");
				return ;
			
			}else {
				System.out.println("=========다음의 파일들이 삭제됩니다.=========");
				
				for(File needlessFile : needlessFileArray) {
					System.out.println(needlessFile.getAbsolutePath());
					
					needlessFile.delete() ;
				}
			}
		}
		

	}
}
