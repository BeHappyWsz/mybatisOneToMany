package mybatis.dao;

import mybatis.domain.Book;

import java.util.List;

public interface BookMapper {

    List<Book> findAll();

    Book findById(int id);

    int insertBook(Book book);

    int batchInsertBook(List<Book> books);
}
