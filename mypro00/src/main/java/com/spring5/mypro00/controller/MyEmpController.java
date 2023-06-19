package com.spring5.mypro00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring5.mypro00.service.MyEmpService;

@Controller
@RequestMapping("/myemp")
public class MyEmpController {
	
	private MyEmpService myEmpService ;

	public MyEmpController(MyEmpService myEmpService) {
		this.myEmpService = myEmpService;
	}

	@GetMapping("/list")
	public void showEmpList(Model model) {
		model.addAttribute("empList", myEmpService.getEmpList()) ;
		 
	}
	
	@GetMapping("/yearhiredCnt")
	public void showHiredEmpCnt(Model model) {
		model.addAttribute("empCntList", myEmpService.getHiredEmpCnt()) ;
		 
	}	

}
