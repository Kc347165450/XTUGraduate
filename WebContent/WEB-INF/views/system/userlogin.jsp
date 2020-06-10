<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0051)http://demo1.mycodes.net/denglu/HTML5_yonghudenglu/ -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>湘潭大学考研资讯网登录</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <!-- General CSS Files -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.8.2/css/all.min.css">

  <!-- Template CSS -->
  <link rel="stylesheet" href="../resources/admin/login/css/styles.css">
  <link rel="stylesheet" href="../resources/admin/login/css/components.css">
<body>

<div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="row">
          <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 col-lg-6 offset-lg-3 col-xl-4 offset-xl-4">

            <div class="card card-primary">
              <div class="card-header">
                <h4>登录</h4>
              </div>
              <form action="javascript:void(0);" method="POST" class="needs-validation" novalidate="">

                <div class="card-body">
                  <div class="form-group login-form-item">
                    <label for="username">用户名</label>
                    <input id="username" type="text" class="form-control" name="username" tabindex="1" required autofocus>
                    <div class="invalid-feedback">
                      	用户名在8~10位之间
                    </div>
                  </div>

                  <div class="form-group login-form-item">
                    <div class="d-block">
                      <label for="password" class="control-label">密码</label>
                      <div class="float-right">
                        <a href="resetPasswd" class="text-small">
                        	  忘记密码？
                        </a>
                      </div>
                    </div>
                    <input id="password" type="password" class="form-control" name="password" tabindex="2" required>
                    <div class="invalid-feedback">
                  	    密码必须在8~16位之间
                    </div>
                  </div>

                  <div id="2fa-form" class="form-group" style="display: block;">
                    <label for="cpacha">验证码</label>
                    <div class="input-group mb-3">
                    <input id="cpacha" type="text" class="form-control" style="width:60%;" name="cpacha" tabindex="1" maxlength="4" placeholder="请输入4位验证码" required>
	                <div class="input-group-append">
	                    <img id="cpacha-img" title="点击切换验证码" style="cursor:pointer;float:right;" src="get_cpacha?vl=4&w=150&h=40&type=loginCpacha" width="110px" height="42px" onclick="changeCpacha()">
	                </div>
                    </div>
                    <div class="invalid-feedback">
                     	 请输入4位验证码
                    </div>
                  </div>
                  
                  <div class="form-group login-form-item" style="display: none;">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" name="remember" class="custom-control-input" tabindex="3" id="remember-me">
                      <label class="custom-control-label" for="remember-me">七天内免登录</label>
                    </div>
                  </div>

                  <div class="form-group">
                    <button type="submit" id="login" class="btn btn-primary btn-lg btn-block login" tabindex="4">
                     	 登录
                    </button>
                  </div>
              </form>
              
            </div>
          </div>
          <div class="mt-5 text-muted text-center login-form-item">
            	还没有账号？ <a href="register">马上注册 👉</a>
          </div>
          <div class="simple-footer">
            Copyright &copy; 2019 湘大研讯
          </div>
        </div>
      </div>
  </div>
  </section>
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
	function hasClass(elem, cls) {
	  cls = cls || '';
	  if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
	  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
	}
	 
	function addClass(ele, cls) {
	  if (!hasClass(ele, cls)) {
	    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  }
	}
	 
	function removeClass(ele, cls) {
	  if (hasClass(ele, cls)) {
	    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      newClass = newClass.replace(' ' + cls + ' ', ' ');
	    }
	    ele.className = newClass.replace(/^\s+|\s+$/g, '');
	  }
	}
	function changeCpacha(){
		$("#cpacha-img").attr("src",'get_cpacha?vl=4&w=150&h=40&type=loginCpacha&t=' + new Date().getTime());
	}
	$(function () {
        var flagUsername=false;
        var flagPass=false;
        var flagCpa=false;
        
        var username,password,cpacha;
        /*验证用户名*/
        $("#username").change(function(){
        	username=$("#username").val();
            if(username.length<8 || username.length>16){
                $("#username").removeClass("form-control is-valid")
                $("#username").addClass("form-control is-invalid");
                flagUsername=false;
            }else{
                $("#username").removeClass("form-control is-invalid")
                $("#username").addClass("form-control is-valid");
                flagUsername=true;
            }
        })
        /*验证密码*/
        $("#password").change(function(){
        	password=$("#password").val();
            if(password.length<8||password.length>16){
                $("#password").removeClass("form-control is-valid")
                $("#password").addClass("form-control is-invalid");
                flagPass=false;
            }else{
                $("#password").removeClass("form-control is-invalid")
                $("#password").addClass("form-control is-valid");
                flagPass=true;
            }
        })
        /*验证码*/
        $("#cpacha").change(function(){
        	cpacha=$("#cpacha").val();
            if(cpacha.length != 4){
                $("#cpacha").removeClass("form-control is-valid")
                $("#cpacha").addClass("form-control is-invalid");
                flagCpa=false;
            }else{
                $("#cpacha").removeClass("form-control is-invalid")
                $("#cpacha").addClass("form-control is-valid");
                flagCpa=true;
            }
        })
        $("#login").click(function(){
        	var flag = false;
        	var username = $("#username").val();
			var password = $("#password").val();
			var cpacha = $("#cpacha").val();
            if(flagUsername&&flagPass&&flagCpa){
                flag = true;
            }else{
                if(!flagUsername){
                    $("#username").addClass("form-control is-invalid");
                }
                if(!flagPass){
                    $("#password").addClass("form-control is-invalid");
                }
                if(!flagCpa){
                    $("#cpacha").addClass("form-control is-invalid");
                }
                flag = false;
            }
            if(flag){
            	$.ajax({
					url:'userlogin',
					data:{username:username,password:password,cpacha:cpacha},
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.type == 'success'){
							window.parent.location = '../index/index';
						}else{
							swal({
					              type: 'error',
					              showCloseButton: true,
					              text: data.msg
					            })
							changeCpacha();
						}
					}
				});
            }
            else{
            	swal({
		              type: 'error',
		              showCloseButton: true,
		              text: "请检查输入"
		            })
				changeCpacha();
            }
        })
    })
</script>
</body></html>