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

import com.mysql.jdbc.StringUtils;

import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class ContactFormController {
    final static Logger logger = LoggerFactory.getLogger(ContactFormController.class);

    @Autowired
    private BooksService booksService;
    /**
     * Homeボタンからホーム画面に戻るページ
     * @param model
     * @return
     */

    @RequestMapping(value = "/contactform", method = RequestMethod.GET)
    public String transitionHome(Model model) {
        return "contactForm";
    }

    @RequestMapping(value = "/recontact", method = RequestMethod.GET)
    public String recontact() {
        return "contactForm";
    }


    @Transactional
    @RequestMapping(value = "/contactformcontents", method = RequestMethod.POST)
    public String createAccount(Locale locale,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("number") String number,
            @RequestParam("whatContents") String whatContents,
            @RequestParam("contents") String contents,
            Model model) {
        logger.info("Welcome createAccount! The client locale is {}.", locale);

        //Eメールがメール形式であるかのバリデーションチェック
        boolean isEmailVaild = email
                .matches("^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$");
        boolean isNumberVaild = number
                .matches("^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$");

        //必須項目
        if (StringUtils.isNullOrEmpty(name) || StringUtils.isNullOrEmpty(email)
                || StringUtils.isNullOrEmpty(contents)) {
            return "contactform";
        }
        //必須項目、形式が問題なければテーブルに入れる。
        booksService.contactform(name, email, number, whatContents, contents);

        //必須項目、形式が問題なければありがとう画面に遷移する。
        return "thanksContactForm";
    }
}
