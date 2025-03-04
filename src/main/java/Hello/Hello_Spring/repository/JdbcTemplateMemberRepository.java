package Hello.Hello_Spring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import Hello.Hello_Spring.domain.Member;

//JdbcTemplate을 이용한 클래스, 아래는 JdbcTemplate을 이용하면 어떤 장점이 있는지 설명한 것이다.
// SQL을 직접 제어하면서도 JDBC의 복잡한 설정을 줄일 수 있음
// ✔ JDBC API의 반복적인 Connection & Statement 처리가 필요 없음
// ✔ 예외 처리를 자동으로 해주므로 try-catch 블록이 필요 없음
// ✔ 간단한 데이터 조회/저장에 유용하게 사용 가능


public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {   
        //DataSource는 스프링이 application.properties의 DB 설정값을 통해 자동으로 객체로 생성해주고 빈과 같이 생성할 필요없이 가져다 쓰기만 하면 된다.
        jdbcTemplate = new JdbcTemplate(dataSource);  //Spring이 application.properties의 DB 설정값을 통해 DataSource를 자동으로 생성함
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); 
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");//멤버테이블에 id값을 자동으로 생성해주는 코드
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }
      @Override
     public List<Member> findAll() {
         return jdbcTemplate.query("select * from member", memberRowMapper());
     }
     @Override
     public Optional<Member> findByName(String name) {
         List<Member> result = jdbcTemplate.query("select * from member where  name = ?", memberRowMapper(), name);
         return result.stream().findAny();
     }
     private RowMapper<Member> memberRowMapper() {
         return (rs, rowNum) -> {
             Member member = new Member();
             member.setId(rs.getLong("id"));
             member.setName(rs.getString("name"));
             return member;
         }; 
        }
}