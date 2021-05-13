package jp.co.seattle.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.LendingBookService;
import jp.co.seattle.library.service.ReturnBookService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口　//この中からjspが必要な情報を引っ張ってくる
public class ReturnBooksController {
    final static Logger logger = LoggerFactory.getLogger(ReturnBooksController.class);

    @Autowired
    private ReturnBookService returnBooksService;

    @Autowired
    private LendingBookService lendingBooksService;

    @Autowired
    private BooksService booksService;

    //自動的インスタンス化　　BooksService　newして

    //@RequestMappingでつなげる。
    /**
     * @param locale  　ロケール情報
     * @param bookId　編集する本のID
     * @param model model
     * @return　遷都先画面
     */
    @RequestMapping(value = "/returnBook", method = RequestMethod.POST) //value＝actionで指定したパラメータ 
    //RequestParamでname属性を取得
    public String login(Locale locale,
            @RequestParam("bookId") int bookId, //bookIdに対応するIdが入っている。
            Model model) {
        //model.addAttribute("bookList", booksService.getBookList());
        //Rentテーブルの中にレコードが入っているか確認する。(sql)
        int recode1 = lendingBooksService.lentBookCheckInfo(bookId);

        int record = returnBooksService.returnBookCheckInfo(bookId);
        //bookDetailsInfoの中に1行分のsql文が入っている。
        if (recode1 != 0) {
            returnBooksService.returndeleteBook(bookId);
            //            detailsに貸出し可能の表示を行う。
            model.addAttribute("returnMsg", "貸出可");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "details";

        } else {
            model.addAttribute("error", "返せません");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "details";
            //　　持っていない時はレコードの追加の処理を書く(sql)
        }

    }

}
