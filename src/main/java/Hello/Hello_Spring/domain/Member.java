package Hello.Hello_Spring.domain;

public class Member {
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
