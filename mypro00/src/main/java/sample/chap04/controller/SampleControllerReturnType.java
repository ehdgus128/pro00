package sample.chap04.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;
import sample.chap04.domain.SampleDTO;
import sample.chap04.domain.SampleDTOList;
import sample.chap04.domain.TodoDTO;

@Controller
@Log4j
@RequestMapping(value = "/sample2/*")
public class SampleControllerReturnType {
	
	//Controller 메서드는 사용자 요청을 처리한 후, 반드시 JSP 파일을 호출하도록
	//메서드의 반환타입에 명시합니다.
	
	
	//메서드의 반환 타입이 void로 지정된 경우, 
	@GetMapping(value = "/basicOnlyGet")
	public void basicGet() {
		log.info("basicGet============");
	}
	
	//웹브라우저: http://localhost:8080/mypro00/sample2/basicOnlyGet 요청
	//웹브라우저의 다음의 오류메시지가 표시됨
	//메시지 파일 [/WEB-INF/views/sample2/basicOnlyGet.jsp]을(를) 찾을 수 없습니다.
	//==> 메서드의 반환타입이 void 인 경우, 웹 브라우저 요청의 CONTEXT 이름 뒤에서부터 끝까지 내용의
	//    앞에는 접두어로 /WEB-INF/views/를 붙이고, 뒤에는 접미어로 .jsp를 붙인 후
	//    응답 HTML 내용을 생성할 JSP를 호출합니다.
	//    servlet-context.xml 파일에 접두어 접미어가 설정되어 있습니다.
	
	//메서드의 반환타입이 String 인 경우,
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, Integer page) {
		
		log.info("dto: " + dto) ;
		log.info("page: " + page) ;
		
		return "/sample/ex04" ;
	//반환타입을 String 으로 지정하여 원하는 JSP 파일을 호출할 수 있습니다.
	//servlet-context.xml에 설정된 접두어, 접미어를 고려해서 부분을 지정해야 합니다.
	}
	
	//웹브라우저: http://localhost:8080/mypro00/sample2/ex04?name=홍길동&age=24&page=1 요청

}
