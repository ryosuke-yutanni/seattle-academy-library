
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
    <main class="thanksform">
         
        <div class="heading">
            <div class="delete-left-border">
                <h1>Thanks for your comments!</h1>
            </div>
        </div>
        <div class="kiran-img">
            <img src="resources/img/kyowa.jpg" alt="" title="キメ顔">
            <div class="kiran"></div>
        </div>
        <div id="anyquestion">
            <p>Any other questions?</p>
        </div>
        <div class="edtDelBookBtn_box">
            <form method="get" action="recontact">
                <a href="<%=request.getContextPath()%>/contactform" class="btn btn-flat"><span>YES</span></a>
            </form>
            <form method="get" action="home">
                <a href="<%=request.getContextPath()%>/home" class="btn btn-flat"><span>NO</span></a>
            </form>
        </div>
        <div>
            <h2 id="sign">Please follow us！</h2>
        </div>
        <div class="slider_wrapper">
            <div class="slider">
                <div>
                    <a href="https://www.youtube.com/watch?v=peWPFBtCjVI"><img src="resources/img/シアトルチャンネル.jpg"></a>
                </div>
                <div>
                    <a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル2.jpg"></a>
                </div>
                <div>
                    <a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル3.jpg"></a>
                </div>
                <div>
                    <a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル4.jpg"></a>
                </div>
                <div>
                    <a href="https://www.youtube.com/watch?v=tjNBr8pGxQY"><img src="resources/img/シアトルチャンネル5.jpg"></a>
                </div>
            </div>
        </div>
    </main>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="resources/js/slick.min.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>
