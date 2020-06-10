<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../home/common/header.jsp"%>
<head>
    <meta name="Content-Type"  content="text/html;charset=utf-8">
    <meta name="keywords" content="湘潭大学研讯论坛">
    <title>湘潭大学研讯论坛</title>
    <script src="../resources/forum/js/jquery-3.2.1.js"></script>
    <script src="../resources/forum/js/bootstrap.min.js"></script>
    <style>
    	a{
            color: #8A8A8A;
            cursor: pointer;
        }
    </style>
</head>
<!-- 引入header文件 include file="header.jsp"-->
<%@ include file="common/header.jsp"%>
<div class="panel panel-default" id="main" style="width: 70%;height:100%;margin:1% 2% 5% 5%;float: left;">
<c:forEach items="${topics }" var="topic">
 	 <article class="excerpt excerpt-1" style="padding:4px;">
		<header><a class="cat" href="../forum/tab?id=${topic.tab.tabNameEn }" title="${topic.tab.tabName}" >${topic.tab.tabName}<i></i></a>
			<h2><a href="../topic/detail?id=${topic.id}" title="${topic.title}" target="_blank" >${topic.title}</a>
			</h2>
		</header>
		<p class="meta">
			<span ><strong><a href="../user/other?id=${topic.userId}">${topic.user.nickname}</a></strong></span>&nbsp;&nbsp;&nbsp;
			<time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${topic.createTime}" pattern="yyyy-MM-dd hh:mm:ss" /></time>
			<span class="views"><i class="glyphicon glyphicon-eye-open"></i> ${topic.click }</span> 
			<span class="comment"><i class="glyphicon glyphicon-comment"></i>${topic.countReplies}</span>
		</p>
		
	</article>
</c:forEach> 	
<%-- 
<ul class="list-group" style="width: 100%">
    <c:forEach items="${topics}" var="topic">
    <li class="list-group-item">
        <div style="height: 50px">
            <div style="width: 89%;float: left">
                <a href="../topic/detail?id=${topic.id}">${topic.title}</a><br/>
                <div>
                    <a><span class="label label-default" >${topic.tab.tabName}</span></a>&nbsp;&nbsp;&nbsp;
                    <!-- 用户个人主页 <a href="/member/${topic.user.id}"></a>-->
                    <span ><strong>${topic.user.nickname}</strong></span>&nbsp;&nbsp;&nbsp;
                    <small class="text-muted">
                    <time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${topic.createTime}" pattern="yyyy-MM-dd hh:mm:ss" /></time>
                    </small>
                    <!-- <small class="text-muted">${topic.createTime}</small> -->
                </div>
            </div>
            <div style="width: 5%;float: right;text-align: center">
                <span class="badge">${topic.countReplies}</span>
            </div>
        </div>
    </li>
    </c:forEach>

</ul>
 --%>

</div>

<!-- 引入侧边栏文件 -->
<%@ include file="common/side.jsp"%>

<!-- 引入footer文件 -->
<%@ include file="../home/common/footer.jsp" %>
