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
public class LendingBookService {
    final static Logger logger = LoggerFactory.getLogger(BooksService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 書籍IDに紐づく書籍詳細情報を取得する
     *
     * @param bookId 書籍ID
     * @return 書籍情報
     */
    public Integer lentBookCheckInfo(int bookId) {
        //q49　に　getBookInfo
        // JSPに渡すデータを設定する
        String sql = "SELECT COUNT(*)"
                + "FROM rent where BOOK_id =" + bookId;
              
        try {
            int recode = jdbcTemplate.queryForObject(sql, Integer.class);
            return recode;

        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        //sqlから持ってきたものをJava利用できるようにする

    }

    //レコードがない時にbookIdを追加する。
    public void lentBookadd(int bookId) {

        String sql = "INSERT INTO rent (BOOK_id) VALUES ('" + bookId + "');";
        //sqlから持ってきたものをJava利用できるようにする
        jdbcTemplate.update(sql);
            
    }

   

}
