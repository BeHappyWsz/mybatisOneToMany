package mybatis.controller;

import com.mysql.jdbc.StringUtils;
import mybatis.dao.BookMapper;
import mybatis.dao.UserMapper;
import mybatis.domain.Book;
import mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static SqlSession session = null;
    public static void main(String[] args) throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        try{
//            insertUser(mapper);
              findUser(mapper);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    public static  void findUser(UserMapper mapper){
        User user = mapper.findById(11);
        System.out.println(user.toString());
        List<Book> books = user.getBooks();
        System.out.println(books.size());
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
    public static  void insertUser(UserMapper mapper){
        User user = new User();
        user.setUsername("222");
        user.setRealName("222");
        user.setPassword("222");
        int id = mapper.insertUser(user);

        List<Book> books = new ArrayList<Book>();
        for(int i = 0;i < 3;i++){
            Book b = new Book();
            b.setName("大白菜"+i);
            b.setTitle("系统"+i);
            b.setAuthor("ww"+i);
            b.setPrice(2.5D);
            b.setDes("东风谷");
            b.setUser(user);
            books.add(b);
        }
        BookMapper bookMapper = session.getMapper(BookMapper.class);
        int ids =  bookMapper.batchInsertBook(books);
        session.commit();

        System.out.println(id + "_"+ ids);
    }
}
