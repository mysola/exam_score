<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>全部考试</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

</head>
<body style="margin:1px;">
<div id="tb">
    <div>
        <a href="javascript:openStuDialog()"
                class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改学生姓名</a>
        <a href="javascript:openStuFileDlg();"
           class="easyui-linkbutton" iconCls="icon-add" plain="true">导入学生名单</a>
        <a href="javascript:openExamDlg();"
           class="easyui-linkbutton" iconCls="icon-add" plain="true">新建考试</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>选择并删除考试:</span>
        <input id="examList" class="easyui-combobox" data-options="
                                    editable:false,valueField:'id',textField:'text',width:110">
        <a href="javascript:deleteExam()"
           class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除考试</a>
        <a href="javascript:deleteAll()"
           class="easyui-linkbutton" iconCls="icon-remove" plain="true">清除所有数据</a>
    </div>
</div>
<table id="dg" >
    <!--将下面两列永远冻结在最左边-->
    <thead data-options="frozen:true">
    <tr>
        <th data-options="field:'cb',checkbox:'true',align:'center'"></th>
        <th data-options="field:'stuNum',width:60,align:'center'">编号</th>
        <th data-options="field:'stuName',width:60,align:'center'">姓名</th>
    </tr>
    </thead>
</table>

<div id="stuDlg" class="easyui-dialog"
     style="width: 300px;height:200px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#stuDlg-buttons">
    <form id="stuFm">
        <table cellspacing="8px">
            <tr>
                <td>编号：</td>
                <td>
                    <input id="stuNum" name="stuNum" class="easyui-textbox" data-options="
                        required:true" disabled="disabled"/>
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td>
                    <input id="stuName" name="stuName" class="easyui-textbox" data-options="
                        required:true"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="stuDlg-buttons">
    <a href="javascript:saveStu();" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeStuDialog();" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

<div id="examDlg" class="easyui-dialog"
     style="width: 300px;height:300px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#examDlg-buttons">
    <form id="examFm" method="post" action="${pageContext.request.contextPath}/exams" enctype="multipart/form-data">
        <div style="margin-bottom:20px">
            <div>选择考试文件:</div>
            <input class="easyui-filebox" name="examFile" id ="examFile" data-options="
            prompt:'选择文件...',buttonText:'选择考试文件',width:'100%',required:true,
            accept:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' "/>
        </div>
        <div style="margin-bottom:20px">
            <div>输入文件中本次考试所在的列的序号:</div>
            <input id="examIndexInFile" name="examIndexInFile" class="easyui-numberbox"
                   min="3" max="10" precision="0" required="true"/>
        </div>
        <div style="margin-bottom:20px">
            <div>选择考试时间:</div>
            <input id="examDate" name="examDate" class="easyui-datebox" data-options="
            width:'100%',required:true"/>
        </div>
    </form>
</div>
<div id="examDlg-buttons">
    <a href="javascript:saveExamFile();" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
    <a href="javascript:closeExamDlg();" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

<div id="stuFileDlg" class="easyui-dialog"
     style="width: 300px;height:150px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#stuFileDlg-buttons">
    <form id="stuFileFm" method="post" action="${pageContext.request.contextPath}/students"
          enctype="multipart/form-data">
        <div style="margin-bottom:20px">
            <div>选择学生名单文件:</div>
            <input class="easyui-filebox" name="stuFile" id ="stuFile" data-options="
            prompt:'选择文件...',buttonText:'选择学生名单文件',width:'100%',required:true,
            accept:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' "/>
        </div>
    </form>
</div>
<div id="stuFileDlg-buttons">
    <a href="javascript:saveStuFile();" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
    <a href="javascript:closeStuFileDlg();" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<script type="text/javascript">
    var upImagPath =
        '<img style="width:20px; height:15px" src="${pageContext.request.contextPath}/images/up.png">';
    var downImagPath =
        '<img style="width:20px; height:15px" src="${pageContext.request.contextPath}/images/down.png">';
    initTable();
    initExamList();
    initExamDlg();
    function initTable() {
        var colums=[[],[]];
        $.ajax({
            url: "${pageContext.request.contextPath}/exams",
            method: "get",
            success: function(data) {
                $.each(data.rows,function(index,value){
                    var exam = {};
                    exam.title = value.examName+' ('+value.examDate+')';
                    exam.colspan = 2;
                    colums[0].push(exam);

                    var score = {};
                    score.title = '分数';
                    score.field = 'e'+index+'Score';
                    score.width = 90;

                    var rank = {};
                    rank.title = '排名';
                    rank.field = 'e'+index+'Rank';
                    rank.width = 90;

                    var scoreVary = {};
                    scoreVary.hidden = true;
                    scoreVary.field = 'e'+index+'ScoreVary';

                    var rankVary = {};
                    rankVary.hidden = true;
                    rankVary.field = 'e'+index+'RankVary';

                    colums[1].push(score);
                    colums[1].push(rank);

                    score.formatter=function (value,row,index) {
                        if(row[score.field]==0){
                            //当前考试缺考
                            value = '缺考';
                        }
                        else{
                            value = '<span style="width: 30px;display: inline-block">'+value+'</span>';
                            if(row[scoreVary.field]!=null){
                                //对比的考试不为空
                                if(row[scoreVary.field]==row[score.field])
                                    value+='(上次缺考)';
                                else {
                                    if(row[scoreVary.field]>=10)
                                        value+=upImagPath+'<sup size="1">'+row[scoreVary.field]+'</sup>';
                                    if(row[scoreVary.field]<=-10){
                                        var scoreVaryPlus = 0-row[scoreVary.field];
                                        value+=downImagPath+'<sup size="1">'+scoreVaryPlus+'</sup>';
                                    }
                                }
                            }
                        }
                        return value;
                    };
                    rank.formatter=function (value,row,index) {
                        if(row[score.field]==0){
                            //当前考试缺考
                            value = '缺考';
                        }
                        else{
                            value = '<span style="width: 30px;display: inline-block">'+value+'</span>';
                            if(row[scoreVary.field]!=null){
                                //对比的考试不为空
                                if(row[scoreVary.field]==row[score.field])
                                    return value+'(上次缺考)';
                                else {
                                    if(row[rankVary.field]>=10)
                                        value+=downImagPath+'<sup size="1">'+row[rankVary.field]+'</sup>';
                                    if(row[rankVary.field]<=-10){
                                        var rankVaryPlus = 0-row[rankVary.field];
                                        value+=upImagPath+'<sup size="1">'+rankVaryPlus+'</sup>';
                                    }
                                }
                            }
                        }
                        return value;
                    };

                });
                $.each(data.rows,function(index,value){
                    var scoreVary = {};
                    scoreVary.hidden = true;
                    scoreVary.field = 'e'+index+'ScoreVary';

                    var rankVary = {};
                    rankVary.hidden = true;
                    rankVary.field = 'e'+index+'RankVary';

                    colums[1].push(scoreVary);
                    colums[1].push(rankVary);
                });
                $('#dg').datagrid({
                    url:"${pageContext.request.contextPath}/exams/scores",
                    pagination:"true",
                    rownumbers:"true",
                    method:"get",
                    toolbar:"#tb",
                    columns:colums,
                    pageList:[15,20],
                    pageSize:15,
                    loadFilter:pagerFilter
                });
                $('#dg').datagrid("getPager").pagination("options").showRefresh = false;
            },
            error: function (data) {
                showErrorInf(data);
            }
        });
    }

    function initExamList() {
        $.ajax({
            url: "${pageContext.request.contextPath}/exams",
            method: "get",
            success: function(data) {
                var combox = [];
                $.each(data.rows,function(index,value){
                    combox.push({ "text": value.examName, "id": value.id });
                });
                $("#examList").combobox("clear");
                if(combox.length!=0){
                    $("#examList").combobox("loadData", combox);
                    $("#examList").combobox("select", combox[combox.length - 1].id);
                }
            },
            error: function (data) {
                showErrorInf(data);
            }
        });
    }

    function initExamDlg(){
        $('#examDate').datebox().datebox('calendar').calendar({
            validator: function(date){
                var now = new Date();
                var d = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                return date<=d;
            }
        });
    }

    function saveExamFile() {
        $("#examFm").ajaxSubmit({
            success:function(){
                $.messager.show({
                    title:'系统提示',
                    msg:'上传成功',
                    showType:'show',
                    timeout:3000,
                    style:{
                        left:'',
                        right:0,
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                closeExamDlg();
                initExamList();
                initTable();
            },
            error:function (data) {
                showErrorInf(data);
            }
        });
    }

    function saveStuFile() {
        $("#stuFileFm").ajaxSubmit({
            success:function(){
                $.messager.show({
                    title:'系统提示',
                    msg:'上传成功',
                    showType:'show',
                    timeout:3000,
                    style:{
                        left:'',
                        right:0,
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                closeStuFileDlg();
            },
            error:function (data) {
                showErrorInf(data);
            }
        });
    }

    function openExamDlg() {
        $('#examDlg').dialog('open').dialog('setTitle', '上传考试成绩');
    }
    function openStuFileDlg() {
        $('#stuFileDlg').dialog('open').dialog('setTitle', '导入学生名单');
    }

    function closeExamDlg() {
        $('#examDlg').dialog('close');
        $('#examFile').filebox('reset');
        $('#examDate').datebox('reset');
        $('#examIndexInFile').numberbox('reset');
    }
    function closeStuFileDlg() {
        $('#stuFileDlg').dialog('close');
        $('#stuFile').filebox('reset');
    }

    function deleteExam() {
        var examId = $('#examList').combobox('getValue');
        var examName = $('#examList').combobox('getText');
        if(examId.length==0){
            $.messager.alert("系统提示", "请选择考试！");
            return;
        }
        $.messager.confirm('系统提示', '确定删除'+examName+'?', function(r){
            if (r){
                $.ajax({
                    type: 'delete',//方法类型
                    url:"${pageContext.request.contextPath}/exams/"+examId,
                    contentType: "application/json; charset=utf-8",
                    data: {},
                    success: function () {
                        $.messager.show({
                            title:'系统提示',
                            msg:'成功删除'+examName,
                            showType:'show',
                            timeout:3000,
                            style:{
                                left:'',
                                right:0,
                                top:document.body.scrollTop+document.documentElement.scrollTop,
                                bottom:''
                            }
                        });
                        initExamList();
                        initTable();
                    },
                    error: function (data) {
                        showErrorInf(data);
                    }
                });
            }
        });
    }

    function deleteAll() {
        $.messager.confirm('系统提示', '确定清除所有数据么?这包括所有考试信息以及学生信息', function(r){
            if (r){
                $.ajax({
                    type: 'delete',//方法类型
                    url:"${pageContext.request.contextPath}/students",
                    contentType: "application/json; charset=utf-8",
                    data: {},
                    success: function () {
                        $.messager.show({
                            title:'系统提示',
                            msg:'成功删除',
                            showType:'show',
                            timeout:3000,
                            style:{
                                left:'',
                                right:0,
                                top:document.body.scrollTop+document.documentElement.scrollTop,
                                bottom:''
                            }
                        });
                        initExamList();
                        initTable();
                    },
                    error: function (data) {
                        showErrorInf(data);
                    }
                });
            }
        });
    }

    function saveStu() {
        var data={
            stuNum:$("#stuNum").val().trim(),
            stuName:$("#stuName").val().trim()
        };
        $.ajax({
            type: 'put',//方法类型
            url:"${pageContext.request.contextPath}/students",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function () {
                $.messager.show({
                    title:'系统提示',
                    msg:'修改成功',
                    showType:'show',
                    timeout:3000,
                    style:{
                        left:'',
                        right:0,
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                closeStuDialog();
                $("#dg").datagrid('reload');
            },
            error: function (data) {
                showErrorInf(data);
            }
        });
    }
    function openStuDialog() {
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        var row = selectedRows[0];
        $("#stuDlg").dialog("open").dialog("setTitle", "修改学生姓名");
        $('#stuFm').form('load', row);
    }
    function closeStuDialog() {
        $("#stuDlg").dialog("close");
        $('#stuFm').form("reset");
    }
    function pagerFilter(data) {
        var dg = $(this);
        var opts = dg.datagrid('options');
        var pager = dg.datagrid('getPager');
        pager.pagination({
            onSelectPage:function(pageNum, pageSize){
                opts.pageNumber = pageNum;
                opts.pageSize = pageSize;
                pager.pagination('refresh',{
                    pageNumber:pageNum,
                    pageSize:pageSize
                });
                dg.datagrid('loadData',data);
            }
        });
        if(!data.originalRows){
            data.originalRows =(data.rows);
        }
        var start =(opts.pageNumber-1)*parseInt(opts.pageSize);
        var end = start + parseInt(opts.pageSize);
        data.rows =(data.originalRows.slice(start, end));
        return data;
    }

    function showErrorInf(data) {
        var errorInf ={
            responseText:data.responseText,
            status:data.status,
            statusText:data.statusText
        }
        $.messager.show({
            title:'系统提示',
            msg:'系统异常，请刷新后重试'+'<br/>'+'错误信息:'+JSON.stringify(errorInf),
            showType:'show',
            timeout:0,
            height:'auto',
            style:{
                left:'',
                right:0,
                top:document.body.scrollTop+document.documentElement.scrollTop,
                bottom:'',
                'word-break':'break-all'
            }
        });
    }

</script>
</body>
</html>