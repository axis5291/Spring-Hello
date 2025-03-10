package Hello.Hello_Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Hello.Hello_Spring.repository.MemberRepository;
import Hello.Hello_Spring.service.MemberService;

// SpringDataJpaMemberRepository를 사용하면 자동으로 빈 등록되므로 현재 SpringConfig 클래스가 필요하지 않습니다.
// 다른 레포지토리(예: JdbcTemplateMemberRepository나 JpaMemberRepository)를 사용하고자 할 경우, 
//SpringConfig에서 수동으로 빈을 등록해야 할 때 @Bean을 사용하여 빈을 등록하는 방식으로 SpringConfig가 필요합니다.

@Configuration  //spring에서 설정 클래스임을 나타내는 어노테이션, Spring 컨테이너가 설정을 읽고 @Bean을 사용하여 객체를 직접 생성하고 관리 가능
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository); 
    }

// 현재 SpringDataJpaMemberRepository를 사용하고 있기 때문에 JpaMemberRepository를 사용하지 않습니다.

    /* 1. JpaMemberRepository를 사용할 때 사용
    - JpaRepository를 사용하기 위해 EntityManager를 주입받아야 합니다.
    - SpringConfig에서 EntityManager를 주입받을 때 이 코드 블록을 활성화합니다.

    private EntityManager em;

    public SpringConfig(EntityManager em){  
        this.em = em;
    }   
    */

    /* 2. JdbcTemplateMemberRepository, JdbcMemberRepository를 사용할 때 사용
    - JdbcTemplate을 사용할 때 DataSource를 주입받아야 합니다.
    - SpringConfig에서 DataSource를 주입받을 때 이 코드 블록을 활성화합니다.

    private DataSource datasource;  

    @Autowired
    public SpringConfig(DataSource datasource){
        this.datasource = datasource;
    }
    */

    /* 3. 원하는 레포지토리를 선택하여 사용할 때 빈으로 수동 등록하여 교체하는 방법
    - 이 메서드에서 주석을 해제하여 원하는 레포지토리를 선택합니다.
    - 각 레포지토리를 선택할 때 주석을 활성화하여 빈을 등록합니다.

    @Bean  
    public MemberRepository memberRepository(){
        // 1. JpaMemberRepository를 사용할 때 이 주석을 해제하여 사용
        return new JpaMemberRepository(em);                   

        // 2. JdbcTemplateMemberRepository를 사용할 때 이 주석을 해제하여 사용
        return new JdbcTemplateMemberRepository(datasource);    

        // 3. JdbcMemberRepository를 사용할 때 이 주석을 해제하여 사용
        return new JdbcMemberRepository(datasource);            

        // 4. MemoryMemberRepository를 사용할 때 이 주석을 해제하여 사용
        return new MemoryMemberRepository();                    
    }
    */


}