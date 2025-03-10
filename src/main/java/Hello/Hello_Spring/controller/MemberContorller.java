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
        return "members/createMemberForm";  //resources/templates/members/createMemberForm.html로 이동
    }

    @PostMapping("/members/new")  // @PostMapping("/members/new")로 요청이 들어오면 스프링이 자동으로 MemberForm 객체가 생성, MemberForm클래스 정의만 해놓으면 됨
    public String create(MemberForm memberForm){  //***아래 주석 참조
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
    


// *** 스프링에서 MemberForm을 찾는 과정
// 스프링은 컨트롤러 메서드의 파라미터 타입(MemberForm)을 보고 해당 클래스를 찾습니다.
// MemberForm 클래스가 존재하면, 자동으로 객체를 생성하고 요청 데이터를 바인딩합니다.
// 이를 "데이터 바인딩"이라고 합니다.

// 📌 동작 과정
// 1. 사용자가 HTML 폼에 데이터를 입력하고 제출하면, POST 요청으로 서버에 전송됩니다.
// 2. 스프링은 @PostMapping("/members/new")에 매핑된 메서드를 호출하고, MemberForm 객체를 생성하여 요청 데이터를 바인딩합니다.
// 3. 생성된 MemberForm 객체가 create() 메서드에 전달되어 회원 가입이 처리됩니다.

 



