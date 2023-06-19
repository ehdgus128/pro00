package com.spring5.mypro00.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.mapper.MyBoardMapper;
import com.spring5.mypro00.mapper.MyReplyMapper;

@Service
//@Transactional //이 클래스의 모든 메서드에 스프링 트랜잭션이 적용됩니다.
public class MyReplyServiceImpl implements MyReplyService {
	
	private MyReplyMapper myReplyMapper ;
	private MyBoardMapper myBoardMapper ;
	
	//자동주입 방법1: 단일 생성자 자동 주입 방식으로 주입 시
	public MyReplyServiceImpl(MyReplyMapper myReplyMapper, MyBoardMapper myBoardMapper) {
		this.myReplyMapper = myReplyMapper ;
		this.myBoardMapper = myBoardMapper ;
	}
	
	
	//자동주입 방법2: @Autowired를 이용한 자동 주입(setter 자동 주입과 동일)
	// @Autowired
	// private MyReplyMapper myReplyMapper;
	// public MyReplyServiceImpl() {
	// }
	
	//자동주입 방법3: setter를 이용한 자동 주입//lombok 어노테이션 이용
	// @Setter(onMethod_ = @Autowired)
	// private MyReplyMapper myReplyMapper;
	// public MyReplyServiceImpl() {
	// }
	
	// private MyReplyMapper myReplyMapper;
	// public MyReplyServiceImpl() {
	// }
	// @Autowired
	// public void setMapper(MyReplyMapper myReplyMapper;) {
	// this.myReplyMapper = myReplyMapper;
	// }
	

	@Override
	public MyReplyPagingCreatorDTO getReplyList(MyReplyPagingDTO myReplyPaging) {
		
		List<Long> rowTotal = myReplyMapper.selectReplyTotCnt(myReplyPaging.getBno()) ;
		
		int pageNum = myReplyPaging.getPageNum() ;
		
		MyReplyPagingCreatorDTO myReplyPagingCreator = null ;
		
		if (rowTotal.get(1) == 0) {
			myReplyPaging.setPageNum(1);
			System.out.println("댓글:서비스- 댓글이 없는 경우 pageNum=1");
			myReplyPagingCreator = new MyReplyPagingCreatorDTO(
										rowTotal, 
										myReplyPaging, 
										myReplyMapper.selectMyReplyList(myReplyPaging));
			
		} else {
			
			if (pageNum == -1) {
				pageNum = (int) Math.ceil(rowTotal.get(0)/(myReplyPaging.getRowAmountPerPage()*1.0)) ;
				
				myReplyPaging.setPageNum(pageNum) ;
			}
			
			myReplyPagingCreator = new MyReplyPagingCreatorDTO(
										rowTotal, 
										myReplyPaging, 
										myReplyMapper.selectMyReplyList(myReplyPaging)) ;
		}
		
		
		return myReplyPagingCreator;
	}
	//@Transactional
	@Override
	public Long registerReplyForBoard(MyReplyVO myReply) {
		
		System.out.println("입력된 행수: " + myReplyMapper.insertMyReplyForBoard(myReply));
		
		myBoardMapper.updateBReplyCnt(myReply.getBno(), 1);
		
		return myReply.getRno() ; 
	}

	@Transactional
	@Override
	public Long registerReplyForReply(MyReplyVO myReply) {

		System.out.println("입력된 행수: " + myReplyMapper.insertMyReplyForReply(myReply));
		
		myBoardMapper.updateBReplyCnt(myReply.getBno(), 1);
		
		return myReply.getRno() ; 
	}

	@Override
	public MyReplyVO getReply(long bno, long rno) {
		// TODO Auto-generated method stub
		return myReplyMapper.selectMyReply(bno, rno);
	}

	@Override
	public int modifyReply(MyReplyVO myReply) {
		
		return myReplyMapper.updateMyReply(myReply);
	}

	@Transactional
	@Override
	public int removeMyReply(long bno, long rno) {
		
		int delCnt = myReplyMapper.deleteMyReply(bno, rno);
		
		myBoardMapper.updateBReplyCnt(bno, -1);
		
		return delCnt;
	}

	@Transactional
	@Override
	public int modifyBdelFlagReply(long bno, long rno) {
		
		int delCnt = myReplyMapper.updateBdelFlagMyReply(bno, rno);
		
		myBoardMapper.updateBReplyCnt(bno, -1);
		
		return delCnt;
	}
	
//	@Override
//	public int removeAllReply(long bno) {
//
//		return myReplyMapper.deleteAllReply(bno);
//	}
	
//	@Override
//	public int modifyrdelFlagAllReply(long bno) {
//
//		return myReplyMapper.updaterdelFlagAllReply(bno);
//	}

}
