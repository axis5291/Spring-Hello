package Hello.Hello_Spring.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Hello.Hello_Spring.domain.Member;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();
   
    @AfterEach
    public void afterEach(){
        repository.clearStore();
        System.out.println("저장소를 비웠습니다.");   
    }

    @Test
    public void save(){
        Member member=new Member();
        member.setName("Axis");
        repository.save(member);

        Member result=repository.findById(member.getId()).get();  //get()은 Optional에서 제공하는 메서드
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("coco");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("erlia");
        repository.save(member2);

        Member result=repository.findByName("coco").get();
        assertThat(result).isEqualTo(member1);
    }
  

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("coco");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("erlia");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
        
    }

  

}
