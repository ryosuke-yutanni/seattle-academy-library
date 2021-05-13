package jp.co.seattle.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 書籍サービス
 * 
 *  booksテーブルに関する処理を実装する
 */
@Service

public class DeleteBookService {
    final static Logger logger = LoggerFactory.getLogger(BooksService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DeleteBookService deletebookcheckinfo;

    /**
     * 書籍IDに紐づく書籍詳細情報を取得する
     *
     * @param bookId 書籍ID
     * @return 書籍情報
     */
    //rentから、idがあるかどうかを確認する。
    public Integer deleteBookCheckInfo(int bookId) {

        String sql = "SELECT COUNT(*)"
                + "FROM rent where BOOK_id =" + bookId;

        try {
            int recode = jdbcTemplate.queryForObject(sql, Integer.class);
            return recode;


        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    //レコードがない時にbookIdを削除する。
    public void returndeleteBook(int bookId) {
        String sql = "DELETE from books where ID =" + bookId;
        jdbcTemplate.update(sql);
    }

}
