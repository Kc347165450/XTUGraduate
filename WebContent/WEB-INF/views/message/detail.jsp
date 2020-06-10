<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-panel" title="留言详情页面"  fit="true" >
		<div style="padding:10px 60px 20px 60px">
		<span>昵称:</span>
		<input id="nickname" name="nickname" readonly="true" value="${message.user.nickname }"><br>
		<span>邮箱:</span>
		<input id="email" name="email" readonly="true" value="${message.email }"><br>
		<span>留言内容:</span><br>
	    <textarea id="content" name="content"  style="width:760px;height:300px;" readonly="true">${message.content }</textarea><br>
	    <span>留言时间:</span>
	    <time><fmt:formatDate value="${message.createTime }" pattern="yyyy-MM-dd hh:mm:ss" /></time>
	    <div style="padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back"  onclick="back()">返回</a>
	    </div>
	    </div>
	</div>

<%@include file="../common/footer.jsp"%>

<script type="text/javascript">
var ue = UE.getEditor('edit-content');
function back(){
	window.history.back(-1);
}	
</script>