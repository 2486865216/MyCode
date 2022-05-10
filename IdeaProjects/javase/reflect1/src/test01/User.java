package test01;

public class User {
    public boolean login(String name,String password){
        if ("abc".equals(name) && "123".equals(password)){
            return true;
        }
        return false;
    }
    public void logout(){
        System.out.println("logout");
    }
}
