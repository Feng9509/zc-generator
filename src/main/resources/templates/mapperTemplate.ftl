<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="">
    <#--结果集-->
    <resultMap id="BaseResultMap" type="">
        <#--主键(可能存在联合主键)-->
<#list param.ids as id>
        <id column="${id.column}" property="${id.property}" jdbcType="${id.jdbctype}" />
</#list>
        <#--其它列-->
<#list param.columns as column>
        <result column="${column.column}" property="${column.property}" jdbcType="${column.jdbctype}" />
</#list>
    </resultMap>

    <#--所有查询字段-->
    <sql id="Base_Column_List">
        <#list param.ids as id>t.${id.column}<#if (id_has_next || (param.columns?size > 0))>,</#if></#list><#rt>
        <#lt><#list param.columns as column>t.${column.column}<#if column_has_next>,</#if></#list>
    </sql>

    <#--WHERE条件-->
    <sql id="Base_Where_List">
        <where>
            <trim prefixOverrides="AND">
                AND t.DELFLAG = 0
<#list param.ids as id>
                <if test="${id.property} != null"> AND t.${id.column} = ${r"#{"}${id.property}}</if>
</#list>
<#list param.columns as column>
                <if test="${column.property} != null"> AND t.${column.column} = ${r"#{"}${column.property}}</if>
</#list>
            </trim>
        </where>
    </sql>

    <select id="select" resultMap="BaseResultMap" parameterType="java.util.Map">
        SELECT <include refid="Base_Column_List" />
        FROM ${param.tablename} t
        <include refid="Base_Where_List" />
    </select>
    
    <select id="selectById" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${param.tablename} t
<#if (param.ids?size lt 1) ><#--联合主键-->
        <where>
            <trim prefixOverrides="AND">
    <#list param.ids as id>
                AND t.${id.column} = ${r"#{"}${id.property}}
    </#list>
            </trim>
        </where>
<#else ><#--单一主键-->
        WHERE t.${param.ids[0].column} = ${r"#{"}${param.ids[0].property}}
</#if>
    </select>
</mapper>
