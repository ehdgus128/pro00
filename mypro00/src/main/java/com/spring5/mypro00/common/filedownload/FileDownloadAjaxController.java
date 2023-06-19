package com.spring5.mypro00.common.filedownload;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileDownloadAjaxController {
	
	@GetMapping(value="/fileDownloadAjax", produces = "application/octet-stream")
	@ResponseBody
	public ResponseEntity<Resource> fileDownloadActionAjax(
										String fileName,
										@RequestHeader("User-Agent") String userAgent) {
		
		System.out.println("전달된 파일이름: " + fileName); 
		
		//C:\myupload\2023\06\12\2e3c5211-d6ad-46cd-a6b8-6b27c032ea17_참고_계층적데이터검색.pdf
		Resource resource = new FileSystemResource(fileName) ;
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND) ;
			
		}
		
		String downloadName = resource.getFilename() ;
		//2e3c5211-d6ad-46cd-a6b8-6b27c032ea17_참고_계층적데이터검색.pdf
		
		//UUID가 제거된 파일이름
		downloadName = downloadName.substring(downloadName.indexOf("_") + 1) ;
		
		HttpHeaders httpHeaders = new HttpHeaders() ;
		
		try {
			if (userAgent.contains("Trident") || userAgent.contains("MSIE") ||
				userAgent.contains("Edge") || userAgent.contains("Edg")) {
				
//				downloadName = URLEncoder.encode(downloadName, "utf-8") ;  //IE
				downloadName = new String(downloadName.getBytes("utf-8"), "ISO-8859-1") ;
				System.out.println("MS 브라우저: " + downloadName);  //콘솔에서는 한글이 모두 깨짐
				
			} else {
//				downloadName = URLEncoder.encode(downloadName, "utf-8") ;
				downloadName = new String(downloadName.getBytes("utf-8"), "ISO-8859-1") ;
				System.out.println("MS 브라우저 이 외: " + downloadName);  //콘솔에서는 한글이 모두 깨짐
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httpHeaders.add("Content-Disposition", "attachment; filename=" + downloadName) ;
		
		return new ResponseEntity<Resource>(resource, httpHeaders, HttpStatus.OK) ;
		
	}

}
