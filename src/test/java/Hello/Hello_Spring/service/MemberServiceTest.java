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
    void testFindMembers() {

    }

    @Test
    void testFindOne() {

    }

   
}
