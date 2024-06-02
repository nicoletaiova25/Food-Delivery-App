package User;

import java.util.*;

public class Login {//clasa singleton
    private Set <User> users;
    private static Login single_instance = null;
    private User current_user;

    private Login() {
        this.users = new HashSet <User>();
        this.current_user = null;
    }

    public static Login getInstance() {

        if (single_instance == null) {
            single_instance = new Login();
        }

        return single_instance;
    }

    //logging in
    public boolean signIn(String email, String password) {
        if(users != null){
            for(User it : users){
                if(email.equals(it.getEmail()) && password.equals(it.getPassword())){
                    this.current_user = it;
                    return true;
                }
            }
        }
        return false;
    }

    //signing up
    public boolean signUp(User newUser){
        if(users != null){
            for(User it : users){
                if(newUser.getEmail().equals(it.getEmail()) && newUser.getPassword().equals(it.getPassword())){
                    return false;
                }
            }
        }
        this.users.add(newUser);
        return true;
    }

    public User getCurrentUser() {
        return current_user;
    }

    public void setCurrentUser(User current_user) {
        this.current_user = current_user;
    }

    public void setUsers(Set <User> users) {
        this.users = users;
    }
}
