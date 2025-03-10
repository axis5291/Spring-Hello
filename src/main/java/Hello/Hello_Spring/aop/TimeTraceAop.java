package Hello.Hello_Spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// *** 이 코드는 Hello.Hello_Spring 패키지 하위의 모든 클래스에 있는 모든 메서드 실행 시간을 측정하기 위해 AOP를 사용한 로깅 코드입니다.

// AOP (Aspect Oriented Programming): 공통 관심 사항(cross-cutting concern)을 핵심 관심 사항(core concern)과 분리하여 관리하는 프로그래밍 기법
// AOP를 사용하면 코드의 중복을 줄이고 모듈화 수준을 높일 수 있습니다.
// 아래 코드는 AOP를 이용해 Hello_Spring 패키지 하위의 모든 클래스와 메서드에 대해 실행 시간을 측정하고 로깅하는 코드입니다.

@Component
@Aspect
public class TimeTraceAop {
 
    @Around("execution(* Hello.Hello_Spring..*(..))")  // 패키지 하위에 있는 모든 클래스의 모든 메서드에 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();  // 대상 메서드 실행
        } finally {   // 예외 발생 여부에 관계없이 실행 시간 출력
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }   
}
