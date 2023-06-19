package com.spring5.mypro00.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrMsgController {

	@GetMapping("/accessForbiddenError")
	public String callAccessForbiddenPage(Authentication authentication,
										  String myAccessDeniedMsg,
										  Model model) {
		
		System.out.println("전달된 authentication: " + authentication);
		
		model.addAttribute("myAccessDeniedMsg", myAccessDeniedMsg) ;
		model.addAttribute("msg", "접근이 금지됨") ;
		
		return "common/err_msg/myAccessForbiddenMsg" ;
	}
}
