package mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author wsz
 * @date 2017年11月8日20:04:14
 */
public class User implements Serializable{

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String realName;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private List<Book> books;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
