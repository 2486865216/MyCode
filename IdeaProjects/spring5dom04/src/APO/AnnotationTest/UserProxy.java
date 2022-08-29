package APO.AnnotationTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
//有多个增强类对同一方法进行增强，设置增强类优先级
//        在增强类上面添加注解@Order(数字类型值)  值越小，优先级越高
//增强类
@Component
@Aspect //生成代理对象
@Order(1)
public class UserProxy {
    //相同切入点抽取
    @Pointcut(value = "execution(* APO.AnnotationTest.User.add(..))")
    public void Point(){}
    //前置通知
    //@Before表示作为前置通知
    @Before(value = "Point()")
    public void before(){
        System.out.println("before......");
    }
    //后置通知
    @AfterReturning(value = "execution(* APO.AnnotationTest.User.add(..))")
    public void AfterReturning(){
        System.out.println("AfterReturning......");
    }
//    异常通知
    @AfterThrowing(value = "execution(* APO.AnnotationTest.User.add(..))")
    public void afterthrow(){
        System.out.println("afterthrow......");
    }
//    环绕通知
    @Around(value = "execution(* APO.AnnotationTest.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("Beforearound......");
        //执行增强方法
        proceedingJoinPoint.proceed();
        System.out.println("Afteraround......");
    }
    //    最终通知
    @After(value = "execution(* APO.AnnotationTest.User.add(..))")
    public void after(){
        System.out.println("after......");
    }
}
