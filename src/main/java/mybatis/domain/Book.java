package mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * @author wsz
 * @date 2017年11月8日20:04:54
 */
public class Book implements Serializable {

    @Setter
    @Getter
    private User user;

    @Setter
    @Getter
    private String userId;

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String author;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private double price;

    @Setter
    @Getter
    private String des;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", des='" + des + '\'' +
                '}';
    }
}
