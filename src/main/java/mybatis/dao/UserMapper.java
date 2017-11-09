package mybatis.dao;

import mybatis.domain.User;

public interface UserMapper {

    User findById(int id);

    int insertUser(User user);

    int updateUser(User user);
}
