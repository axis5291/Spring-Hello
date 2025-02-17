package Hello.Hello_Spring.repository;

import java.util.List;
import java.util.Optional;

import Hello.Hello_Spring.domain.Member;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);   //Null값이 반환된다면 optional객체로 감씨서 반환, 예외처리 불필요
    Optional<Member> findByName(String name);
    List<Member> findAll();
    
}
