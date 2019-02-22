<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>

    <%@ include file="/common/css.jsp" %>
</head>

<body class="easyui-layout" data-options="fit:true, border:false">
    <div data-options="region:'center'" data-options="fit:true, border:false">
        <table id="grid" data-options="fit: true, border: false"></table>
    </div>
</body>

<%@ include file="/common/js.jsp" %>
<script >
    $(function() {
        var generateUrl = "<c:url value='/sys/table/generate.json' />";

        $('#grid').datagrid({
            url: basepath + "/sys/table/listpage.json",
            pagination: true,
            selectOnCheck: false,
            checkOnSelect: false,
            singleSelect: true,
            fitColumns: true,
            columns:[[
                {field: 'name',title: '名称',width: 150},
                {field: 'createtime',title: '建表时间',width: 100},
                {field: 'updatetime',title: '表更新时间',width: 100},
                {field: 'comment',title: '备注',width: 200},
                {field: 'opt',title: '操作',width: 80,
                    formatter: function (value, row, index) {
                        return "<div class='edit' style='cursor: pointer;' data-name='"+row.name+"' data-comment='"+row.comment+"'><span>编辑</span></div>";
                    }
                },
            ]],
        });

        $("body").on("click", ".edit", function () {
            var tablename = $(this).data("name");
            var comment = $(this).data("comment");
            $.messager.confirm("提示", "是否对此表生成实体类？", function (r) {
                if (r) {
                    $.post(generateUrl, {tablename: tablename, comment: comment}, function (result) {
                        $.messager.show({
                            title: "提示",
                            msg: result.msg,
                            timeout: 1000,
                        });
                    });
                }
            });
        });
    });
</script>
</html>
