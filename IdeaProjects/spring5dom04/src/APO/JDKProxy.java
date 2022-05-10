package APO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {
    public static void main(String[] args){
        Class[] interfaces = {UserDao.class};
//        Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
        UserDaoImp userDaoImp = new UserDaoImp();
        UserDao userDao =(UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces,new UserDaoProxy(userDaoImp));
        int result = userDao.add(1,2);
        System.out.println(result);
    }
}
class UserDaoProxy implements InvocationHandler{
//    增强的逻辑
//    创建的是谁的代理对象把谁传递过来
//    有参构造进行传递
    private Object object;
    public UserDaoProxy(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        方法之前
        System.out.println("方法之前......"+method.getName()+"传递的参数..."+ Arrays.toString(args));
//        被增强的方法执行
        Object res = method.invoke(object,args);
//        方法之后
        System.out.println("方法之后......"+object);
        return res;
    }
}
