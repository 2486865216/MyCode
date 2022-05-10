package com.example.Bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/3/15  18:13
 *
 * @Aspect:告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspect {
    //抽取公共的切入点表达式
    //1、本类引用        @Before("pointcut()")
    //2、其他的切面引用   @Before("com.example.Bean.LogAspect.pointcut()")
    @Pointcut("execution(public int com.example.Bean.MathCalculator.*(..))")
    public void pointcut(){}
    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    //@Before("public int com.example.Bean.MathCalculator.div(int, int)")
    @Before("pointcut()")
    //JoinPoint一定要出现在参数表的第一位
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        System.out.println(joinPoint.getSignature().getName()+" :除法运行的参数列表——————————:{"+ Arrays.asList(args) +"}");
    }

    @After("pointcut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+" :除法结束");
    }

    @AfterReturning( value = "pointcut()", returning = "result")
    public void logReturn(Object result){
        System.out.println("除法正常返回。。。运行结果:{"+result+"}");
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void logError(Throwable exception){
        System.out.println("除法异常。。。异常信息:{"+exception+"}");
    }
}
