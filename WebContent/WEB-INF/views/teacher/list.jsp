<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>导师:</label><input id="search-teacher" class="wu-text" style="width:100px">
            <label>所属学院:</label>
            <select id="search-academy" class="easyui-combobox" panelHeight="200" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${academyList }" var="academy">
            		<option value="${academy.id }">${academy.academyName }</option>
            	</c:forEach>
            </select>
            <label>所属专业:</label>
            <select id="search-department" class="easyui-combobox" panelHeight="200" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${departmentList }" var="department">
            		<option value="${department.id }">${department.departmentName }</option>
            	</c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	
	
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelections');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
				var ids = '';
				for(var i=0;i<item.length;i++){
					ids += item[i].id + ',';
				}
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{ids:ids},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		window.location.href = 'add';
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		window.location.href = 'edit?id=' + item.id;
	}	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {teacherName:$("#search-teacher").val(),academyId:$("#search-academy").combobox('getValue'),departmentId:$("#search-department").combobox('getValue')};
		$('#data-datagrid').datagrid('reload',option);
	});
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'teacherName',title:'导师',width:40,formatter:function(value,row,index){
				return '<a href="../../teacher/detail?id='+row.id+'" target="_blank">' + value + '</a>';
			}},
			{ field:'academyName',title:'学院',width:100,formatter:function(value,row,index){
				return row.academy.academyName;
			}},
			{ field:'departmentName',title:'专业',width:100,formatter:function(value,row,index){
				return row.department.departmentName;
			}},
			{ field:'tel',title:'电话',width:80},
			{ field:'email',title:'邮箱',width:80},
			{ field:'commentNumber',title:'评论数',sortable:true,width:30},
		]],
	});
</script>