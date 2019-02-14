<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>

    <%@ include file="/common/css.jsp" %>
    <link href="static/css/index.css" type="text/css" rel="stylesheet"></link>
</head>


<div data-options="region:'center'" style="padding:5px;">
    <table id="grid"></table>
</div>
</body>

<%@ include file="/common/js.jsp" %>
<script >
    $(function() {
        alert(basepath + "sys/table/gettables.json");
        $('#grid').datagrid({
            url: basepath + "sys/table/gettables.json",
            columns:[[
                {field:'name',title:'名称',width:100},
                {field:'createtime',title:'createtime',width:100},
                {field:'updatetime',title:'updatetime',width:100}
            ]]
        });
    });
</script>
</html>
