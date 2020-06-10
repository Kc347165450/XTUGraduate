<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<head>
    <meta name="Content-Type"  content="text/html;charset=utf-8">
    <meta name="keywords" content="湘潭大学研讯论坛">
    <title>他人信息</title>
    <script src="../resources/forum/js/jquery-3.2.1.js"></script>
    <script src="../resources/forum/js/bootstrap.min.js"></script>
    <style>
    	a{
            color: #8A8A8A;
            cursor: pointer;
        }
    </style>
</head>

<div class="panel panel-default" id="main" style="width: 70%;margin:1% 2% 5% 5%;float: left;">
	<div style="padding:6px;">
		<c:if test="${other.sex == 0 }"><h4>他的帖子</h4></c:if>
		<c:if test="${other.sex == 1 }"><h4>他的帖子</h4></c:if>
		<c:if test="${other.sex == 2 }"><h4>她的帖子</h4></c:if>	
  	</div>
<c:forEach items="${topicList }" var="topic">
 	 <article class="excerpt excerpt-1" style="padding:4px;">
		<header><a class="cat" href="../forum/tab?id=${topic.tab.tabNameEn }" title="${topic.tab.tabName}" >${topic.tab.tabName}<i></i></a>
			<h2><a href="../topic/detail?id=${topic.id}" title="${topic.title}" target="_blank" >${topic.title}</a>
			</h2>
		</header>
		<p class="meta">
			<span ><strong>${topic.user.nickname}</strong></span>&nbsp;&nbsp;&nbsp;
			<time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${topic.createTime}" pattern="yyyy-MM-dd hh:mm:ss" /></time>
			<span class="views"><i class="glyphicon glyphicon-eye-open"></i> ${topic.click }</span> 
			<span class="comment"><i class="glyphicon glyphicon-comment"></i>${topic.countReplies}</span>
		</p>
		
	</article>
</c:forEach>

</div>
<div class="panel panel-default" id="sidebar2" style="width: 20%;margin:1% 2% 1% 0%;float: right">
        <div class="panel-heading" style="background-color: white;text-align: center">
            <a href="../home/user/other?id=${other.id}">${other.nickname}</a>
        </div>
        <ul class="list-group" style="width: 100%">
            <li class="list-group-item">邮箱：${other.email}</li>
            <li class="list-group-item">年龄：${other.age}</li>
            <li class="list-group-item">
            	性别：
				<c:if test="${other.sex == 0 }">保密</c:if>
				<c:if test="${other.sex == 1 }">男</c:if>
				<c:if test="${other.sex == 2 }">女</c:if>
            </li>
        </ul>
</div>

<!-- 引入footer文件 -->
