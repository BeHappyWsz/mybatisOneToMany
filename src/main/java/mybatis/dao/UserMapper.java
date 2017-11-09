package mybatis.dao;

import mybatis.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    User findById(int id);

    int insertUser(User user);

    int updateUser(User user);
}
