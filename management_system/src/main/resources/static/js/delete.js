function batchDelete() {
	var curWwwPath=window.document.location.href;
	//获取主机地址之后的目录
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	//获取地址到端口号
	var localhostPath=curWwwPath.substring(0,pos);
	//项目名
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var checkedNum =  $("input[name='ids2']:checked").length;
	if(checkedNum == 0){
	    alert("至少选择一项进行删除！");
	    return;
	}
	if(confirm("确定删除选中的用户吗？")){
	    var userList = new Array();
	    $("input[name='ids2']:checked").each(function(){
	        userList.push($(this).val());
		});
	}
	//提交
	$.ajax({
		type:"post",
		url:projectName+"/batchDelete.do",
		data:{userList: userList.toString()},
		success: function(){
		    alert("删除成功！");
		    location.reload();
        },
		error :function(){
		    alert("删除失败！")
        }
	});
}