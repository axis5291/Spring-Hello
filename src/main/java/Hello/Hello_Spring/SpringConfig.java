package Hello.Hello_Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Hello.Hello_Spring.repository.MemberRepository;
import Hello.Hello_Spring.repository.MemoryMemberRepository;
import Hello.Hello_Spring.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();   //이렇게 빈으로 등록하면 레포지토리 변경이 쉽다.다른 코드를 변경하지 않아도 된다.
    }
}
