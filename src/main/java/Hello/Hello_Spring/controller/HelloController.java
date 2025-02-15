package Hello.Hello_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        String value="HelloController에서 넘긴 값을 출력합니다.";
        model.addAttribute("data", value);
        // resources/templates/hello.html에서 키값으로 data를 사용하여 값을 출력한다.
        return "hello";   // resources/templates/hello.html를 가리킨다.
    }

    @GetMapping("/hello-mvc")   // http://localhost:8080/hello-mvc?name=erlia 로 접속하면 name값이 출력된다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";  
    }

    @GetMapping("/hello-string")  // http://localhost:8080/hello-string?name=erlia 로 접속하면 hello erlia가 출력된다.  소스보기를 하면 바로위의 것과 차이가 난다. html이 보이지 않는다. 
    @ResponseBody                 //*1.API 방식(제이슨 반환)으로 사용할 때 사용, @ResponseBody를 보고 httpMessageConvert가  문자를 반환하는 방식
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;  //
    }

    @GetMapping("/hello-api")    // http://localhost:8080/hello-api?name=erlia 로 접속
    @ResponseBody               //*2.API 방식(제이슨 반환)으로 사용할 때 사용, @ResponseBody를 보고 httpMessageConvert가 객체를 제이슨 형태로 변환하여 반환하는 방식
    public Hello helloApi(@RequestParam("name") String name){ 
        Hello hello =new Hello();
        hello.setName(name);
        return hello;

    }
    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
        
    }
    
}
