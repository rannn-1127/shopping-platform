package shopping.dao;

import shopping.db.DataBase;
import shopping.entity.User;

import java.util.Set;

public class UserDaoImpl implements IUserDao{
    private Set<User> users = DataBase.getUsers();
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Set<User> queryAllUser() {
        users = DataBase.getUsers();
        return users;
    }
}
