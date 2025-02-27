package jp.co.seattle.library.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
@Controller //APIの入り口
public class BulkBooksController {
    final static Logger logger = LoggerFactory.getLogger(BulkBooksController.class);

    @Autowired
    private BooksService booksService;

    @Autowired
    private ThumbnailService thumbnailService;

    /**
     *homeから遷移してくる。
     *@param locale
     *@param model
     *@return
     */
    @RequestMapping(value = "/bulkBook", method = RequestMethod.GET) //value＝actionで指定したパラメータ

    //RequestParamでname属性を取得
    public String login(Model model) {
        return "bulkBook";
    }

    /**書籍情報を一括登録する。
     *@param locale
     *@param sFile
     *@param model
     *@return
     */
    @Transactional
    @RequestMapping(value = "/bulkBook2", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String filecontents(Locale locale,
            @RequestParam("sFile") MultipartFile sFile,
            Model model) {

        String line = null;

        logger.info("Welcome insertBooks.java! The client locale is {}.", locale);

        //ファイルを1行づつ読み込んでいる。
        List<String[]> lines = new ArrayList<String[]>();
        List<String> errorLines = new ArrayList<String>();
        try {
            InputStream stream = sFile.getInputStream();
            Reader reader = new InputStreamReader(stream);
            BufferedReader buf = new BufferedReader(reader);

            boolean isValid = false; //falseの場合は処理を行わない。

            int count = 0;
            while ((line = buf.readLine()) != null) {
                String[] bulkBookInfo = line.split(",", -1);//1行目を区切って分けているbulkBookInfo ,を基準に分けている -1で空白を一つの要素とする。

                lines.add(bulkBookInfo);

                //必須項目があるか

                if (StringUtils.isNullOrEmpty(bulkBookInfo[0]) || StringUtils.isNullOrEmpty(bulkBookInfo[1])
                        || StringUtils.isNullOrEmpty(bulkBookInfo[2])
                        || StringUtils.isNullOrEmpty(bulkBookInfo[3])) {

                    errorLines.add((count + 1) + "行目の必須項目を入力してください");
                    isValid = true; //エラーが起きた際はtrueにしてエラー表示、登録する前に一括登録画面に遷移する。
                    //バリデーションチェックで不正が起きた場合にエラー処理を行う
                }
                //文字数や形式はっているか？（バリデーションチェック）
                //ISBN　if文を使用

                boolean isValidIsbn = bulkBookInfo[4].matches("[0-9]{10}|[0-9]{13}");

                if (!isValidIsbn) {
                    errorLines.add((count + 1) + "行目のISBNの桁数または半角数字が正しくありません");
                    isValid = true;
                }
                //文字数や形式はっているか？（バリデーションチェック）
                //出版日　try catchを使用

                try {
                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                    df.setLenient(false);
                    df.parse(bulkBookInfo[3]);
                } catch (ParseException p) {

                    errorLines.add((count + 1) + "行目の出版日は半角数字のYYYYMMDD形式で入力してください");
                    isValid = true;
                }
                count += 1;
            }
            if (isValid) {
                model.addAttribute("error_lines", errorLines);
                return "bulkBook";
            }
            //isValidにはすでにfalseとtrueが入っているので、==falseなどは入れる必要性がない。

            //本棚からどの部分を読み込むかを表示しているiは本の行で[]はタイトル名などの配列を取り出している。

            for (int i = 0; i < lines.size(); i++) {
                BookDetailsInfo bookInfo = new BookDetailsInfo();
                bookInfo.setTitle(lines.get(i)[0]);
                bookInfo.setAuthor(lines.get(i)[1]);
                bookInfo.setPublisher(lines.get(i)[2]);
                bookInfo.setPublishDate(lines.get(i)[3]);
                bookInfo.setDescription(lines.get(i)[4]);
                bookInfo.setIsbn(lines.get(i)[5]);
                //保存
                booksService.registBook(bookInfo);
            }

            model.addAttribute("resultMessage", "登録完了");
            System.out.print(errorLines);

            //  詳細画面に遷移する
            return "bulkBook";

        } catch (Exception p) {
            return "bulkBook";
        }
    }
}