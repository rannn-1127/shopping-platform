package shopping.dao;

import shopping.entity.User;

import java.util.Set;

public interface IUserDao {
    void addUser(User user);
    Set<User> queryAllUser();
}
