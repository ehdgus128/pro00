package com.spring5.mypro00.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, 
					   HttpServletResponse response, 
					   AccessDeniedException accessDeniedException) 
							   throws IOException, ServletException {
//		[redirect 방법1]
		response.sendRedirect("/mypro00/accessForbiddenError?myAccessDeniedMsg=" + accessDeniedException.getMessage());

//		[redirect 방법2]
//		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;
//		
//		request.setAttribute("accessDeniedException", accessDeniedException);		
//		
//		redirectStrategy.sendRedirect(request, 
//									  response, 
//									  "/accessForbiddenError"); //컨텍스트 이름 포함하면 안됨
		
	}
}
