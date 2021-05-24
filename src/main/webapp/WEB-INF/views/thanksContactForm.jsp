
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
<link href="<c:url value="/resources/css/thankscontactform.css" />" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/slick.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/slick-theme.css" />" rel="stylesheet" type="text/css">
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
    <main>
        <div class="main-box abcd">
             
            <div class="heading">
                <h1>お問い合わせ頂き誠にありがとうございます！</h1>
                <div class="kyowapic">
                    <img src="resources/img/kyowa.jpg" alt="" title="キメ顔">
                </div>
                <p>他にもお問い合わせはございますか？</p>
            </div>
            <div class="edtDelBookBtn_box">
                <form method="get" action="recontact">
                    <button type="submit" value="${bookDetailsInfo.bookId}" name="" class="btn_returnBook">YES</button>
                </form>
                <form method="get" action="home">
                    <button type="submit" value="${bookDetailsInfo.bookId}" name="bookId" class="btn_rentBook">NO</button>
                </form>
            </div>
            <div class="slider">
                <div><a href="https://www.youtube.com/watch?v=peWPFBtCjVI"><img src="resources/img/シアトルチャンネル.jpg"></a></div>
                <div><a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル2.jpg"></a></div>
                <div><a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル3.jpg"></a></div>
                <div><a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル4.jpg"></a></div>
                <div><a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル5.jpg"></a></div>
            </div>
        </div>
    </main>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="resources/js/slick.min.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>
