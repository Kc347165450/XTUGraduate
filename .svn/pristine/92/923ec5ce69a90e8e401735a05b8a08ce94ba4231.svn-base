<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-panel" title="导师评价详情页面"  fit="true" >
		<div style="padding:10px 60px 20px 60px">
		<h4>匿名：${comment.nickname}</h4>
		<br>
		<span>导师辨识特征:</span>
		<p>${comment.identify}</p>
		<span>学术水平:</span>
		<p>${comment.ability}</p>
		<span>科研经费:</span>
		<p>${comment.funds}</p>
		<span>师生关系:</span>
		<p>${comment.relationship}</p>
		<span>学生前途:</span>
		<p>${comment.future}</p>
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