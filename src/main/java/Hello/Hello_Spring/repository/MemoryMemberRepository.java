package Hello.Hello_Spring.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Hello.Hello_Spring.domain.Member;

//이것이 제대로 동작되는지 확인하기 위헤 테스트케이스를 작성한다.  ->JUnit이라는 프레임워크로 테스트를 실행
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store=new HashMap<>();   //id<Long>로 조회하여 member를 담기 위해, key값으로 id(long), value로 member가 반환되게
    private static long sequence=0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);  //map애 member객체 저장
       return  member;
    }

    @Override
    public Optional<Member> findById(Long id) {
       return Optional.ofNullable(store.get(id));  //map형태인 store에서 키값으로 id(long)을 넣으면 값으로 member객체가 반횐된다.
       //Optional을 사용하면 null도 감싸서 반환이 가능하다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member->member.getName().equals(name)).findAny();
        
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  //store(map)에 들어있는 value(값)은 member객체들이다..이것을 List형테로 반환한다..
    }

    public void clearStore() {
        store.clear();
    }
    

}
