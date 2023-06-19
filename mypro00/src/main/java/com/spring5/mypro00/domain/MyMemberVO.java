package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyMemberVO {

	private String userId ;
	private String userPw ;
	private String userName ;
	private Timestamp mregDate ;
	private Timestamp mmodDate ;
	private String mdropFlg ;  //'1'탈퇴 / '0' 유지
	private boolean enabled ;  // '0' = 비활성 상태(false) / '1' = 활성 상태(true)
	
	private List<MyAuthorityVO> authorityList ;
}
