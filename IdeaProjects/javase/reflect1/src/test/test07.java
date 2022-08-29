package test;
/*获取父类和接口*/
public class test07 {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("java.lang.String");
        Class superClass = c.getSuperclass();
        System.out.println(superClass.getName());

        Class[] interfances = c.getInterfaces();
        for (Class interfance:interfances){
            System.out.println(interfance.getName());
        }


    }
}
