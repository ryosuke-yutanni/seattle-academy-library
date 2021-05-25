<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>ホーム｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/contactform.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Bangers rel="stylesheet">

</head>
<body class="wrapper">
    <header>
        <div class="left">
            <img class="mark" src="resources/img/logo.png" />
            <div class="logo">Seattle Library</div>
        </div>
        <div class="right">
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/">ログアウト</a></li>
            </ul>
        </div>
    </header>

    <main class="ryochan">
        <div class="background">
            <div class="list-button">
                <a href="<%=request.getContextPath()%>/addBook" class="btn_add_book"><span>書籍の追加</span></a> <a href="<%=request.getContextPath()%>/bulkBook" class="btn_bulk_book">一括登録</a> <a href="<%=request.getContextPath()%>/contactform" class="btn_contact">お問い合わせ</a>
                <div class="delete-left-border">
                    <h1>CONTACTFORM</h1>
                </div>
            </div>
            <div class="Form">
                <form method="post" action="contactformcontents">
                    <div class="Form-Item-head">

  
        

                        <p class="Form-Item-Label">
                            <span class="Form-Item-Label-Required">必須</span>お名前
                        </p>
                        <input type="text" class="Form-Item-Input" id="name" name="name" required placeholder="例）油谷亮佑">
                    </div>
                    <div class="Form-Item">
                        <p class="Form-Item-Label">
                            <span class="Form-Item-Label-Required">必須</span>メールアドレス
                        </p>
                        <input type="email" class="Form-Item-Input" id="email" name="email" required placeholder="例）example@gmail.com">
                    </div>
                    <div class="Form-Item">
                        <p class="Form-Item-Label">
                            <span class="Form-Item-Label-Required">任意</span>電話番号
                        </p>
                        <input type="text" class="Form-Item-Input" id="number" name="number" placeholder="例）000-0000-0000" pattern="\d{2,4}-?\d{3,4}-?\d{3,4}">
                    </div>
                    <div class="Form-Item">
                        <p class="Form-Item-Label">
                            <span class="Form-Item-Label-Required">必須</span>お問い合わせ種別
                        </p>
                        <select class="pull-down" name="whatContents">

                            <option hidden>お問い合わせ内容を洗濯してください</option>

                     

                            <option value="追加ができない">追加ができない</option>
                            <option value="編集ができない">編集ができない</option>
                            <option value="編集ができない">一括登録ができない</option>
                            <option value="新規登録ができない">新規登録ができない</option>
                            <option value="その他">その他</option>
                        </select>
                    </div>
                    <div class="Form-Item">
                        <p class="Form-Item-Label isMsg">
                            <span class="Form-Item-Label-Required">必須</span>お問い合わせ内容
                        </p>
                        <textarea class="Form-Item-Textarea" id="contents" name="contents" required></textarea>
                    </div>
                    <button type="submit" value="${bookDetailsInfo.bookId}" name="bookId" class="submit_btn">送信</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
