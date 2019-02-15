<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>XXXXXX</title>

    <%@ include file="/common/css.jsp" %>
    <link href="/static/css/index.css" type="text/css" rel="stylesheet"></link>
</head>

<body class="easyui-layout">
    <div data-options="region:'north'" style="height:90px; overflow: hidden;">
        <div class="nav-top"></div>
    </div>
    <div data-options="region:'west'" style="width:160px;">
        <ul class="nav-left">
            <li class="menuOne" data-url="<c:url value='/sys/table/list.htm' />">
                <div>
                    <span>XXXXX</span>
                </div>
            </li>
        </ul>
    </div>
    <div data-options="region:'center'" style="padding:5px;">
        <div id="tabs" class="easyui-tabs" data-options="fit: true, border: false"></div>
    </div>
</body>

<%@ include file="/common/js.jsp" %>
<script >
    $(function () {
        $(".menuOne").click(function () {
            var url = $(this).data("url");
            $("#tabs").tabs("add", {
                title: "测试",
                content: "<iframe src="+url+" allowTransparency='true' style='border:0;width:100%;height:100%;display: block;' frameBorder='0'></iframe>",
                fit: true,
                border: false,
            });
        });
    });
</script>
</html>
