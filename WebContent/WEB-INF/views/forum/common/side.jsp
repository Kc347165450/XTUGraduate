<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div>
	
<c:if test="${empty user}">
	
    <!-- 未登录 -->
    <div class="panel panel-default" id="sidebar2" style="width: 20%;margin:1% 2% 1% 0%;float: right">
        <div class="widget widget_search">
			<form class="navbar-form" action="../forum/search_list" method="get">
			  <div class="input-group">
				<input type="text" name="keyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off" value="${keyword }">
				<span class="input-group-btn">
				<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
				</span> </div>
			</form>
  		</div>
        
        <div class="panel-heading" style="background-color: white;text-align: center;">
            <blockquote>
                湘大论坛
                <small>让你的研途更加平坦</small>
            </blockquote>
        </div>
        <!-- 
        <ul class="list-group" style="width: 100%">
            <li class="list-group-item">
                <a  href="/signin" class="btn btn-primary btn-block">登录</a>
                <a  href="/signup" class="btn btn-default btn-block">注册</a>
            </li>
        </ul>
         -->
    </div>
</c:if>

<c:if test="${!empty user}">
    <!-- 已登录 -->
    <div class="panel panel-default" id="sidebar2" style="width: 20%;margin:1% 2% 1% 0%;float: right">
        <div class="widget widget_search">
			<form class="navbar-form" action="../forum/search_list" method="get">
			  <div class="input-group">
				<input type="text" name="keyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off" value="${keyword }">
				<span class="input-group-btn">
				<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
				</span> </div>
			</form>
  		</div>
        
        <div class="panel-heading" style="background-color: white;text-align: center">
            <a href="../user/info">${user.nickname}</a>
        </div>
        <ul class="list-group" style="width: 100%">
            <li class="list-group-item" style="background-color: #E0FFFF;"><a href="../topic/newTopic">发帖</a></li>
            <li class="list-group-item" style="background-color: #F0FFFF;"><a href="" id="credit" name="credit"></a></li>
        </ul>
    </div>
</c:if>

 <div class="panel panel-default" id="sidebar1" style="width: 20%;margin:1% 2% 1% 0%;float: right">
    <div class="panel-heading" style="background-color: white;text-align: center;">
        热帖
    </div>
    <ul class="list-group" style="width: 100%">
        <c:forEach items="${hotestTopics}" var="hotestTopic">
            <li class="list-group-item"><a href="../topic/detail?id=${hotestTopic.id}">${hotestTopic.title}</a></li>
        </c:forEach>
    </ul>
</div> 
</div>
<script type="text/javascript">
$.get("../forum/credit",function(data){
	$("#credit").html("积分："+data);
},"json")
</script>
