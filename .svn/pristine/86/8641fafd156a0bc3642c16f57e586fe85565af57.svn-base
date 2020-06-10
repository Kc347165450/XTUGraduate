<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/header.jsp" %>
<head>
<link rel="stylesheet" type="text/css" href="../resources/home/css/teacher.css">
</head>
<section class="container">
<div class="content-wrap">
<div class="content">
<div class="newslist jslist">
<div class="zmnav">
  <table>
  <tr>
  <td><b>学院:</b></td>
  	<td>
  	<c:forEach items="${academyList }" var="academy">
  	<a href="../teacher/teacher_a?aid=${academy.id }" title="${academy.academyName }" target="_blank" >${academy.academyName }</a>
	</c:forEach>
	</td>
  </tr>
  <tr>
  <td><b>专业:</b></td>
  	<td>
  	<c:forEach items="${departmentList }" var="department">
  	<a href="../teacher/teacher_d?aid=${department.academyId }&did=${department.id }" title="${department.departmentName }" target="_blank" >${department.departmentName }</a>
	</c:forEach>
	</td>
  </tr>
  </table>
   
   <ul>
   <c:forEach items="${teacherList }" var="teacher">
	<li>
		<p><a href="../teacher/detail?id=${teacher.id }" title="${teacher.teacherName }"><img src="${teacher.photo }"></a></p>
		<p><a href="../teacher/detail?id=${teacher.id }" title="${teacher.teacherName }">${teacher.teacherName }</a></p>
	</li>
	</c:forEach> 
   </ul>
</div>
</div>
</div>
</div>
<%@ include file="sidebar.jsp" %>
</section>
<%@ include file="../common/footer.jsp" %>
