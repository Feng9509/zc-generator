<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>代码生成器</title>

    <%@ include file="/common/css.jsp" %>
    <link href="static/css/index.css" type="text/css" rel="stylesheet"></link>
</head>

<body class="easyui-layout">
    <div data-options="region:'north'" style="height:90px; overflow: hidden;">
        <div class="nav-top"></div>
    </div>
    <div data-options="region:'west'" style="width:160px;">
        <ul class="nav-left">
            <li>
                <div>
                    <span class="onename" data-url="<c:url value=''/>">代码生成</span>
                </div>
            </li>
        </ul>
    </div>
    <div data-options="region:'center'" style="padding:5px;">
        <div id="tabs" class="easyui-tabs" data-options="border: flase; fit: true;"></div>
    </div>
</body>

<%@ include file="/common/js.jsp" %>
<script >
    $(function() {
        $(".onename").each(function(index, data) {

        });
    });
</script>
</html>
