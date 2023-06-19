package com.spring5.mypro00.common.paging.domain;

import java.util.List;

import com.spring5.mypro00.domain.MyReplyVO;

import lombok.Getter;
import lombok.ToString;

//용도: 화면에서 표시되는 페이징 숫자들을 만들 때 필요한 값들을 전달
@Getter
@ToString
public class MyReplyPagingCreatorDTO {
	
	private MyReplyPagingDTO myReplyPaging ; //pageNum, rowAmountPerPage  교재(BOOK)
	private int startPagingNum ;
	private int endPagingNum ;
	private boolean prev ;
	private boolean next ;
	private List<Long> rowTotal ;      //교재(BOOK)
	private int pagingNumCnt ;
	private int lastPageNum ;
	
	private List<MyReplyVO> replyList ;  //교재(BOOK)
	
	public MyReplyPagingCreatorDTO(List<Long> rowTotal, 
								   MyReplyPagingDTO myReplyPaging, 
								   List<MyReplyVO> replyList) {
				
		this.myReplyPaging = myReplyPaging ;
		this.rowTotal = rowTotal ;
		this.replyList = replyList ;
		
		this.pagingNumCnt = 3 ;
		
		//계산된 끝 페이징 번호:
		this.endPagingNum = (int) Math.ceil((double) myReplyPaging.getPageNum() / this.pagingNumCnt ) * this.pagingNumCnt ;
		
		this.startPagingNum = this.endPagingNum - (this.pagingNumCnt - 1) ;	
		
		//총 페이지 수 = 맨 마지막 페이지번호
		this.lastPageNum = (int) Math.ceil( (this.rowTotal.get(0) * 1.0) /this.myReplyPaging.getRowAmountPerPage()) ;
		
		//맨 마지막 페이지번호를 endPagingNum에 대입
		if(this.lastPageNum < this.endPagingNum) {
			this.endPagingNum = this.lastPageNum ;
		}
		
		//이전 버튼 표시(true) 여부
		this.prev = this.startPagingNum > 1 ;
		
		//다음 버튼 표시(true) 여부
		this.next = this.endPagingNum < this.lastPageNum ;
		
		System.out.println("전달된 페이징 기본데이터-myReplyPagingDTO: " + this.myReplyPaging.toString());
		System.out.println("시작 페이징번호: " + this.startPagingNum);
		System.out.println("끝 페이징번호: " + this.endPagingNum);
		System.out.println("이전버튼 표시 여부: " + this.prev);
		System.out.println("다음버튼 표시 여부: " + this.next);
		System.out.println("마지막 페이지 번호: " + this.lastPageNum);
				
	}

}
