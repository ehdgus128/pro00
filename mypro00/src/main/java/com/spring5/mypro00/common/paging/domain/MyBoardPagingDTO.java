package com.spring5.mypro00.common.paging.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyBoardPagingDTO {
	
	private int pageNum ;
	private int rowAmountPerPage ;
	private String scope ;   //검색범위(scope - T:btitle, C:bcontent, W:bwriter)
	private String keyword ; //검색어
	
	public String[] getScopeArray() {
		return scope == null ? new String[] {} : scope.split("") ;
	}
	
	//Setter를 이용하여 필드의 값들이 설정됩니다.
	public MyBoardPagingDTO() {
		this.pageNum = 1 ;
		this.rowAmountPerPage = 10 ;
	}
	
	
	//생성자: 목록 화면에서 사용자가 표시할 행수를 선택하고 페이징 번호 클릭 시,
	//페이지번호와 행수를 각각 사용자가 선택한 페이징번호와 표시행수로 전달	
	public MyBoardPagingDTO(Integer pageNum, Integer rowAmountPerPage) {
			
		if (pageNum == null || pageNum <= 0) {
			this.pageNum = 1 ;
			
		} else {
			this.pageNum = pageNum;	
			
		}
		
		if (rowAmountPerPage == null ) {
			this.rowAmountPerPage = 10 ;
			
		} else {
			this.rowAmountPerPage = rowAmountPerPage;	
			
		}
		
	}
	

}


/*
 		<select class="form-control" id="selectScope" name="scope">
			<option value="" ${pagingCreator.myBoardPaging.scope == null ? 'selected' : '' }>선택</option>
			<option value="T" ${pagingCreator.myBoardPaging.scope eq 'T' ? 'selected' : '' }>제목</option>
			<option value="C" ${pagingCreator.myBoardPaging.scope eq 'C' ? 'selected' : '' }>내용</option>
			<option value="W" ${pagingCreator.myBoardPaging.scope eq 'W' ? 'selected' : '' }>작성자</option>
			<option value="TC" ${pagingCreator.myBoardPaging.scope eq 'TC' ? 'selected' : '' }>제목+내용</option>
			<option value="TW" ${pagingCreator.myBoardPaging.scope eq 'TW' ? 'selected' : '' }>제목+작성자</option>
			<option value="CW" ${pagingCreator.myBoardPaging.scope eq 'CW' ? 'selected' : '' }>내용+작성자</option>
			<option value="TCW" ${pagingCreator.myBoardPaging.scope eq 'TCW' ? 'selected' : '' }>제목+내용+작성자</option>
		</select>
 
 
*/
