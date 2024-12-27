<!DOCTYPE html>
<html lang="en">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signin</title>
    <!-- Logo -->
    <link href="assets/images/logo.png" rel="icon"/>
    <link href="assets/images/logo.png" rel="apple-touch-icon"/>
    <link rel="stylesheet" type="text/css" href="assets/css/as-alert-message.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style-starter.css">
    <link rel="stylesheet" type="text/css" href="assets/css/sign-in.css">
</head>

<body>
<header id="site-header" class="w3l-header fixed-top">
    <!--/nav-->
    <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
        <div class="container">
            <h1><a class="navbar-brand" href="/home"><span class="fa fa-play icon-log"
                                                           aria-hidden="true"></span>
                MyShowz </a></h1>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            </div>
            <div class="Login_SignUp" id="login_s">
                <!-- style="font-size: 2rem ; display: inline-block; position: relative;" -->
                <!-- <li class="nav-item"> -->
                <a class="nav-link" href="/sign"><i class="fa fa-user-circle-o"></i></a>
                <!-- </li> -->
            </div>
            <!-- toggle switch for light and dark theme -->
            <div class="mobile-position">
                <nav class="navigation">
                    <div class="theme-switch-wrapper">
                        <label class="theme-switch" for="checkbox">
                            <input type="checkbox" id="checkbox">
                            <div class="mode-container">
                                <i class="gg-sun"></i>
                                <i class="gg-moon"></i>
                            </div>
                        </label>
                    </div>
                </nav>
            </div>
        </div>
    </nav>
</header>
<div class="container_signup_signin2 d-inline-flex mt-10" id="container_signup_signin2"
     style="height: 850px; width: 80vw;">
    <img src="assets/images/banner/${movie_value.movie_poster_url}" alt="">
    <div class="form-container2 sign-up-container2">
        <form:form method="post" name="sign-up-form" action="${action}" onsubmit="return signUpValidateForm()"
                   modelAttribute="Movie_data" cssStyle="width: 100%">
            <h1 style="color: black; padding-top: 25px;">${title}</h1>
            <span style="font-size: 32px; color: green;">${description}</span>
            <input type="hidden" name="formType" value="Create_User"/>
            <form:input path="movie_id" type="hidden"
                        placeholder="${customer_placeholder.movie_id}"
                        value="${customer_value.movie_id}"/>
            <form:input path="movie_rating" type="hidden"
                        placeholder="${customer_placeholder.movie_rating}"
                        value="${customer_value.movie_rating}"/>
            <form:input path="movie_release_date" type="hidden"
                        placeholder="${customer_placeholder.movie_release_date}"
                        value="${customer_value.movie_release_date}"/>
            <form:input path="movie_created_at" type="hidden"
                        placeholder="${customer_placeholder.movie_created_at}"
                        value="${customer_value.movie_created_at}"/>
            <form:input path="movie_studio" type="hidden"
                        placeholder="${customer_placeholder.movie_studio}"
                        value="${customer_value.movie_studio}"/>
            <form:input path="movie_country" type="hidden"
                        placeholder="${customer_placeholder.movie_country}"
                        value="${customer_value.movie_country}"/>
            <form:input path="movie_poster_url" type="hidden"
                        placeholder="${customer_placeholder.movie_poster_url}"
                        value="${customer_value.movie_poster_url}"/>
            <form:input path="total_tickets_sold" type="hidden"
                        placeholder="${customer_placeholder.total_tickets_sold}"
                        value="${customer_value.total_tickets_sold}"/>
            <form:input path="ticket_price" type="hidden"
                        placeholder="${customer_placeholder.ticket_price}"
                        value="${customer_value.ticket_price}"/>
            <form:input path="revenue" type="hidden"
                        placeholder="${customer_placeholder.revenue}"
                        value="${customer_value.revenue}"/>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Movie
                    title:</label>
                <form:input path="movie_title" name="sign-up-name" type="text"
                            style=""
                            placeholder="${movie_placeholder.movie_title}"
                            value="${movie_value.movie_title}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Description:</label>
                <form:input path="movie_description" name="sign-up-email" type="text"
                            placeholder="${movie_placeholder.movie_description}"
                            value="${movie_value.movie_description}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Duration:</label>
                <form:input path="movie_duration" name="sign-up-email" type="text"
                            placeholder="${movie_placeholder.movie_duration}"
                            value="${movie_value.movie_duration}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Trailer
                    url:</label>
                <form:input path="movie_trailer_url" name="sign-up-phone" type="text"
                            placeholder="${movie_placeholder.movie_trailer_url}"
                            value="${movie_value.movie_trailer_url}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">main actor:</label>
                <form:input path="movie_main_actor" name="sign-up-phone" type="text"
                            placeholder="${movie_placeholder.movie_main_actor}"
                            value="${movie_value.movie_main_actor}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Director:</label>
                <form:input path="movie_director" name="sign-up-phone" type="text"
                            placeholder="${movie_placeholder.movie_director}"
                            value="${movie_value.movie_director}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Genre:</label>
                <form:input path="movie_genre" name="sign-up-gender" type="text"
                            placeholder="${movie_placeholder.movie_genre}"
                            value="${movie_value.movie_genre}"/>
            </div>
            <div class="form-group d-flex " style="display: inline-flex; width: 100%;align-items: center;">
                <label style="margin-left: 0px;width: 180px; justify-self: start; text-align: left;">Movie for
                    Age:</label>
                <form:input path="movie_for_age" name="sign-up-isActive" type="text"
                            placeholder="${movie_placeholder.movie_for_age}"
                            value="${movie_value.movie_for_age}"/>
            </div>
            <button>${title}</button>
        </form:form>
    </div>
</div>
<!-- Hiển thị thông báo lỗi nếu có -->
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
<script type="text/javascript" src="assets/js/as-alert-message.min.js"></script>
<script src="assets/js/jquery-3.3.1.min.js"></script>
<!--/theme-change-->
<script src="assets/js/theme-change.js"></script>
<!-- disable body scroll which navbar is in active -->

<!-- disable body scroll which navbar is in active -->
<!--/MENU-JS-->

<script src="assets/js/bootstrap.min.js"></script>

<script type="text/javascript" src="assets/js/sign-in.js"></script>

</body>

</html>