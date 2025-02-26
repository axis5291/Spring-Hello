package Hello.Hello_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")   //이렇게 하면 localhost:8080/으로 들어가면 home.html이 보여진다.
    public String home(){
        return "home";  //이렇게 작성하면 resources/templates/home.html을 가리키고 그 내용을 보여준다. 
    }

}
