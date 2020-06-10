<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../resources/home/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../resources/home/css/nprogress.css">
<link rel="stylesheet" type="text/css" href="../resources/home/css/style.css">
<link rel="stylesheet" type="text/css" href="../resources/home/css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="../resources/home/images/icon.png">
<link rel="shortcut icon" href="../resources/home/images/favicon.ico">
<header>
    <nav class="navbar navbar-default" role="navigation" style="background-color: white">
        <div class="navbar-header" style="margin-left: 10%">
            <div>
                <!--向左对齐-->
                <ul class="nav navbar-nav navbar-right">
                <c:forEach items="${tabList }" var="tab">
                	<li class="active" ><a href="../forum/tab?id=${tab.tabNameEn }" data-cont="${tab.tabName }" title="${tab.tabName }">${tab.tabName }</a></li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </nav>


</header>
