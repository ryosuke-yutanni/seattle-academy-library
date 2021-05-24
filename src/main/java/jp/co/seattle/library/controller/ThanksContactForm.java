package jp.co.seattle.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class ThanksContactForm {
    final static Logger logger = LoggerFactory.getLogger(ThanksContactForm.class);

    /**
     * Homeボタンからホーム画面に戻るページ
     * @param model
     * @return
     */
    @RequestMapping(value = "/thanksContactForm", method = RequestMethod.POST)
    public String transitionHome(Model model) {

        return "home";
    }

}
