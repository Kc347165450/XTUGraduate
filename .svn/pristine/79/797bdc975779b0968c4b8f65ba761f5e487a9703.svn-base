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
            <label>帖子标题:</label><input id="search-title" class="wu-text" style="width:100px">
            <label>帖子作者:</label><input id="search-author" class="wu-text" style="width:100px">
            <label>所属分类:</label>
            <select id="search-tab" class="easyui-combobox" panelHeight="200" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${tabList }" var="tab">
            		<option value="${tab.id }">${tab.tabName }</option>
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
		var option = {title:$("#search-title").val(),tabId:$("#search-tab").combobox('getValue'),nickname:$("#search-author").val()};
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
			{ field:'title',title:'标题',width:300,formatter:function(value,row,index){
				return '<a href="../../topic/detail?id='+row.id+'" target="_blank">' + value + '</a>';
			}},
			{ field:'tabName',title:'分类',width:80,formatter:function(value,row,index){
				return row.tab.tabName;
			}},
			{ field:'nickname',title:'作者',width:80,formatter:function(value,row,index){
				return row.user.nickname;
			}}, 
		]],
	});
</script>