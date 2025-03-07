package Hello.Hello_Spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// 비즈니스 관점에서는 Member와 같은 클래스를 도메인 모델이라고 부르고, JPA 관점에서는 그 클래스가 데이터베이스와 매핑되는 객체이므로 엔티티 클래스라고 부릅니다.
// 결국 둘은 같은 객체를 의미할 수 있지만, 그 관점에 따라 다르게 불리는 것입니다.

@Entity  //JPA를 쓸려면 이렇게 엔티티로 관리한다고 알려줘야함
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //IDENTITY: 데이터베이스에 위임하는 방식, 데이터베이스가 자동으로 값을 생성하도록 설정, id값을 자동으로 생성및 증가
    private Long id;   //시스템에서 사용하는 값
    private String name;
  

     public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}
