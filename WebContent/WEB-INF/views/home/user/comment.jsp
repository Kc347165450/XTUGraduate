<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">

		<!-- General CSS Files -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.8.2/css/all.min.css">

		<!-- Template CSS -->
		<link rel="stylesheet" href="../resources/user/css/style-blue.css">
		<link rel="stylesheet" href="../resources/user/css/components.css">

		<!-- Custom CSS -->
		<link rel="stylesheet" href="../resources/user/css/malio.css">
		<title>评论管理</title>
		<style>
			.table-links a {
				font-weight: normal;
			}
		</style>
	</head>

	<body>
		<div id="app">
			<div class="main-wrapper">
				<div class="navbar-bg"></div>
				<nav class="navbar navbar-expand-lg main-navbar">
					<form class="form-inline mr-auto">
						<ul class="navbar-nav mr-3">
						</ul>
					</form>
					<ul class="navbar-nav navbar-right">
						<li class="dropdown"><a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg nav-link-user">
								<div class="d-sm-none d-lg-inline-block">Hi, <span class="__cf_email__">${user.nickname }</span></div>
							</a>
							<div class="dropdown-menu dropdown-menu-right">
								<a href="../user/info" class="dropdown-item has-icon">
									<i class="fas fa-user"></i> 我的账号
								</a>
								<div class="dropdown-divider"></div>
								<a href="/XtuGraduate/index/userlogout" class="dropdown-item has-icon text-danger">
									<i class="fas fa-sign-out-alt"></i> 退出登录
								</a>
							</div>
						</li>
					</ul>
				</nav>
				<div class="main-sidebar sidebar-style-2">
					<aside id="sidebar-wrapper">
						<div class="sidebar-brand">
							<a href="/XtuGraduate/index/index">湘大研讯</a>
						</div>
						<div class="sidebar-brand sidebar-brand-sm">
							<a href="/XtuGraduate/index/index">XTU</a>
						</div>
						<ul class="sidebar-menu">
							<li class="menu-header">页面</li>
							<li><a class="nav-link" href="/XtuGraduate/index/index"><i class="fas fa-home"></i> <span>研讯主页</span></a></li>
							<li class="menu-header">我的</li>
							<li><a class="nav-link" href="../user/info"><i class="fab fa-fort-awesome"></i> <span>用户中心</span></a></li>
							<li><a class="nav-link" href="../user/topic"><i class="fas fa-book"></i> <span>帖子管理</span></a></li>
							<li><a class="nav-link" href="../user/comment"><i class="fas fa-fire" ></i> <span>新闻评论管理</span></a></li>
							<li class="menu-header">设置</li>
							<li>
								<a href="../user/update" class="nav-link "><i class="fas fa-user"></i> <span>个人信息设置</span></a>
							</li>
						</ul>
					</aside>
				</div>
				<!-- Main Content -->
				<div class="main-content">
					<section class="section">
						<div class="section-header">
							<h1>我的评论</h1>
						</div>
						<div class="section-body">
							<div class="row">
								<div class="col-12">
									<div class="card">
										<div class="card-header">
											<h4>评论列表 </h4>
										</div>
										<div class="card-body">

											<div class="table-responsive">
												<table class="table table-striped">
													<tbody>
														<tr>
															<th>评论内容</th>
															<th>评论时间</th>
															<th>操作</th>
														</tr>
														<c:forEach items="${commentList }" var="comment">
														<tr>
															<td>
																<a href="../news/detail?id=${comment.newsId }" style="color:#6a757e">${comment.content }</a>
															</td>
															<td><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
															<td>
																<a href="javascript:void(0)" data-id="${comment.id }" class="btn btn-primary">删除</a>
															</td>
														</tr>
														</c:forEach>
														
													</tbody>
												</table>
												<div align="center">
													<table><tbody>
														<tr>
															<td colspan="5"  aligh="center">
															<c:if test="${page>1 }">
																<a href="comment?page=1" class="btn btn-info">首页</a>
																<a href="comment?page=${page -1}" class="btn btn-info">上一页</a>
															</c:if>		
															
															<c:forEach var="i" begin="${page}" end="${totalPage }">
																<a href="comment?page=${i }">${i }</a>  
															</c:forEach>
															
															<c:if test="${page<totalPage }">
																<a href="comment?page=${page +1}" class="btn btn-info">下一页</a>
																<a href="comment?page=${totalPage}" class="btn btn-info">尾页</a>
															</c:if>	
															</td>
														</tr>
													</tbody></table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
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
	$(document).ready(function(){
		$(".btn-primary").click(function(){
			var $this = $(this);
			if(confirm("确定删除?")){
				$.ajax({
					url:'deleteComment',
					type:'POST',
					data:{id:$this.attr('data-id')},
					dataType:'json',
					async:false,
					success:function(data){
						if(data.type == 'success'){
							window.location.reload();
						}else{
							alert(data.msg);
						}
					}
				});
			}
		});
	});
	
	</script>
	</body>

</html>