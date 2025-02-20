package Hello.Hello_Spring.service;

import java.util.List;
import java.util.Optional;

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemberRepository;

public class MemberService {
    final private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){  //외부에서 MemberServeice객체를 생성할 때 MemberRepository를 넣어준다.
        this.memberRepository=memberRepository;
    }
// 생성자 매개변수(MemberRepository memberRepository)와 같이 인터페이스로 지정한 이유
// MemberService를 생성할 때 매개변수로 MemoryyMemberRepository 대신 JpaMemberRepository로 변경하면 MemberService의 코드를 변경하지 않아도 된다.
//이렇게 하면 손쉽게 레포지토리를 타입별로 바꿀수 있다.  MemberServiceTest에서 @beforeEach()부분 볼것..

    public Long join(Member member){//회원가입 메서드
        validateDuplicateMember(member);  //중복회원 검증
        memberRepository.save(member);    //map에 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {  // //중복회원 검증
        Optional<Member> result=memberRepository.findByName(member.getName());
        result.ifPresent(m->{  
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        // findByName() 메서드는 Optional<Member>를 반환하는데, 만약 해당 이름을 가진 회원이 존재하지 않으면 Optional.empty()가 반환됨.
        // 만약 이미 존재하면 ifPresent()가 실행되어 예외가 발생함.
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
