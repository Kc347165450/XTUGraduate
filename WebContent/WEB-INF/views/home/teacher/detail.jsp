<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.8.2/css/all.min.css">
<!-- Template CSS -->
<link rel="stylesheet" href="../resources/user/css/components.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="../resources/user/css/malio.css">
<section class="container">
<div class="content-wrap">
<div class="content">
  <header class="article-header">
	<h1 class="article-title"><a href="" title="${teacher.teacherName }" >${teacher.teacherName }</a></h1>
	<div class="article-meta"> 
	  	<span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="${teacher.tel }" data-original-title="${teacher.tel }">
	  		<i class="glyphicon glyphicon-phone-alt"></i> 
	  		<a href="" title="${teacher.tel}" >${teacher.tel}</a>
	  	</span> 
	  	<span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="${teacher.email }" data-original-title="${teacher.email }">
	  		<i class="glyphicon glyphicon-envelope"></i> 
	  		<a href="" title="${teacher.email}" >${teacher.email}</a>
	  	</span> 
	  	<span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom" title="${teacher.commentNumber }" data-original-title="评论量${teacher.commentNumber }">
	  		<i class="glyphicon glyphicon-comment"></i>${teacher.commentNumber } 
	  	</span> 
	 </div>
  </header>
  <article class="article-content">
  	${teacher.content }
  </article>
  <div class="title" id="comment">
  	<c:if test="${empty user}">
		<h3>请先<a href="/XtuGraduate/system/userlogin"  title="登陆" id=login >登陆</a></h3>
	</c:if>
	<c:if test="${!empty user}">
	<button type="button" tabindex="4" data-toggle="modal" data-target="#comment-modal" class="btn btn-primary" style="float:right">评价</button>
	</c:if>
  </div>
  <%-- 
  <div id="respond">
		<form id="comment-form" name="comment-form" action="" method="POST">
			<div class="comment">
				<input type="hidden" name="teacherId" value="${teacher.id }">
				<c:if test="${empty user}">
				<h3>请先<a href="/XtuGraduate/system/userlogin"  title="登陆" id=login >登陆</a></h3>
				</c:if>
				<c:if test="${!empty user}">
				<input name="nickname" id="nickname" class="form-control" size="22" maxlength="8" autocomplete="off" tabindex="1" type="text" placeholder="请填写您的匿名昵称(8字以内)">
				<div class="comment-box">
					<textarea placeholder="请对导师的学术水平、科研项目、为人等方面作出公正的评价" name="content" id="comment-textarea" cols="100%" rows="3" tabindex="3"></textarea>
					<div class="comment-ctrl">
						<div class="comment-prompt" style="display: none;"> <i class="fa fa-spin fa-circle-o-notch"></i> <span class="comment-prompt-text">评论正在提交中...请稍后</span> </div>
						<div class="comment-success" style="display: none;"> <i class="fa fa-check"></i> <span class="comment-prompt-text">评论提交成功...</span> </div>
						<button type="button" tabindex="4" data-toggle="modal" data-target="#comment-modal" class="btn btn-secondary">评论</button>
					</div>
				</div>
				</c:if>
			</div>
		</form>
		
	</div>
	 --%>
	<div id="postcomments" style="padding-bottom:0px;">
		<ol id="comment_list" class="commentlist">
		<c:forEach items="${commentList}" var="comment">
			<li class="comment-content">
				<div>
				<div style="float:left"><h4>匿名：${comment.nickname}</h4></div>
					<%-- <div style="float:right">
					<a><span class="glyphicon glyphicon-thumbs-up"></span>${comment.thumbsUp}</a>
					&nbsp;&nbsp;
					<a><span class="glyphicon glyphicon-thumbs-down"></span>${comment.thumbsDown}</a>
					</div> --%>
				</div>
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
			</li>
		</c:forEach>	        
		</ol>
  	</div>
  	<div class="ias_trigger" style="margin-top:0px;display:none;"><a href="javascript:;" id="load-more-comment-btn">查看更多</a></div>
</div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="comment-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">评价</h5>
					<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>匿名昵称</label>
						<input type="hidden" id="teacherId" name="teacherId" value="${teacher.id }">
						<input name="nickname" id="nickname" class="form-control" size="22" 
						maxlength="8" autocomplete="off" tabindex="1" type="text" placeholder="请填写您的匿名昵称(8字以内)" class="form-control" style="resize:none;">
					</div>
					<div class="form-group">
						<label>导师辨识特征</label>
						<textarea id="identify" name="identify" rows="2" class="form-control" 
						placeholder="证明您认识该导师，提供信息如办公室地点，口音，口头禅等等，他人可以此辨别您的真实性" style="resize:none;"></textarea>
					</div>
					<div class="form-group">
						<label>学术水平</label>
						<textarea id="ability" name="ability" rows="2" class="form-control" 
						placeholder="能否站在学术前沿，论文及工程成果如何" style="resize:none;"></textarea>
					</div>
					<div class="form-group">
						<label>科研经费</label>
						<textarea id="funds" name="funds" rows="2" class="form-control" 
						placeholder="经费、项目、支撑学生实验或调查" style="resize:none;"></textarea>
					</div>
					<div class="form-group">
						<label>师生关系</label>
						<textarea id="relationship" name="relationship" rows="2" class="form-control" 
						placeholder="实验室管理、尊重学生、论文排名、不安排杂活" style="resize:none;"></textarea>
					</div>
					<div class="form-group">
						<label>学生前途</label>
						<textarea id="future" name="future" rows="2" class="form-control" 
						placeholder="是否允许实习，毕业后在学术界或业界出路" style="resize:none;"></textarea>
					</div>
				</div>
				<div class="modal-footer bg-whitesmoke br">
					<button type="button" id="teacher-submit" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
<%@ include file="sidebar.jsp" %>
</section>
<%@ include file="../common/footer.jsp" %>
<!-- General JS Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/tooltip.js@1.3.2/dist/umd/tooltip.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery.nicescroll@3.7.6/jquery.nicescroll.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.18.1/min/moment.min.js"></script>

<!-- JS Libraies -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.25.6/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bowser@1.9.4/bowser.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/jquery-qrcode2@1.0.0/dist/jquery-qrcode.min.js"></script>
<script>
var page = 1;

$(document).ready(function(){
	$("body").addClass('single');
	//评论文章
	$("#teacher-submit").click(function(){
		var teacherId = $("#teacherId").val();
		var nickname = $("#nickname").val();
		var identify = $("#identify").val();
		var ability = $("#ability").val();
		var funds = $("#funds").val();
		var relationship = $("#relationship").val();
		var future = $("#future").val();
		if($("#nickname").val() == ''){
			swal({
	              type: 'error',
	              showCloseButton: true,
	              text: "必须填写昵称"
	            })
			return;
		}
		if($("#identify").val() == ''){
			swal({
	              type: 'error',
	              showCloseButton: true,
	              text: "导师辨识信息必须填写"
	            })
			return;
		}
		$.ajax({
			url:'../teacher/comment_teacher',
			type:'post',
			data:{teacherId:teacherId,nickname:nickname,identify:identify,ability:ability,funds:funds,relationship:relationship,future:future},
			dataType:'json',
			success:function(data){
				if(data.type == 'success'){
					swal({
			              type: 'success',
			              showCloseButton: true,
			              text: "评价成功！"
			            })
			            window.location.href = "../teacher/detail?id="+teacherId;
				}else{
					swal({
			              type: 'error',
			              showCloseButton: true,
			              text: "评价失败"
			            })
				}
			}
		});
	}); 
	//异步加载评论内容
	/* $.ajax({
		url:'../teacher/get_comment_list',
		type:'post',
		data:{rows:10,page:page++,newsId:'${news.id}'},
		dataType:'json',
		success:function(data){
			if(data.type == 'success'){
				var commentList = data.commentList;
				var html = '';
				for(var i=0;i<commentList.length;i++){
					var li = '<li class="comment-content"><span class="comment-f">#' + (commentList.length -i);
				    li += '</span><div class="comment-main"><p><a class="address" href="#" rel="nofollow" target="_blank">'+commentList[i].nickname+'</a><span class="time">('+format(commentList[i].createTime)+')</span><br>'+commentList[i].content+'</p></div></li></ol>';
					html += li;
				}
				$("#comment_list").append(html);
			}
		}
	}); */
	
	/* $("#load-more-comment-btn").click(function(){
		if($("#load-more-comment-btn").attr('data-key') == 'all')return;
		$("#load-more-comment-btn").text('查看更多评论');
		//异步加载评论内容
		$.ajax({
			url:'../teacher/get_comment_list',
			type:'post',
			data:{rows:10,page:page++,newsId:'${news.id}'},
			dataType:'json',
			success:function(data){
				if(data.type == 'success'){
					
					var commentList = data.commentList;
					$("#load-more-comment-btn").text('查看更多评论!');
					if(commentList.length == 0){
						$("#load-more-comment-btn").text('没有更多了!');
						$("#load-more-comment-btn").attr('data-key','all');
					}
					var html = '';
					for(var i=0;i<commentList.length;i++){
						var li = '<li class="comment-content"><span class="comment-f">#' + ($("#comment_list").children('li').length + i + 1);
					    li += '</span><div class="comment-main"><p><a class="address" href="#" rel="nofollow" target="_blank">'+commentList[i].nickname+'</a><span class="time">('+format(commentList[i].createTime)+')</span><br>'+commentList[i].content+'</p></div></li></ol>';
						html += li;
					}
					$("#comment_list").append(html);
				}
			}
		});
	}); */
});	
</script>