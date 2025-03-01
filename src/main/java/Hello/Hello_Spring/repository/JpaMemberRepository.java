package Hello.Hello_Spring.repository;

import java.util.List;
import java.util.Optional;

import Hello.Hello_Spring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional //jpa를 사용하려면 항상 트랜잭션을 걸어줘야한다.
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;   

    public JpaMemberRepository(EntityManager em) {//JPA를 사용하려면  EntityManager를 주입받아야 한다.
        this.em = em;
    }


    @Override
    public Member save(Member member) {
       em.persist(member);  //persist는 jpa가 insert쿼리를 만들어서 db에 넣어준다.
        return member;
    } 

    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class, id);  //find는 jpa가 select쿼리를 만들어서 db에서 데이터를 가져온다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result= em.createQuery("select m from Member m where m.name=:name", Member.class)
        .setParameter("name", name)
        .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
       List<Member> result= em.createQuery("select m from Member m", Member.class).getResultList();  
       //jpql이라는 객체지향 쿼리언어를 사용한다. "select m from Member m", Member.class는 Member객체를 대상으로 쿼리를 날린다.
        return result;
        
    }

   

   
   
    
}
