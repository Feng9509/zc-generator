<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>

    <%@ include file="/common/css.jsp" %>
</head>

<body class="easyui-layout" data-options="fit:true, border:false">
    <div id="toolbar">
        <a href="javascript: void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true"><span>确认</span></a>
        <a href="javascript: void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"><span>取消</span></a>
    </div>

    <div data-options="region:'center'" data-options="fit:true, border:false">
        <table id="grid" data-options="fit: true, border: false"></table>
    </div>
</body>

<%@ include file="/common/js.jsp" %>
<script >
    $(function() {
        var editUrl = "<c:url value='/sys/table/edit.htm' />";

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
                        return "<div class='edit' style='cursor: pointer;' data-name='"+row.name+"'><span>编辑</span></div>";
                    }
                },
            ]],
        });

        $("body").on("click", ".edit", function () {
            $("<div/>").dialog({
                title: "信息填写",
                width: 600,
                height: 400,
                content: "<iframe src="+editUrl+" allowTransparency='true' style='border:0;width:100%;height:100%;display: block;' frameBorder='0'></iframe>",
                modal: true
            });
        })
    });
</script>
</html>
