package jp.co.seattle.library.controller;

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

import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.LendingBookService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口　//この中からjspが必要な情報を引っ張ってくる
public class RentBooksController {
    final static Logger logger = LoggerFactory.getLogger(RentBooksController.class);

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
    
    // 借りるボタンを押下した際に、IDを持ってきて、このcontrollerに遷移しdetailsにを戻す。
   
    @Transactional
    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    public String detailsBook(Locale locale,
            @RequestParam("bookId") Integer bookId,
            Model model) {


        
        //Rentテーブルの中にレコードが入っているか確認する。(sql)　        
        int recode = lendingBooksService.lentBookCheckInfo(bookId);
        //bookDetailsInfoの中に1行分のsql文が入っている。
        if (recode != 0) {
            model.addAttribute("error", "すでに貸出中です");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "details";
        } else {
            //　　持っていない時はレコードの追加の処理を書く(sql)
            lendingBooksService.lentBookadd(bookId);
            //            detailsに貸出し可能の表示を行う。
            model.addAttribute("lendingMsg", "貸出中");
            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            return "details";
        }
            
    }

}