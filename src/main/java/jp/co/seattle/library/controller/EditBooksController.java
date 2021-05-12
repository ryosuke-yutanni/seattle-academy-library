package jp.co.seattle.library.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.ThumbnailService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口　//この中からjspが必要な情報を引っ張ってくる
public class EditBooksController {
    final static Logger logger = LoggerFactory.getLogger(EditBooksController.class);

    @Autowired
    private BooksService booksService;
    //自動的インスタンス化　　BooksService　newして
    @Autowired
    private ThumbnailService thumbnailService;

    //@RequestMappingでつなげる。
    /**
     * @param locale  　ロケール情報
     * @param bookId　編集する本のID
     * @param model model
     * @return　遷都先画面
     */
    @RequestMapping(value = "/editBook", method = RequestMethod.POST) //value＝actionで指定したパラメータ 
    //RequestParamでname属性を取得
    public String login(Locale locale,
            @RequestParam("bookId") int bookId, //bookIdに対応するIdが入っている。
            Model model) {
        model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
        ///黄色のbookDetailsInfoをjspに送るときに新たな変数を用意している。
        return "editBook";
    }

    /**
     * 書籍情報を更新する
     * @param locale ロケール情報
     * @param title 書籍名
     * @param author 著者名
     * @param publisher 出版社
     * @param publishDate 出版日
     * @param isbm ISBM
     * @param description 説明文
     * @param file サムネイルファイル
     * @param model モデル
     * @return 遷移先画面
     */
    //更新が推されるよここで
    @Transactional //Integerを使っているからintだけで大丈夫
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String reinsertBook(Locale locale,
            @RequestParam("bookId") int bookId, //既存の情報を持ってくるため
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("publisher") String publisher,
            @RequestParam("publishDate") String publishDate,
            @RequestParam("thumbnail") MultipartFile file,
            @RequestParam("description") String description,
            @RequestParam("isbn") String isbn,
            Model model) {
        logger.info("Welcome insertBooks.java! The client locale is {}.", locale);
        //赤文字と黒もじ出すやつ（）エラー分
        //
        // パラメータで受け取った書籍情報をDtoに格納する。
        //  @RequestParamの情報をdtoに格納する（中継のような役割
        //Dtoに入っている要素がプライベートだから引数を渡すときに、setを使う
        BookDetailsInfo bookInfo = new BookDetailsInfo();
        bookInfo.setBookId(bookId);
        bookInfo.setTitle(title);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setPublishDate(publishDate);
        bookInfo.setDescription(description);
        bookInfo.setIsbn(isbn);

        // クライアントのファイルシステムにある元のファイル名を設定する
        String thumbnail = file.getOriginalFilename();

        if (!file.isEmpty()) {
            try {
                // サムネイル画像をアップロード
                String fileName = thumbnailService.uploadThumbnail(thumbnail, file);
                // URLを取得
                String thumbnailUrl = thumbnailService.getURL(fileName);

                bookInfo.setThumbnailName(fileName);
                bookInfo.setThumbnailUrl(thumbnailUrl);

            } catch (Exception e) {

                // 異常終了時の処理
                logger.error("サムネイルアップロードでエラー発生", e);
                model.addAttribute("bookDetailsInfo", bookInfo);
                return "editBook";
            }
        }

        //必須項目　// model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));をバリデーションチェックの後に持ってくる。
        if (StringUtils.isNullOrEmpty(title) || StringUtils.isNullOrEmpty(author)
                || StringUtils.isNullOrEmpty(publisher)
                || StringUtils.isNullOrEmpty(publishDate)) {
            model.addAttribute("error", "必須項目を入力してください");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "editBook";
        }

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            df.setLenient(false);
            df.parse(publishDate);
        } catch (ParseException p) {
            model.addAttribute("error", "ISBNの桁数または半角数字が正しくありません<br>出版日は半角数字のYYYYMMDD形式で入力してください");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "editBook";
        }
        boolean isValidIsbn = isbn.matches("[0-9]{10}|[0-9]{13}");

        if (!isValidIsbn) {
            model.addAttribute("error", "ISBNの桁数または半角数字が正しくありません<br>出版日は半角数字のYYYYMMDD形式で入力してください");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "editBook";
        }
        // 書籍情報を編集する
        booksService.editBook(bookInfo);

        model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));

        //  詳細画面に遷移する
        return "details";
    }
}