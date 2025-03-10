package Hello.Hello_Spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemberRepository;
// MemberService가 하는 역할
// 📌 회원가입 (join) ➡ 중복 회원을 검증한 후, memberRepository.save(member);를 통해 저장
// 📌 전체 회원 조회 (findMembers)  ➡ 저장된 모든 회원 목록을 반환
// 📌 특정 회원 조회 (findOne)  ➡ 주어진 memberId로 특정 회원을 찾아 반환
// 📌 중복 회원 검증 (validateDuplicateMember) ➡ findByName()을 이용해 중복된 회원이 있으면 예외 발생
@Service
public class MemberService {
    final private MemberRepository memberRepository; 
// final: 해당 변수가 한 번만 초기화될 수 있도록 제한하는 역할 즉, final로 선언된 변수는 값을 한 번 설정한 후 변경할 수 없다
// memberRepository가 생성된 후 변경되지 않도록 보장하려는 의도로 사용. 
    @Autowired
    public MemberService(MemberRepository memberRepository){  
   //***SpringConfig에서 memberRepository()에서 Bean으로 등록한 memberRepository를 매개변수로 받음
        this.memberRepository=memberRepository;
    }
// ***생성자에서 MemberRepository 인터페이스를 매개변수로 받는 이유
// MemberService는 MemberRepository 인터페이스를 사용하므로,
// 실제 구현체(JpaMemberRepository, MemoryMemberRepository 등)를 변경하더라도 MemberService의 코드 자체는 수정할 필요가 없다. (OCP - 개방-폐쇄 원칙 적용)
//
// 현재 SpringConfig에서 MemberRepository 구현체를 주입하도록 설정했기 때문에, MemoryMemberRepository에서 JpaMemberRepository로 쉽게 교체할 수 있다.
//
// 즉, SpringConfig에서 `return new JpaMemberRepository(em);`로 변경하면 MemberService의 코드는 수정하지 않고도 JPA 기반의 레포지토리를 사용할 수 있다.

    public Long join(Member member){     //회원가입 메서드->회원중복 검증후 레포지토리에 저장하는 메서드
        validateDuplicateMember(member); //중복회원 검증, *이미 생성된 빈(객체)이므로 바로 호출가능(this.validateDuplicateMember(member)에서 this가 생략)
        memberRepository.save(member);   //레포지토리에 저장
        return member.getId();           //ID를 반환하면 회원 가입 이후 해당 회원을 쉽게 추적하고, 후속 작업을 할 수 있도록 설계된 것! 😊
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {  //맴버의 이름으로 중복회원 검증, 같은 이름이 있으면 예외 발생
        Optional<Member> result=memberRepository.findByName(member.getName());  //MemberControlle create()에서 설정한 멤버의 이름을 꺼내서 중복검증
        result.ifPresent(m->{    //ifPresent()는 Optional 내부에 값이 있을 경우 실행
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        // findByName() 메서드는 Optional<Member>를 반환하는데, 만약 해당 이름을 가진 회원이 존재하지 않으면 Optional.empty()가 반환됨.
        // 만약 이미 존재하면 ifPresent()가 실행되어 예외가 발생함.
    }

    
  //아래 join()와 findMembers() 메서드는 실행에 걸리는 시간을 측정하고자 작성 -> AOP로 대체

    // public Long join(Member member) { 
    //     long start = System.currentTimeMillis();
    //     try {
    //         validateDuplicateMember(member); //중복 회원 검증 memberRepository.save(member);
    //         return member.getId();
    //     } finally {
    //         long finish = System.currentTimeMillis();
    //         long timeMs = finish - start;
    //         System.out.println("join()의 시간측정: " + timeMs + "ms");
    //     } 
    // } 

    // public List<Member> findMembers() {  
    //      long start = System.currentTimeMillis();
    //      try {
    //          return memberRepository.findAll();
    //      } finally {
    //          long finish = System.currentTimeMillis();
    //          long timeMs = finish - start;
    //          System.out.println("시간측정 findMembers()의 시간측정: " + timeMs + "ms");
    //     } 
    // }
 

}

