<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<head>
    <meta charset="UTF-8">
    <link href="../resources/forum/css/bootstrap.min.css" rel="stylesheet">
    <script src="../resources/forum/js/jquery-3.2.1.js"></script>
    <script src="../resources/forum/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.8.2/css/all.min.css">
    <title>${topic.title} - 湘大论坛 </title>
</head>
<!-- 引入header文件   include file="../common/header.jsp"-->

<div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
<div class="panel panel-default" id="main" style="">
    <div class="panel-heading" style="background-color: #F0FFFF">
        <div>
            <div class="panel-heading" style="background-color: #F0FFFF">
                <a href="/XtuGraduate/forum/cate">湘大论坛</a> › 帖子详情
            </div>
            <h3>${topic.title}</h3><br/>
            <div>
                <a href="../user/other?id=${topic.userId}"><span ><strong>${topic.user.nickname}</strong></span></a>&nbsp;&nbsp;
                <small class="text-muted">
                    <time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${topic.createTime}" pattern="yyyy-MM-dd hh:mm:ss" /></time>
                </small>
                <small class="text-muted">${topic.click}次点击</small>
            </div>
        </div>

    </div>

    <ul class="list-group" style="width: 100%;background-color: #F0F8FF" >
            <li class="list-group-item" style="background-color: #F0F8FF">
                ${topic.content}
            </li>
            <c:if test="${!empty topic.filepath }">
            <li class="list-group-item" style="background-color: #F0F8FF">
            	附件：${topic.filename }
            	<c:if test="${!empty user}">
            	<input type="hidden" id="UserId" name="UserId" value="${user.id}">
            	<input type="hidden" id="TopicUserId" name="TopicUserId" value="${topic.userId}">
            	<!-- <button type = "button" onclick = "download()">下载附件</button>
            	<input id="topicFile" name="topicFile" type="button" value="下载附件"/> -->
            	<a id="downFile" name="downFile" href="javascript:void(0)" onclick="downfile()">下载附件</a>
            	</c:if>
            	<c:if test="${empty user}">
            	<a href="/XtuGraduate/system/userlogin"  title="登陆" id=login >登陆后可下载附件</a>
            	</c:if>
            	<a id="topicFile" name="topicFile" href="${topic.filepath }" download="${topic.filename }" style="display:none">${topic.filename }</a>
            </li>
            </c:if>
    </ul>
</div>

<c:if test="${!empty replies}">
<div class="panel panel-default" id="main" style="">
    <div class="panel-heading" style="background-color: #C6E2FF">
        <span>
                ${fn:length(replies)} 回复  |  直到 <c:forEach items="${replies}" var="reply" varStatus="status">

<c:if test="${status.last}">
    ${reply.localCreateTime}
    </c:if>
    </c:forEach>
    </span>
    </div>
	<!-- 遍历评论-->
    <ul id="reply_list" class="list-group" style="width: 100%">
        <c:forEach items="${replies}" var="reply">
        <li class="list-group-item" style="background-color: #E0FFFF">
            <div style="height: 50px">
                <%-- <div style="float: left;width: 6%;margin-bottom: 5px">
                    <img width="50px" height="50px" src="${reply.user.photo} " class="img-rounded">
                </div> --%>
                <div style="width: 89%;float: left">
                    <a href="/member/${reply.user.id}"><strong>${reply.user.nickname}</strong></a>&nbsp;&nbsp;
                    <small class="text-muted">${reply.localCreateTime}</small>
                    <br/>
                    <div>
                        <p>${reply.content}</p>
                    </div>
                </div>
            </div>
        </li>
        </c:forEach>
    </ul>
      
</div>
</c:if>
<c:if test="${empty user}">
<h3><a href="/XtuGraduate/system/userlogin"  title="登陆" id=login >登陆</a>后可回复</h3>
</c:if>
<c:if test="${!empty user}">

<div class="panel panel-default" id="main" style="">
    <div class="panel-heading" style="background-color: #E0FFFF">
        添加一条新回复
    </div>
    <div class="panel-body" style="background-color: #F5FFFA">
        <div class="form-group">
            <form id="reply-form" name="reply-form" action="../reply/add" method="post">
                <input type="hidden" name="topicId" value="${topic.id}">
                <input type="hidden" name="replyUserId" value="${user.id}">
                <textarea id="reply-textarea" class="form-control" rows="3" name="content" required="required"></textarea><br/>
            	<!-- <button type="button" id="reply-submit" class="btn btn-default btn-sm">回复</button> -->
            	<input type="submit" id="reply-submit" class="btn btn-default btn-sm" value="回复">
            </form>
        </div>

    </div>
</div>
</c:if>

</div>
<!-- General JS Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/tooltip.js@1.3.2/dist/umd/tooltip.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery.nicescroll@3.7.6/jquery.nicescroll.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.18.1/min/moment.min.js"></script>

<!-- JS Libraies -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.25.6/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"></script>


<script type="text/javascript">

var page = 1;

$(document).ready(function(){
	$("body").addClass('single');
	
	//异步加载评论内容
	$.ajax({
		url:'../reply/get_reply_list',
		type:'post',
		data:{rows:10,page:page++,topicId:'${topic.id}'},
		dataType:'json',
		success:function(data){
			if(data.type == 'success'){
				var replyList = data.replyList;
				var html = '';
				for(var i=0;i<replyList.length;i++){
					var li = '<li class="list-group-item">';
					li += '<div style="height: 50px">';
		            li += '<div style="float: left;width: 6%;margin-bottom: 5px">';
	                li += '<img width="50px" height="50px" src="'+${reply.user.photo}+'" class="img-rounded"></div>'
	                li += '<div style="width: 89%;float: left">';
	                li += '<a href="/member/'+${reply.user.username}+'"><strong>'+${reply.user.username}+'</strong></a>&nbsp;&nbsp;';
	                li += '<small class="text-muted">'+${reply.localCreateTime}+'</small><br/>';
	                li += '<div><p>'+${reply.content}+'</p></div>';    
	                li += '</div></div></li>';
	                html += li;
				}
				$("#reply_list").append(html);
			}
		}
	});
})
</script>
<script type="text/javascript">
function downfile(){
	var UserId = $("#UserId").val();
	var TopicUserId = $("#TopicUserId").val();
	$.ajax({
		url:'../user/download',
		type:'post',
		data:{UserId:UserId,TopicUserId:TopicUserId},
		dataType:'json',
		async : false,
		success:function(data){
			if(data.type == 'success'){
				$("#topicFile").html('<span></span>').children().trigger('click');
				swal({
		              type: 'success',
		              showCloseButton: true,
		              text: "下载成功！积分扣除："+data.msg
		            })
			}else{
				swal({
		              type: 'error',
		              showCloseButton: true,
		              text: data.msg
		            })
			}
		}
	});
}
</script>
