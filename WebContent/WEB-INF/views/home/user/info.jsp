<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">

		<!-- General CSS Files -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.8.2/css/all.min.css">

		<!-- Template CSS -->
		<link rel="stylesheet" href="../resources/user/css/style-blue.css">
		<link rel="stylesheet" href="../resources/user/css/components.css">
		<link rel="stylesheet" type="text/css" href="../resources/user/css/mystyle.css"/>

		<!-- Custom CSS -->
		<link rel="stylesheet" href="../resources/user/css/malio.css">

		<title>用户中心 &mdash; 湘大研讯</title>

		<!-- C3 chart css -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/c3@0.6.8/c3.min.css">
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
								<div class="d-sm-none d-lg-inline-block">Hi, <span class="__cf_email__" >${user.nickname }</span></div>
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
							<h1>用户中心</h1>
						</div>
						<div class="row">
							<div class="col-12 col-md-7 col-lg-7">
								<div class="card">
									<div class="card-header">
										<h4><i class="fas fa-file-alt"></i> 我的帖子</h4>
									</div>
									<div class="card-body" style="color:#312f2f;">
									<table >
										<tbody>
										<c:forEach items="${topicList }" var="topic">
											<tr>
												<td>
													<a href="../topic/detail?id=${topic.id}" style="color:#6a757e">${topic.title }</a>
												</td>
											</tr>
										</c:forEach>	
										</tbody>
									</table>			
									</div>
								</div>

							</div>

							<div class="col-12 col-md-5 col-lg-5">
								<div class="card">
									<div class="card-header">
										<h4><i class="fas fa-user"></i> 个人信息</h4>
									</div>
									<div class="card-body">
										<p>邮箱：${user.email}</p>
										<p>年龄：${user.age}</p>
										<p>
										性别：
										<c:if test="${user.sex == 0 }">保密</c:if>
										<c:if test="${user.sex == 1 }">男</c:if>
										<c:if test="${user.sex == 2 }">女</c:if>
										</p>
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
	</body>