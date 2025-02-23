package Hello.Hello_Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Hello.Hello_Spring.service.MemberService;

@Controller
public class MemberContorller {
    private final MemberService memberService;

    @Autowired
    public MemberContorller(MemberService memberService){
        this.memberService=memberService;
    }

}
