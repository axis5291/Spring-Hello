package Hello.Hello_Spring.controller;

//MemberController에서 사용할 폼 데이터를 받기 위한 클래스, create() 메서드의 매개변수로 사용
public class MemberForm {
    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}