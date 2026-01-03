package shopping.service;

import shopping.entity.User;

import java.util.Set;

public interface IUserService {
    boolean isVAlidUser(User user);
    //添加
    void addUser(User user);
    //查询
    Set<User> queryAllUser();
}
