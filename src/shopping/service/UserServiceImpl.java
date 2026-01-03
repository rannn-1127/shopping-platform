package shopping.service;

import shopping.dao.UserDaoImpl;
import shopping.entity.User;

import java.util.Set;

public class UserServiceImpl implements IUserService {
    private Set<User> users;
    private UserDaoImpl userDaoImpl =  new UserDaoImpl();
    @Override
    public boolean isVAlidUser(User user) {
        users = userDaoImpl.queryAllUser();
        for(User u:users){
            if(user.getName().equals(u.getName())&&
                user.getPassword().equals(u.getPassword())&&
                user.getRole().equals(u.getRole())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        userDaoImpl.addUser(user);
    }

    @Override
    public Set<User> queryAllUser() {
        return userDaoImpl.queryAllUser();
    }
}
