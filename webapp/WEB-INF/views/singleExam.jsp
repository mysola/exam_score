<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>单次考试</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

</head>
<body style="margin:1px;">

<div id="tb">
    <div>
        <div>
            &nbsp;选择考试：&nbsp;<input id="curExamList" class="easyui-combobox" data-options="
                                    onSelect:curExamOnSelect,editable:false,
                                    valueField:'id',textField:'text',width:110">&nbsp;
            &nbsp;选择需要对比的考试：&nbsp;<input id="preExamList" class="easyui-combobox" data-options="
                                    onSelect:preExamOnSelect,editable:false,
                                    valueField:'id',textField:'text',width:110">&nbsp;

            <a
                href="javascript:openModifyDialog()"
                class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改分数</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考试时间：
            <span id="examDate"></span>
        </div>
        <div id="localSelect" style="margin-top: 8px">
            &nbsp;姓名查找：&nbsp;<input id="nameSearch" class="easyui-searchbox" data-options="
                                prompt:'请输入姓名',searcher:doSearch" style="width:100px">&nbsp;

            &nbsp;分数段：&nbsp; <input id="scoreMin" class="easyui-numberbox" value="0"
                                    precision="1" min="0" max="100" style="width:50px"/>

            &nbsp;至&nbsp;
                                <input id="scoreMax" class="easyui-numberbox" value="100"
                                precision="1" min="0" max="100" style="width:50px"/>

            <a
                    href="javascript:doSearch()"
                    class="easyui-linkbutton" iconCls="icon-edit" plain="true">筛选</a>
            &nbsp;选择排序方式：&nbsp;<select id="rankCol" class="easyui-combobox" data-options="
                                    onSelect:doSearch,editable:false,
                                    valueField:'id',textField:'text',width:80">
                                        <option value="1">按分数</option>
                                        <option value="0">按编号</option>
                                    </select>&nbsp;
        </div>
    </div>
</div>

<table id="dg" style="width: 800px">
    <thead data-options="frozen:true">
    <tr>
        <th data-options="field:'cb',checkbox:'true',align:'center'"></th>

        <th data-options="field:'scoreVary'" hidden=""></th>
        <th data-options="field:'rankVary'" hidden=""></th>

        <th data-options="field:'stuNum',width:100,align:'center'">编号</th>
        <th data-options="field:'stuName',width:100,align:'center'">姓名</th>

        <th data-options="field:'stuScore',width:100,align:'center'" formatter='formatScore'>分数</th>
        <th data-options="field:'stuRank',width:100,align:'center'" formatter='formatRank'>排名</th>

    </tr>
    </thead>
</table>
<div id="dlg" class="easyui-dialog"
     style="width: 300px;height:250px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#dlg-buttons">
    <form id="fm">
        <table cellspacing="8px">
            <tr>
                <td>编号：</td>
                <td>
                    <input id="stuNum" name="stuNum" class="easyui-textbox" disabled="disabled"/>
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td>
                    <input id="stuName" name="stuName" class="easyui-textbox" disabled="disabled"/>
                </td>
            </tr>
            <tr>
                <td>分数：</td>
                <td>
                    <input id="stuScore" name="stuScore" class="easyui-numberbox"
                        min="0" max="100" precision="1" required="true"/>
                </td>
            </tr>
            <tr>
                <td>排名：</td>
                <td>
                    <input id="stuRank" name="stuRank" class="easyui-textbox" disabled="disabled"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<script type="text/javascript">
    var upImagPath =
        '<img style="width:20px; height:15px" src="${pageContext.request.contextPath}/images/up.png">';
    var downImagPath =
        '<img style="width:20px; height:15px" src="${pageContext.request.contextPath}/images/down.png">';
    var tableCurExamId = undefined;
    var tablePreExamId = undefined;
    initTable();
    initExamList();
    //初始化考试选择列表
    function initExamList() {
        $.ajax({
            url: "${pageContext.request.contextPath}/exams",
            method: "get",
            success: function(data) {
                var combox = [];
                $.each(data.rows,function(index,value){
                    combox.push({ "text": value.examName, "id": value.id ,"examDate":value.examDate});
                });
                $("#curExamList").combobox("loadData", combox);
                $("#curExamList").combobox("select", combox[combox.length - 1].id);
                combox.push({ "text": "未选择", "id": 0 });
                $("#preExamList").combobox("loadData", combox);
                $("#preExamList").combobox("select", combox[combox.length - 1].id);
            },
            error: function (data) {
                showErrorInf(data);
            }
        });
    }

    function initTable() {
        $('#dg').datagrid({
            pagination:"true",
            rownumbers:"true",
            method:"get",
            toolbar:"#tb",
            pageList:[15,20],
            pageSize:15,
            loadFilter:pagerFilter
        });
        $('#dg').datagrid("getPager").pagination("options").showRefresh = false;
    }

    function initLocalSelectDiv(){
        $('#nameSearch').searchbox('setValue','');
        $('#scoreMin').numberbox('reset');
        $('#scoreMax').numberbox('reset');
        $('#rankCol').combobox('select','1');
    }

    function loadTable(curExamId,preExamId) {
        if(curExamId.length==0||preExamId.length==0||rankCol.length==0)
            return;
        //加载远程数据
        isLoadLocal = false;
        $('#dg').datagrid("options").url = "${pageContext.request.contextPath}/exams/"+curExamId+"/scores";
        var queryParams={};
        if(preExamId!=0&&preExamId!=curExamId){
            queryParams.lastId = preExamId;
        }
        $('#dg').datagrid('load',queryParams);
        tableCurExamId = curExamId;
        tablePreExamId = preExamId;
        initLocalSelectDiv();
    }

    function formatScore(value,row,index) {
        if(row.stuScore==0){
            //当前考试缺考
            value = '缺考';
        }
        else{
            value = '<span style="width: 30px;display: inline-block">'+value+'</span>';
            if(tablePreExamId!=0&&tablePreExamId!=tableCurExamId){
                //对比的考试不为空
                if(row.scoreVary==row.stuScore)
                    value+='(上次缺考)';
                else {
                    if(row.scoreVary>=10)
                        value+=upImagPath+'<sup size="1">'+row.scoreVary+'</sup>';
                    if(row.scoreVary<=-10){
                        var scoreVaryPlus = 0-row.scoreVary;
                        value+=downImagPath+'<sup size="1">'+scoreVaryPlus+'</sup>';
                    }
                }
            }
        }
        return value;
    }

    function formatRank(value,row,index) {
        if(row.stuScore==0){
            //当前考试缺考
            value = '缺考';
        }
        else {
            value = '<span style="width: 30px;display: inline-block">' + value + '</span>';
            if (tablePreExamId != 0 && tablePreExamId != tableCurExamId) {
                //对比的考试不为空
                if (row.scoreVary == row.stuScore)
                    value += '(上次缺考)';
                else {
                    if (row.rankVary >= 10)
                        value += downImagPath + '<sup size="1">' + row.rankVary + '</sup>';
                    if (row.rankVary <= -10) {
                        var rankVaryPlus = 0 - row.rankVary;
                        value += upImagPath + '<sup size="1">' + rankVaryPlus + '</sup>';
                    }
                }
            }
        }
        return value;
    }

    function curExamOnSelect(record) {
        var preExamId = $('#preExamList').combobox('getValue');
        $('#examDate').text(record.examDate);
        loadTable(record.id,preExamId);
    }

    function preExamOnSelect(record) {
        var curExamId = $('#curExamList').combobox('getValue');
        loadTable(curExamId,record.id);
    }

    function save() {
        var data={
            examId:tableCurExamId,
            stuNum:$("#stuNum").val(),
            chineseScore:$("#stuScore").numberbox('getValue')
        };
        $.ajax({
            type: 'put',//方法类型
            url:"${pageContext.request.contextPath}/scores",
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
                closeDialog();
                loadTable(tableCurExamId,tablePreExamId);
            },
            error: function (data) {
                showErrorInf(data);
            },
            beforeSend: function(xhr) {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                xhr.setRequestHeader(header, token);
            }

        });
    }
    function openModifyDialog() {
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        var row = selectedRows[0];
        $("#dlg").dialog("open").dialog("setTitle", "修改分数");
        $('#fm').form('load', row);
    }
    function closeDialog() {
        $("#dlg").dialog("close");
        $('#fm').form("reset");
    }
    function doSearch() {
        isLoadLocal = true;
        isResetPageNum = true;
        $('#dg').datagrid('loadData',$('#dg').datagrid('getData'));
    }
    var isLoadLocal = false;
    var isResetPageNum = true;
    function pagerFilter(data)
    {
        var dg = $(this);
        var opts = dg.datagrid('options');
        var pager = dg.datagrid('getPager');
        if(isResetPageNum)
            pager.pagination('refresh',{	// 客户端筛选时条件变更，重置页码为1
                pageNumber: 1
            });
        pager.pagination({
            onSelectPage:function(pageNum, pageSize){
                opts.pageNumber = pageNum;
                opts.pageSize = pageSize;
//                pager.pagination('refresh',{
//                    pageNumber:pageNum,
//                    pageSize:pageSize
//                });
                //加载本地数据
                isLoadLocal = true;
                isResetPageNum = false;
                //翻页时触发
                dg.datagrid('loadData',data);
            }
        });
        if(!data.originalRows){
            data.originalRows =(data.rows);
        }
        var start =(opts.pageNumber-1)*parseInt(opts.pageSize);
        var end = start + parseInt(opts.pageSize);
        var temp = [];
        if(isLoadLocal){
            var stuName = $('#nameSearch').searchbox('getValue');
            var scoreMin = $('#scoreMin').numberbox('getValue');
            var scoreMax = $('#scoreMax').numberbox('getValue');
            var rankCol = $('#rankCol').combobox('getValue');
            //客户端筛选
            $.each(data.originalRows,function(index,value){
                if(isValid(stuName,scoreMin,scoreMax,value))
                    temp.push(value);
            });
            //客户端排序
            if(rankCol=="0")
                temp.sort(scoreSortByNum);
            if(rankCol=="1")
                temp.sort(scoreSortByScore);
            data.rows = temp.slice(start,end);
            data.total = temp.length;
        }
        else {
            data.rows =(data.originalRows.slice(start, end));
            data.total = data.originalRows.length;
        }
        return data;
    }
    function scoreSortByNum(a,b) {
        return a.stuNum - b.stuNum;
    }
    function scoreSortByScore(a,b) {
        return b.stuScore - a.stuScore;
    }
    function isValid(stuName,scoreMin,scoreMax,data) {
        if(stuName.length!=0&&data.stuName.indexOf(stuName)==-1)
            return false;
        if(scoreMin.length!=0&&scoreMin>0&&data.stuScore<scoreMin)
            return false;
        if(scoreMax.length!=0&&scoreMax<100&&data.stuScore>scoreMax)
            return false;
        return true;
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