package Hello.Hello_Spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemberRepository;
import jakarta.transaction.Transactional;

@SpringBootTest  //스프링컨테이가 실행되면서 테스트를 진행하겠다는 어노테이션
@Transactional   //테스트케이스에 이 어노테이션이 있으면 테스트 시작 전에 트랜잭션을 시작하고, 테스트가 끝나면 롤백을 한다. 테스트케이스에만 이 어노테이션을 붙이면 테스트가 끝나면 롤백을 한다.
class MemberServiceIntegrationTest {
    @Autowired
    MemberRepository memberRepository;   //MemberRepository 인터페이스지만 구현체가 있다면 가능

    @Autowired
    MemberService memberService;

    @Test
    void testJoin() {
        //given, when, then 식으로 개발을 하라
         //given
        Member member=new Member();
        member.setName("마종호");

        //when
        Long saveId=memberService.join(member);  //MemberService에서 레포지토리에 넘김
        //then
        Member findMember=memberService.findOne(saveId).get();  //Optional에서 값을 꺼낼때는 get()메서드를 사용
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        //생성한 객체의 이름과 찾아온 객체의 이름이 같은지 확인
        if(member.getName().equals(findMember.getName())){
            System.out.println("같은 회원으로 판명되었습니다.");
        }
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("마종호");

        Member member2=new Member();
        member2.setName("마종호");

        //when
        memberService.join(member1);
        IllegalStateException e=org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class,()->memberService.join(member2));
        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        System.out.println("중복_회원_예외 메서드 실행결과:예외가 발생했습니다.");
    }
}
