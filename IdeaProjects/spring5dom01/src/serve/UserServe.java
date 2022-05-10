package serve;

import dog.UserInter;

public class UserServe {

    private UserInter userInter;

    public void setUserInter(UserInter userInter) {
        this.userInter = userInter;
    }

    public void add(){
        System.out.println("serve add................");
        userInter.add();
    }

//    public static void main(String[] args) {
//        UserInter userInter = new User();
//        userInter.add();
//    }
}
