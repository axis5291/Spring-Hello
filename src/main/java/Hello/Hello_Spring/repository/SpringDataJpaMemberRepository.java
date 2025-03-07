package Hello.Hello_Spring.repository;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Hello.Hello_Spring.domain.Member;
//SpringDataJpa의 특징
//JpaRepository를 상속받아 SpringDataJpaMemberRepository 인터페이스를 만들면 SpringDataJpa가 자동으로 Spring이 인터페이스의 구현체(클래스)를 만들어 객체로 등록해 준다
//인터페이스이지만 클래스를 따로 만들 필요가 없다. 스프링이 만들어 준다. 

@Repository
@Primary   //같은 타입의 빈이 여러개 있을 때 @Primary가 붙은 빈을 우선적으로 주입한다.(현제 MemoryMemberRepository와 Jpa레포지토리가 같은 타입이므로)
public interface  SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
//JpaRepository의 첫 번째 제네릭 인자는 엔티티 클래스 (@Entity) 여야 한다. 두 번째 제네릭 인자는 엔티티의 ID 타입과 동일해야 한다.
    @Override
    Optional<Member> findByName(String name);
}


//MemberRepository 인터페이스에 있는 메서드들을 보고 구현해준다.
//다른방식 참조
//#1
// public interface JpaMemberRepository extends JpaRepository<Member, Long> {
//     Optional<Member> findByName(String name);
// }

