<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>成绩管理系统</title>
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

<body class="easyui-layout">
<div region="north" style="height: 38px;background-color: #ffff">
    <table width="100%">
        <tr>
            <td width="50%"/>
        </tr>
    </table>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="考试纵览">
            <iframe frameborder=0 scrolling='auto' style='width:100%;height:100%'
                    src='${pageContext.request.contextPath}/allExam'>
            </iframe>
        </div>
    </div>
</div>
<div region="west" style="width: 180px;height:500px;" title="导航菜单" split="true">
    <div class="easyui-accordion">
        <div title="考试管理"
             data-options="selected:true,iconCls:'icon-exams'"
             style="padding: 10px;">
            <a
                    href="javascript:openTab('考试纵览','allExam','icon-exam')"
                    class="easyui-linkbutton"
                    data-options="plain:true,iconCls:'icon-exam'"
                    style="width: 150px;">考试纵览</a>
            <a
                    href="javascript:openTab('考试详情','singleExam','icon-exam')"
                    class="easyui-linkbutton"
                    data-options="plain:true,iconCls:'icon-exam'"
                    style="width: 150px;">考试详情</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    function addTab(url, text, iconCls) {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/"
            + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        });
    }

    function openTab(text, url, iconCls) {
        if ($("#tabs").tabs("exists", text)) {
            $("#tabs").tabs("close", text);
            addTab(url, text, iconCls);
            $("#tabs").tabs("select", text);
        } else {
            addTab(url, text, iconCls);
        }
    }
</script>
</body>
</html>