package Hello.Hello_Spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemoryMemberRepository;

public class MemberServiceTest {
    MemoryMemberRepository memberRepository;   //여기 필드에서 객체를 생성하면 모든 테스트에서 같은 MemoryMemberRepository 인스턴스를 공유하게 되어 데이터가 중복될 수 있다. (싱글톤처럼 동작할 수 있음)"
    MemberService memberService;

    @BeforeEach  //각 메서드의 테스트가 실행되기 전에 실행되는 메서드
    public void bdforeEach(){
        this.memberRepository=new MemoryMemberRepository();  //각 메서드마다 별도의 레포지토리 객체를 만드는 것
        //@BeforeEach를 통해 매 테스트마다 새로운 MemoryMemberRepository 인스턴스를 생성하므로, 각 테스트는 독립적으로 실행되고 데이터가 섞이지 않는다
        this.memberService=new MemberService(memberRepository);
    }

    @AfterEach  //각 메서드의 테스트가 끝날때마다 실행되는 메서드
    public void ArrayList(){
        memberRepository.clearStore();  //map에 저장된 데이터를 모두 삭제하는 메서드
    }

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
    @Test
    void testFindMembers() throws InterruptedException {
        System.out.println("테스트 실행됨!!!");
      
    }
    @Test
    void testFindOne() {

    }
   
}
