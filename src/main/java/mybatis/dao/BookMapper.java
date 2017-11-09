package mybatis.dao;

import mybatis.domain.Book;

import java.util.List;

public interface BookMapper {

    Book findById(int id);

    int insertBook(Book book);

    int batchInsertBook(List<Book> books);

    int updateBook(Book book);
}
