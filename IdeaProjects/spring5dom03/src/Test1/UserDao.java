package Test1;

import org.springframework.stereotype.Service;

@Service(value="userDao")
public class UserDao implements User{

    @Override
    public void add() {
        System.out.println("UserDao add .......");
    }
}
