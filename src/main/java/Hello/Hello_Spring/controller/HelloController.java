package Hello.Hello_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


// 크게 @ResponseBody를 쓰느냐 안쓰냐에 따라 웹페이지를 찾는 건지 데이터를 건네는 건지 의미가 달라진다.
//@ResponseBody를 쓰면 값을 보낼 때 객체형태로 보낼수 있거나 문자열로 보낼 수 있다.
@Controller
public class HelloController {
    @GetMapping("/hello")  //localhost:8080/hello로 접속.
    public String hello(Model model) {
        String value="HelloController에서 넘긴 값을 출력합니다.";
        model.addAttribute("data", value);   // resources/templates/hello.html에서 키값으로 data를 사용하여 값을 출력한다.
        return "hello";   // resources/templates/hello.html를 가리킨다.
    }
// Model은 컨트롤러에서 데이터를 뷰(View, HTML)로 전달하는 역할을 하는 스프링 프레임워크의 객체. 컨트롤러에서 만든 데이터를 웹 화면(HTML)에서 사용할 수 있도록 넘겨주는 가방 같은 역할

    @GetMapping("/hello-mvc")   // http://localhost:8080/hello-mvc?name=erlia 로 접속하면 name값이 출력된다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";  // resources/templates/hello-temlpate.html를 가리킨다.
    }

   //@ResponseBody는 데이터만 보내겠다는 의미
    @GetMapping("/hello-string")  // http://localhost:8080/hello-string?name=erlia  
    @ResponseBody                 //*1.API 방식(제이슨 반환)으로 사용할 때 사용, @ResponseBody를 써서 소스보기를 보면 html이 보이지 않고 순수 데이터만 보인다.
    public String helloString(@RequestParam("name") String value){
        return "헬로 " + value;  //
    }

    //클래스의 객체를 넘겨주면 제이슨 형태로 출력하는 방식 
    @GetMapping("/hello-api")    // http://localhost:8080/hello-api?name=helloClass 로 접속
    @ResponseBody               //*2.API 방식(제이슨 반환)으로 사용할 때 사용, @ResponseBody를 써서 객체를 제이슨 형태로 변환하여 반환하는 방식
    public HelloClass helloApi(@RequestParam("name") String value){ 
        HelloClass helloClass =new HelloClass();
        helloClass.setName(value);
        return helloClass;   //객체를 반환
    
    }
    static class HelloClass{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
        
    }
    
}
