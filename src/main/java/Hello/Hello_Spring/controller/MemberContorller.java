package Hello.Hello_Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.service.MemberService;


@Controller
public class MemberContorller {
    private final MemberService memberService;

    @Autowired  // MemberController가 생성될 때, MemberService 객체를 자동으로 주입(연결)해준다.
    public MemberContorller(MemberService memberService){   
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  // @PostMapping("/members/new")로 요청이 들어오면 스프링이 자동으로 MemberForm 객체가 생성, MemberForm클래스 정의만 해놓으면 됨
    public String create(MemberForm memberForm){
        Member member=new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";   //회원가입이 끝나면 홈화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model){
            List<Member> members=memberService.findMembers();
            model.addAttribute("members", members);  //"members"라는 키로 회원 목록을 저장하고 뷰에서 사용할 수 있도록 전달
            return "members/memberList";
        }
}
    

 



