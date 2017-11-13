package mybatis.controller;

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
import java.util.Random;
import java.util.logging.Logger;

public class Test {
    private static SqlSession session = null;
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("test");
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        try{
            logger.info("开启第一个session");
            session = sqlSessionFactory.openSession();
            BookMapper bookMapper1 = session.getMapper(BookMapper.class);
            logger.info("同一个session查询1");
            Book book = bookMapper1.findById(30);
            logger.info("同一个session查询2");
            Book book1 = bookMapper1.findById(30);
            logger.info("查询2结束");
            session.commit();


            logger.info("开启第二个session");
            SqlSession session2 = sqlSessionFactory.openSession();
            BookMapper bookMapper2 = session2.getMapper(BookMapper.class);
            Book book2 = bookMapper2.findById(30);
            logger.info("查询结束");
            session2.commit();
//            UserMapper mapper = session.getMapper(UserMapper.class);
//            insertUser(mapper);
//            findUser(mapper);
//            update(mapper,bookMapper);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    public  static void update(UserMapper mapper,BookMapper bookMapper){
        User user = mapper.findById(29);
        user.setUsername("28");
        int i = mapper.updateUser(user);

        List<Book> books = user.getBooks();
        if(!books.isEmpty()){//测试更新一条数据
            Book book = books.get(0);
            book.setName("翻车鱼");
            book.setUserId("29");
            bookMapper.updateBook(book);
        }
        session.commit();
        System.out.println(i);
    }

    public static  void findUser(UserMapper mapper){
        User user = mapper.findById(29);
        System.out.println(user.toString());
        List<Book> books = user.getBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
    public static  void insertUser(UserMapper mapper){
        Random random = new Random();
        User user = new User();
        user.setUsername(String.valueOf(random.nextInt()));
        user.setRealName(String.valueOf(random.nextInt()));
        user.setPassword(String.valueOf(random.nextInt()));
        int id = mapper.insertUser(user);

        List<Book> books = new ArrayList<Book>();
        for(int i = 0;i < 3;i++){
            Book b = new Book();
            b.setName("大白菜"+i);
            b.setTitle("系统"+i);
            b.setAuthor("ww"+i);
            b.setPrice(2.5D);
            b.setDes("东风谷");
            b.setUserId(String.valueOf(user.getId()));
            books.add(b);
        }
        BookMapper bookMapper = session.getMapper(BookMapper.class);
        int ids =  bookMapper.batchInsertBook(books);
        session.commit();
        System.out.println(id + "_"+ ids);
    }
}
