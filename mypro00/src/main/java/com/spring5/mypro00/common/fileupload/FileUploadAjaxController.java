package com.spring5.mypro00.common.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring5.mypro00.common.fileupload.domain.AttachFileDTO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class FileUploadAjaxController {

	private String uploadFileRepoDir = "C:/myupload" ;
	
	//form을 통한 다중 파일 업로드  //uploadFiles
	
	//1. 파일 업로드 요청 JSP 페이지 호출
	@GetMapping(value = {"/fileUploadAjax"})
	public String callFileUploadFormPage() {
		System.out.println("=======[Ajax-요청-업로드] 페이지 표시==");
		return "sample/fileUploadAjax" ;
	}
	
	
	private boolean checkIsImageFile(File uploadFile) {
		
		String contentType = null;
		try {
			contentType = Files.probeContentType(uploadFile.toPath());
			System.out.println("업로드 파일 타입: " + contentType);
			return contentType.startsWith("image") ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false ;
		
		
	}
	
	
	//날짜 형식 경로 문자열 생성 메소드
	private String getDatefmtPathName() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
		
		String dateFmtPathName = simpleDateFormat.format(new Date()) ;
		
		return dateFmtPathName ;
//		return dateFmtPathName.replace("/", File.separator ) ;
	}
	
	
	
	//2. 파일 업로드 처리
	@PostMapping(value = "/fileUploadAjaxAction", 
			     produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> fileUploadActionPost(MultipartFile[] uploadFiles) {
	
		List<AttachFileDTO> attachFileList = new ArrayList<AttachFileDTO>();
		
		String dateFmtPathName = getDatefmtPathName() ;
		
		File fileUploadPath = new File(uploadFileRepoDir, dateFmtPathName) ;
//			 C:/myupload\2023/06/09

		if(!fileUploadPath.exists()) {
			fileUploadPath.mkdirs() ;
		}
		
		String uploadFileName = null ;
		String uuidStr = null ;
		
		for(MultipartFile uploadFile : uploadFiles) {
			System.out.println("==================================");
			System.out.println("Upload File Name: " + uploadFile.getOriginalFilename());
			System.out.println("Upload File Size: " + uploadFile.getSize());
			
			AttachFileDTO attachFileDTO = new AttachFileDTO() ;
			
			
			
//			File saveUploadFile = new File(uploadFileRepoDir, uploadFile.getOriginalFilename());
			uploadFileName = uploadFile.getOriginalFilename() ;
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1) ;
			
			attachFileDTO.setFileName(uploadFileName) ;
			attachFileDTO.setUploadPath(dateFmtPathName);
			attachFileDTO.setRepoPath(uploadFileRepoDir) ;
			
			//UUID를 이용한 고유한 파일이름 적용
			UUID uuid = UUID.randomUUID();
			uuidStr = uuid.toString() ;
			uploadFileName = uuidStr + "_" + uploadFileName ;
			
			attachFileDTO.setUuid(uuidStr) ;
			
//			System.out.println("uuid 추가된 파일이름: " + uploadFileName);
			
			//날짜 형식(yyyy/MM/dd)의 폴더구조 생성
			// File(String Parent, String Child)
			// 부모경로(레포지토리 경로) 문자열에 자식경로(날짜형식 경로) 문자열이 더해진 File 객체 생성
			// 날짜형식 경로를 생성하기 위하여 앞에서 생성한 getDatefmtPathName() 메서드 호출
			
			
			
//			File saveUploadFile = new File(uploadFileRepoDir, uploadFileName);
			File saveUploadFile = new File(fileUploadPath, uploadFileName);
			
			
			try {
				uploadFile.transferTo(saveUploadFile);
				
				if (checkIsImageFile(saveUploadFile)) {
					
					FileOutputStream fileOutputStream =
							new FileOutputStream(new File(fileUploadPath, "s_" + uploadFileName)) ;
					
					Thumbnailator.createThumbnail(uploadFile.getInputStream(), 
												  fileOutputStream, 
												  20, 20) ;
					fileOutputStream.close() ;
					
					attachFileDTO.setFileType("I") ;
					
				} else {
					attachFileDTO.setFileType("F") ;	
				}
				
				
				
			} catch (IllegalStateException | IOException e) {
				System.out.println("error:" + e.getMessage());
			} 
			
			attachFileList.add(attachFileDTO) ;
			
		}//for-end
		
		return new ResponseEntity<List<AttachFileDTO>> (attachFileList, HttpStatus.OK) ;
	}
	
	@GetMapping("/displayThumbnail")
	@ResponseBody
	public ResponseEntity<byte[]> sendThumbnailFile(String fileName) {
		
		System.out.println("fileName: " + fileName);
		
		File file = new File(fileName) ;
		
		ResponseEntity<byte[]> result = null ;
		
		HttpHeaders httpHeaders = new HttpHeaders() ;
		
		try {
			httpHeaders.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), httpHeaders, HttpStatus.OK) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return result ;
	}
	
	
	//서버에 저장된 업로드 파일 삭제
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String fileType) {
		
		System.out.println("fileName: " + fileName);
		//fileName: C:/myupload/2023/06/13/
		//ed9dfd77-7e21-4e2f-98b9-752ed9893bac_chap12%20%EB%A9%80%ED%8B%B0%EC%93%B0%EB%A0%88%EB%93%9C.ppt
		System.out.println("fileType: " + fileType);
		
		//일반파일과 썸네일 파일이름(경로명 포함)이 전달됨

		try {
			fileName = URLDecoder.decode(fileName, "utf-8") ;
			System.out.println("Decoded_fileName: " + fileName);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File delFile = new File(fileName) ; 
		
		boolean delResult = delFile.delete() ;   //일반파일과 썸네일 파일 삭제	
		
		
		if(fileType.equals("I")) {
			//원본이미지파일을 삭제
			delFile = new File(fileName.replace("s_", ""));

			boolean delResultForImage = delFile.delete() ;	//이미지파일 삭제
			
			delResult = delResult && delResultForImage ;
		}
		
		return delResult ? new ResponseEntity<String>("S", HttpStatus.OK) 
				         : new ResponseEntity<String>("F", HttpStatus.OK) ;
	}
	
	
	
	
}
