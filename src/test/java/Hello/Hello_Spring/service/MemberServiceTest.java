package Hello.Hello_Spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import Hello.Hello_Spring.domain.Member;

public class MemberServiceTest {
    MemberService memberService=new MemberService();
    @Test
    void testJoin() {
        //given, when, then 식으로 개발을 하라
         //given
        Member member=new Member();
        member.setName("마종호");

        //when
        Long saveId=memberService.join(member);
        //then
        Member findMember=memberService.findOne(saveId).get();  //Optional에서 값을 꺼낼때는 get()메서드를 사용
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void testFindMembers() {

    }

    @Test
    void testFindOne() {

    }

   
}
