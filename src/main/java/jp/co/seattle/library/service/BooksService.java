package jp.co.seattle.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.rowMapper.BookDetailsInfoRowMapper;
import jp.co.seattle.library.rowMapper.BookInfoRowMapper;

/**
 * 書籍サービス
 * 
 *  booksテーブルに関する処理を実装する
 */
@Service
public class BooksService {
    final static Logger logger = LoggerFactory.getLogger(BooksService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 書籍リストを取得する
     *
     * @return 書籍リスト
     */
    public List<BookInfo> getBookList() {

        // TODO 取得したい情報を取得するようにSQLを修正
        //書籍名、出版社、著者、出版日、サムネイル、id
        List<BookInfo> getedBookList = jdbcTemplate.query(
                "SELECT id,title,author,publisher,publish_date,thumbnail_url,description,isbn FROM books ORDER BY title asc",
                new BookInfoRowMapper());

        return getedBookList;
    }

    /**
     * 書籍IDに紐づく書籍詳細情報を取得する
     *
     * @param bookId 書籍ID
     * @return 書籍情報
     */
    public BookDetailsInfo getBookInfo(int bookId) {
        //q49　に　getBookInfo
        // JSPに渡すデータを設定する
        String sql = "SELECT * FROM books where id ="
                + bookId; //引数で指定したIdに対応するもの

        BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());
        //sqlから持ってきたものをJava利用できるようにする
        return bookDetailsInfo;
    }

    /**
     * 書籍を登録する
     *
     * @param bookInfo 書籍情報
     */
    public void registBook(BookDetailsInfo bookInfo) {

        String sql = "INSERT INTO books (title, author,publisher,thumbnail_name,thumbnail_url,publish_date,description,isbn,reg_date,upd_date) VALUES ('"
                + bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "','"
                + bookInfo.getThumbnailName() + "','"
                + bookInfo.getThumbnailUrl() + "','"
                + bookInfo.getPublishDate() + "','"
                + bookInfo.getDescription() + "','"
                + bookInfo.getIsbn() + "',"
                + "sysdate(),"
                + "sysdate())";
        jdbcTemplate.update(sql);
    }

    /**
     * 書籍を編集する
     *
     * @param bookInfo 書籍情報
     */
    public void editBook(BookDetailsInfo bookInfo) {
        //文字列をダブル　をシングルにする。自分で打ち込む必要はないものはダブルいらない
        String sql = "update books set title = ' " + bookInfo.getTitle()
                + "' , author = ' " + bookInfo.getAuthor()
                + "',publisher = '" + bookInfo.getPublisher()
                + "' , thumbnail_name = '" + bookInfo.getThumbnailName()
                + "' , thumbnail_url ='" + bookInfo.getThumbnailUrl()
                + "' , publish_date = '" + bookInfo.getPublishDate()
                + "', description = '" + bookInfo.getDescription()
                + "' ,isbn ='" + bookInfo.getIsbn()
                + "',upd_date = sysdate()"
                + " where id = " + bookInfo.getBookId() + ";";
        //最後のところは変数を表すため
        jdbcTemplate.update(sql);
    }

    /*書籍をデータベースから持ってきて削除する
    
     */

    public void deletingBook(int bookId) {
        String sql = "DELETE from books where id =" + bookId;
        jdbcTemplate.update(sql);
    }

    public void contactform(String name, String email, String number, String whatContents, String contents) {
        String sql = "INSERT INTO contactform (CUSTOMER_NAME,EMAIL,number,WHAT_CONTENTS,CONTENTS,REG_DATE,UPD_DATE) VALUES ('"
                + name + "','"
                + email + "','"
                + number + "','"
                + whatContents + "','"
                + contents + "',"
                + "sysdate(),"
                + "sysdate())";

        jdbcTemplate.update(sql);
    }

}
