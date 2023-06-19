package sample.chap04.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;
import sample.chap04.domain.SampleDTO;
import sample.chap04.domain.SampleDTOList;
import sample.chap04.domain.TodoDTO;

@Controller
@Log4j
@RequestMapping(value = "/sample/*")
public class SampleController {
	
//	@RequestMapping(value = "/sample/1", method = RequestMethod.GET)
//	public void basic1() {
//		log.info("basic1============");
//	}
//	
//	@RequestMapping(value = "/sample/2")
//	public void basic2() {
//		log.info("basic2============");
//	}

	//위의 메서드의 URL을 아래처럼 수정 
	
//	@RequestMapping(value = "/1", method = RequestMethod.GET)
//	public void basic1() {
//		log.info("basic1============");
//	}
//	
//	@RequestMapping(value = "/2")
//	public void basic2() {
//		log.info("basic2============");
//	}
//	
//	@RequestMapping(value = "", method= {RequestMethod.GET, RequestMethod.POST})
//	public void basic3() {
//		log.info("basic3============");	
//	}
////////////////////////////////////////////////////////////
	
	@GetMapping(value = "/basicOnlyGet")
	public void basicGet() {
		log.info("basicGet============");
	}
	
	//웹브라우저: http://localhost:8080/mypro00/sample/basicOnlyGet
	
	
	@PostMapping("/basicOnlyPost")
	public void basicPost() {
		log.info("basicPost============");
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/basicOnlyPost
	  // ==> 오류
	
////////////////////////////////////////////////////////////////
	@GetMapping(value = "/ex01")
	public void myEx01(SampleDTO dto) {
		System.out.println("dto.name: " + dto.getName());
		System.out.println("dto.age: " + dto.getAge());
		log.info("===============: " + dto);
	
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex01?name=홍길동&age=24 로 요청

	@GetMapping(value = "/ex02")
	public void myEx02(String name, int age) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
	
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02?name=홍길동&age=24 로 요청
	
//	@GetMapping(value = "/ex022")
//	public void myEx022(@RequestParam("name") String name1, 
//						@RequestParam("age")  Integer age1) {
	@GetMapping(value = "/ex022")
	public String myEx022(@RequestParam("name") String name1, 
						  @RequestParam("age")  Integer age1) {
		System.out.println("name: " + name1);
		System.out.println("age: " + age1);
		return "sample/ex04" ;
	
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex022?name=홍길동&age=24 로 요청

	@GetMapping("/ex02List")  //값 전달이 않됨
	public void myEx02List( ArrayList<String> myIds) {
		log.info("=====myIds1: " + myIds);
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	
	@GetMapping("/ex022List")  //값 전달이 됨
	public void myEx022List(@RequestParam("myIds") ArrayList<String> myIds) {
		log.info("ids로 전달된 값들: " + myIds);
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	
	@GetMapping("/ex02Array")  //값 전달이 됨
	public void myEx02Array( String[] myIds) {
		log.info("=====myIds.length: " + myIds.length);
		log.info("=====myIds: " + Arrays.toString(myIds));
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02Array?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	@GetMapping("/ex022Array")  //값 전달이 됨
	public void myEx022Array(@RequestParam("myIds") String[] myIds) {
		log.info("=====myIds.length: " + myIds.length);
		log.info("=====myIds: " + Arrays.toString(myIds));
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex022Array?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	@GetMapping("/ex02Bean")
	public void ex02Bean(SampleDTOList list) {
		log.info("list: " + list);
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02Bean?list[0].name=홍길동&list[1].name=슈퍼맨&list[2].age=24 요청
 	  //웹브라우저: http://localhost:8080/mypro00/sample/ex02Bean?list%5B0%5D.name=홍길동&list%5B1%5D.name=슈퍼맨&list%5B2%5D.age=24 요청
	  //[ : %5B, ] : %5D
	
	@GetMapping(value = "/ex03")
	public void ex03(TodoDTO todo) {
		log.info("todo: " + todo);
	}
	
	//웹브라우저: http://localhost:8080/mypro00/sample/ex03?title=홍길동&dueDate=2023-05-28 요청
	
	//메서드의 반환타입이 void 및 String 일 경우의 JSP 호출 거동에 대하여
	//SampleControllerReturnType 클래스를 이용해서 실습한 것 확인 요망
	
	//메서드가 처리한 값을 JSP에 자동으로 스프링 프레임워크를 이용해서 넘기는 방법 실습
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, String page) {
		
		log.info("dto: " + dto) ;
		log.info("page: " + page) ;
		
		return "/sample/ex04" ;
	
	} 
	//웹브라우저: http://localhost:8080/mypro00/sample/ex04?name=홍길동&age=24&page=1 요청
	//ex04.jsp에 표시된 내용을 확인하면, SampleDTO에 저장된 값들은 JSP로 전달되었지만,
	//기본타입, Wrapper, String 타입의 값은 JSP로 전달되지 못합니다.
	//기본타입, Wrapper, String 타입의 값은 JSP로 전달하려면 ModelAtttribute 어노테이션을 이용합니다.
	
	@GetMapping("/ex05")
//	public String ex05(SampleDTO dto,  @ModelAttribute("page1")  String page) {
	public String ex05(SampleDTO dto,  @ModelAttribute("page")  String page1) {
		
		log.info("dto: " + dto) ;
		//log.info("page: " + page) ;
		log.info("page: " + page1) ;
		
		return "/sample/ex04" ;
	}
	//웹브라우저: http://localhost:8080/mypro00/sample/ex05?name=홍길동&age=24&page=1 요청
	
	@GetMapping("/ex055")
	public String ex055(SampleDTO dto,  String page, Model model ) {
		
		log.info("dto: " + dto) ;
		log.info("page: " + page) ;
		log.info("model: " + model) ;
		
		model.addAttribute("page1", page) ;
		
		
		return "/sample/ex04" ;
	}
	//웹브라우저: http://localhost:8080/mypro00/sample/ex055?name=홍길동&age=24&page=1 요청
	
	@GetMapping("/ex055List")  //값 전달이 됨
	public String ex055List(@RequestParam("myIds") ArrayList<String> myIds, SampleDTO dto, Model model) {
		log.info("myIds: " + myIds);
		log.info("dto: " + dto);
		log.info("model: " + model);
		
		model.addAttribute("myIds", myIds) ;
		model.addAttribute("dto", dto) ;
		log.info("model: " + model);
		
		return "/sample/ex04" ;	
	}
	//웹브라우저: http://localhost:8080/mypro00/sample/ex055List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨&name=이순신&age=24 요청
	
	
	//myIds 값은 화면까지 값이 하나만 전달됨
	@GetMapping("/ex0555List")  //값 전달이 됨
	public String ex0555List(@ModelAttribute("myIds") ArrayList<String> myIds, 
			                 @ModelAttribute("name") String name, 
			                 @ModelAttribute("age") int age) {
		log.info("myIds: " + myIds);
				
		return "/sample/ex04" ;	
	}
	//웹브라우저: http://localhost:8080/mypro00/sample/ex055List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨&name=이순신&age=24 요청
	//myIds 값은 화면까지 값이 하나만 전달됨
	@GetMapping("/ex0555Array")  //값 전달이 됨
	public String ex0555Array(@ModelAttribute("myIds") String[] myIds, 
			                  @ModelAttribute("name") String name, 
			                  @ModelAttribute("age") int age) {
		log.info("myIds: " + Arrays.toString(myIds));
				
		return "/sample/ex04" ;	
	}
	
	//웹브라우저: http://localhost:8080/mypro00/sample/ex0555Array?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨&name=이순신&age=24 요청
	
	
	
	//컨트롤러 메서드의 반환타입이 String 이고, 반환문자열이 redirect:로 시작된 경우 실습
	@GetMapping("/ex06")
	public String ex06(String name, int age, int page) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("page: " + page);
		
		try {
			name = URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//[주의]아래에서 redirect: 다음에 컨텍스트이름을 포함하면 않됨
		return "redirect:/ex04.jsp?name=" + name + "&age=" + age + "&page=" + page;  
	} // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex06?name=이순신&age=24&page=2 요청
	  // ==> 웹브라우저가 http://localhost:8080/mypro00/ex04.jsp로 요청하도록 URL이 전달됨
	
	//컨트롤러 메서드의 반환타입이 String 이고, 반환문자열이 redirect:로 시작된 경우 실습
	@GetMapping("/ex066")
	public String ex066(String name, int age, int page, RedirectAttributes redirectAttr) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("page: " + page);
		
		redirectAttr.addAttribute("name", name) ;
		redirectAttr.addAttribute("age", age) ;
		redirectAttr.addAttribute("page", page) ;

		return "redirect:/ex04.jsp";  
	} // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex066?name=이순신&age=24&page=2 요청
	  // ==> 웹브라우저가 http://localhost:8080/mypro00/ex04.jsp로 요청하도록 URL이 전달됨

	//컨트롤러 메서드의 반환타입이 String 이고, 반환문자열이 redirect:로 시작된 경우 실습
	@GetMapping("/ex0666")
	public String ex0666(String name, 
			             int age, int page, RedirectAttributes redirectAttr,
			             HttpServletRequest request) {
		
		System.out.println("URL: " + request.getRequestURI());
		
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("page: " + page);
		
		redirectAttr.addAttribute("name", name) ;
		redirectAttr.addAttribute("age", age) ;
		redirectAttr.addAttribute("page", page) ;

		//return "redirect:/sample/ex055";
		return "redirect:/";  
	} // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex0666?name=이순신&age=24&page=2 요청
	  // ==> 웹브라우저가 http://localhost:8080/mypro00/ex04.jsp로 요청하도록 URL이 전달됨
	
	
	//메서드 반환타입이 DTO, VO 인 경우(void/String 이 아닌 경우)
	
	@GetMapping("/ex07")
	public SampleDTO ex07(SampleDTO dto) {
		System.out.println("name: " + dto.getName());
		System.out.println("age: " + dto.getAge());
		return dto ;
	}
	
	// 웹브라우저에서 http://localhost:8080/mypro00/sample/ex07?name=이순신&age=24
	// URL을 기반으로 JSP 파일을 호출합니다.
	
	//@ResponseBody: REST API 어노테이션, JSP 파일 호출이 없음
	@GetMapping("/ex08")
	@ResponseBody
	public SampleDTO ex08(SampleDTO dto) {
//	public @ResponseBody SampleDTO ex08(SampleDTO dto) {
	
		System.out.println("name: " + dto.getName());
		System.out.println("age: " + dto.getAge());
		
		return dto ;
	}
	//웹브라우저에서 http://localhost:8080/mypro00/sample/ex08?name=이순신&age=24
	
	
	//반환타입이 ResponseEntity<E> 인 경우, JSP 호출을 않함
	
	@GetMapping("/ex09")
	public ResponseEntity<String> ex09(SampleDTO dto) {
		log.info("/ex09===========================") ;
		
		HttpHeaders httpHeaders = new HttpHeaders() ;
//		httpHeaders.add("Content-Type", "text/plain;charset=utf-8") ;
		httpHeaders.add("Content-Type", "application/json;charset=utf-8") ;
		
		                   /* {"name": "홍길동", "age": 24} */
		String myJsonStr = "{\"name\": \"" + dto.getName() + "\", \"age\": " + dto.getAge() + "}" ;
		
//		return new ResponseEntity<String>(myJsonStr, HttpStatus.NOT_FOUND) ;
		return new ResponseEntity<String>(myJsonStr, httpHeaders ,HttpStatus.OK) ;
	}
	//웹브라우저에서 http://localhost:8080/mypro00/sample/ex09?name=이순신&age=24
	
	//commons-fileupload 라이브러리를 이용한 파일 업로드 처리
	//1.Upload JSP 페이지 호출 메서드 작성
	@GetMapping("/exUpload")
	public void exMyUpload() {
		
	}
	
	//    /WEB-INF/views/sample/exUpload.jsp
	
	private static final String uploadFolder = "C:/myupload" ;
	
	//2.파일업로드 처리 메서드 작성
	@PostMapping("/exUploadPost")
//	public void exUploadPost(MultipartFile myFile, SampleDTO dto) {
//	public void exUploadPost(ArrayList<MultipartFile> myFiles, SampleDTO dto) {
	public void exUploadPost(MultipartFile[] myFiles, SampleDTO dto) {
		log.info("name: " + dto.getName()) ;
		log.info("age: " + dto.getAge()) ;
		
		//String uploadFolder = "C:/myupload" ;

//      //ArrayList<MultipartFile> 타입 매개변수 사용 시
//		myFiles.forEach(myFile -> {
//			log.info("파일업로드 시작====") ;
//			log.info("--업로드 파일이름: " + myFile.getOriginalFilename());
//			log.info("--업로드 파일크기: " + myFile.getSize());
//			
//		});
		
		System.out.println("myFiles 길이: " + myFiles.length);
		
		log.info("파일업로드 시작====") ;
		
		//MultipartFile[] 타입 사용 시
		for(MultipartFile myFile : myFiles) {
			
			
			log.info("--업로드 파일이름: " + myFile.getOriginalFilename() + " : " +  myFile.getOriginalFilename().length());
			log.info("--업로드 파일크기: " + myFile.getSize());
			System.out.println("myFile: " + myFile.toString());
			
			if ( myFile.getSize() > 0 ) {
				
				File saveFile = new File(uploadFolder, myFile.getOriginalFilename()) ;
				
				try {
					myFile.transferTo(saveFile);  //서버에 저장.
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			
			
		}
		
		//업로드 테스트
		//업로드 페이지 표시: http://localhost:8080/mypro00/sample/exUpload 요청
		//  파일 선택 --> 버튼 클릭
		
	}
		
}
