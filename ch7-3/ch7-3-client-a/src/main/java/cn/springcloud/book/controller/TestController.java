package cn.springcloud.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import java.util.Enumeration;

@RestController
public class TestController {

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/add")
	public Integer add(Integer a, Integer b){
		return a + b;
	}

	@GetMapping("client/add")
	public Integer preAdd(Integer a, Integer b){
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()){
			String s = headerNames.nextElement();
			System.out.println(s);
		}
		return a + b;
	}
	
	@GetMapping("/a/add")
	public Integer aadd(Integer a, Integer b){
		return a + b;
	}
	
	@GetMapping("/sub")
	public Integer sub(Integer a, Integer b){
		return a - b;
	}
	
	@GetMapping("/mul")
	public String mul(Integer a, Integer b){
		return "client-a-" + a * b;
	}
	
	@GetMapping("/div")
	public Integer div(Integer a, Integer b){
		return a / b;
	}
}
