package Hello.Hello_Spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP: Aspect Oriented Programming->공통 관심 사항(cross-cutting concern)과 핵심 관심 사항(core concern)을 분리하여 관리하는 프로그래밍 기법
//공통 관심 사항: 여러 모듈에서 공통으로 사용하는 기능 , 핵심 관심 사항: 각 모듈의 핵심 기능
//AOP는 핵심 기능과 공통 기능을 분리하여 관리할 수 있어 코드의 중복을 줄이고 모듈화 수준을 높일 수 있다.
//아래 코드는 AOP를 이용하여 Hello_Spring 패키지 하위에 있는 모든 클래스의 모든 메서드에 적용하여 메서드의 실행 시간을 측정하는 코드이다.
//AOP를 사용하려면 @Aspect 어노테이션을 사용하여 클래스를 선언하고, @Around 어노테이션을 사용하여 적용할 메서드를 선언한다.

//아래는 각각의 클래스마다 각각의 실행 메서드들 실행시간을 측정하는 코드이다.
 @Component
 @Aspect
 public class TimeTraceAop {
 
    @Around("execution(* Hello.Hello_Spring..*(..))")  //패키지 하위에 있는 모든 클래스의 모든 메서드에 적용, 이부분을 수정해서 영역을 지정하면 복사붙여넣기로 사용 가능
     public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
         long start = System.currentTimeMillis();
         System.out.println("START: " + joinPoint.toString());
         try {
             return joinPoint.proceed();  //***대상 클래스들의 메서드들을 실행하는 역할
         } finally {   //try문에서 예외가 발생하더라도 finally문은 항상 실행된다. try를 쓰면 반드시 catch나 finally를 써야한다.
             long finish = System.currentTimeMillis();
             long timeMs = finish - start;
             System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
         }
    }   
}
