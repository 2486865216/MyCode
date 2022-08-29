package APO.AnnotationTest;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"APO.Zhujie,APO.AnnotationTest"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Configs {
}
