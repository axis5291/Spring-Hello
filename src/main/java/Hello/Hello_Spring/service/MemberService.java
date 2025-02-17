package Hello.Hello_Spring.service;

import java.util.List;
import java.util.Optional;

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemberRepository;
import Hello.Hello_Spring.repository.MemoryMemberRepository;

public class MemberService {
    final private MemberRepository memberRepository =new MemoryMemberRepository();

    //회원가입 메서드
    public Long join(Member member){
       
       validateDuplicateMember(member);  //중복회원 검증
        memberRepository.save(member);
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
