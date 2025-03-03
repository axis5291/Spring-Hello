package Hello.Hello_Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Hello.Hello_Spring.repository.JpaMemberRepository;
import Hello.Hello_Spring.repository.MemberRepository;
import Hello.Hello_Spring.service.MemberService;
import jakarta.persistence.EntityManager;


// Spring이 자동으로 JdbcTemplateMemberRepository를 인식하도록 Spring Configuration에 등록해야 함
// 보통 @Configuration 클래스를 만들어 등록

@Configuration  //spring에서 설정 클래스임을 나타내는 어노테이션, Spring 컨테이너가 설정을 읽고 @Bean을 사용하여 객체를 직접 생성하고 관리 가능
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em){  //JpaRepository를 사용하기 위해 EntityManager를 주입받아야 한다.
        this.em=em;
    }   


    //아래의 것은 JpaMemberRepository를 사용하지 않을 때 나머지 것에 대한 코드
    // private DataSource datasource;  //jdbc를 사용할 때 사용

    // @Autowired
    // public SpringConfig(DataSource datasource){
    //     this.datasource=datasource;
    // }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean  
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);  
       // return new JdbcTemplateMemberRepository(datasource);    //SpringConfig에 Bean 등록을 해야 Spring에서 JdbcTemplateMemberRepository를 사용할 수 있음
       // return new JdbcMemberRepository(datasource);   //이렇게 빈으로 등록하면 레포지토리 변경이 쉽다.다른 코드를 변경하지 않아도 된다.
       //return new MemoryMemberRepository();
    }
}
