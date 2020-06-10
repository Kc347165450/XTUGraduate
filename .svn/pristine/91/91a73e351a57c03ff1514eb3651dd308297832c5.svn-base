<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../resources/forum/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../resources/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/wu.css" />
    <link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/icon.css" />
    <script src="../resources/forum/js/jquery-3.2.1.js"></script>
    <script src="../resources/forum/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
    
    <title>发帖 › 湘大论坛 </title>
</head>
<body>
<!-- 引入header文件include file="../common/header.jsp" -->
 
    <div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
    <div class="panel panel-default" id="main" style="">
        <div class="panel-heading" style="background-color: white">
            <a href="/">湘大论坛</a> › 发帖
        </div>

        <div class="panel-body">
            <form action="../topic/add" method="post" id="replyForm">
                <div class="form-group">
                    <label for="title">帖子标题</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="请输入主题标题，如果标题能够表达完整内容，则正文可以为空" required="required">
                </div>
                <div class="form-group">
                    <label for="content">正文</label>
                    <textarea class="form-control" rows="10" id="content" name="content"></textarea>
                </div>
                <div class="form-group">
                    <label for="content">上传文件</label>
                    <input type="text" id="filename" name="filename" readonly="readonly" class="wu-text " />
                    <input type="hidden" id="filepath" name="filepath" readonly="readonly" class="wu-text " />
                    <button type="button" onclick="uploadFile()">选择文件</button>
                </div>

                <div class="form-group">
                    <label for="tab">板块</label><br/>
                    <div class="col-sm-10" style="width: 40%">
                        <select class="form-control" id="tab" name="tab">
                            <c:forEach items="${tabList}" var="tab">
                            <option value="${tab.id}">${tab.tabName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div><br/>
                <input type="hidden" name="UserId" value="${user.id}">
                <input type="submit" class="btn btn-default btn-sm" value="发布帖子">

            </form>
        </div>

    </div>



</div>


    <div class="panel panel-default" id="sidebar2" style="width: 20%;margin:1% 2% 1% 0%;float: right">
        <div class="panel-heading" style="background-color: white;text-align: center">
            发帖提示
        </div>
        <ul class="list-group" style="width: 100%">
            <li class="list-group-item">
                <h5>标题</h5>
                <p>
                    请在标题中描述内容要点。如果一件事情在标题的长度内就已经可以说清楚，那就没有必要写正文了。
                </p>
            </li>

            <li class="list-group-item">
                <h5>正文</h5>
                <p>
                    请清楚地表达所要说明的内容。
                </p>
            </li>
        </ul>
    </div>


    <div class="panel panel-default" id="sidebar1" style="width: 20%;margin:1% 2% 1% 0%;float: right">
    <div class="panel-heading" style="background-color: white;text-align: center">
        社区指导原则
    </div>
    <ul class="list-group" style="width: 100%">
        <li class="list-group-item">
            <h5>文明发帖</h5>
            <p>
                请不要发布任何不文明的内容。
            </p>
        </li>

        <li class="list-group-item">
            <h5>友好互助</h5>
            <p>
                保持对陌生人的友善。用知识去帮助别人。
            </p>
        </li>
    </ul>
</div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传文件'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="user-file" style="display:none;" onchange="upload()">

<!-- 引入footer文件 include file="../../common/footer.jsp"-->
<script>
function start(){
	var value = $('#p').progressbar('getValue');
	if (value < 100){
		value += Math.floor(Math.random() * 10);
		$('#p').progressbar('setValue', value);
	}else{
		$('#p').progressbar('setValue',0)
	}
};
function upload(){
	if($("#user-file").val() == '')return;
	var formData = new FormData();
	formData.append('file',document.getElementById('user-file').files[0]);
	$("#process-dialog").dialog('open');
	var interval = setInterval(start,200);
	$.ajax({
		url:'../topic/upload_file',
		type:'post',
		data:formData,
		contentType:false,
		processData:false,
		success:function(data){
			clearInterval(interval);
			$("#process-dialog").dialog('close');
			if(data.type == 'success'){
				//$("#preview-photo").attr('src',data.filepath);
				$("#filename").val(data.filename);
				$("#filepath").val(data.filepath);
				//$("#edit-preview-photo").attr('src',data.filepath);
				//$("#edit-photo").val(data.filepath);
			}else{
				$.messager.alert("消息提醒",data.msg,"warning");
			}
		},
		error:function(data){
			clearInterval(interval);
			$("#process-dialog").dialog('close');
			$.messager.alert("消息提醒","上传失败!","warning");
		}
	});
}

function uploadFile(){
	$("#user-file").click();
	
}
    function submitValidate(flag){
        return flag;
    }
    $("#replyForm").submit(function () {
        if($("#title").val()==''){
            alert("请填写标题！");
            return submitValidate(false);
        }else {
            var ifSubmit=confirm("确定发表该帖子吗?");
            if (ifSubmit == true){

            }else {
                return submitValidate(false);
            }
        }
    })
</script>
</body>
</html>