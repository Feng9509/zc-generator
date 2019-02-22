

<#if param.dateflag>import java.util.Date;</#if>

/**
 * @Author 代码生成器
 * @Date ${.now}
 <#if param.comment != "">* @Description ${param.comment}</#if>
 */

public class ${param.classname} {
<#list param.columns as column>
    private ${column.javatype} ${column.property};  <#if column.comment != "">//  ${column.comment}</#if>
</#list>

<#list param.columns as column>
    public ${column.javatype} get${column.property?capitalize} () {
        return this.${column.property};
    }

    public void set${column.property?capitalize} (${column.javatype} ${column.property}) {
        this.${column.property} = ${column.property};
    }

</#list>
}
