package com.spring5.mypro00.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;

public interface MyReplyMapper {
	
	//기본 CRUD 처리 메서드 정의

	//특정 게시물에 대한 댓글 목록 조회: 페이징 고려(매개변수, bno-> MyReplyPagingDTO 으로 변경)
	public List<MyReplyVO> selectMyReplyList(MyReplyPagingDTO myReplyPaging);
	
	//특정 게시물에 대한 댓글 총 개수
//	public long selectReplyTotCnt(long bno) ;
	public List<Long> selectReplyTotCnt(long bno) ;

	//특정 게시물에 대한 댓글 등록
	public long insertMyReplyForBoard(MyReplyVO myReply) ;
	
	//댓글에 대한 답글 등록
	public long insertMyReplyForReply(MyReplyVO myReply) ;
	
	//특정 게시물에 대한 특정 댓글/답글 조회
	public MyReplyVO selectMyReply(@Param("bno") long bno, @Param("rno") long rno) ;
	
	//특정 게시물에 대한 특정 댓글/답글 수정
	public int updateMyReply(MyReplyVO myReply) ;
	
	//특정 게시물에 대한 특정 댓글/답글 삭제
	public int deleteMyReply(@Param("bno") long bno, @Param("rno") long rno) ;
	
	//특정 게시물에 대한 특정 댓글/답글 삭제 요청
	public int updateBdelFlagMyReply(@Param("bno") long bno, @Param("rno") long rno) ;
	
	
	//특정 게시물에 대한 모든 댓글 삭제 //myreply.bno 컬럼의 F.K에 ON DELETE CASCADE를 사용하는 경우, 필요 없음
	public int deleteAllReply(long bno);
	
	//특정 게시물에 대한 모든 댓글 삭제 요청
	public int updateBdelFlagAllReply(long bno);
	
}
