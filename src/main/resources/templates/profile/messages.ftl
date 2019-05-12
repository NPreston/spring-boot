<#import "../layouts/common.ftl" as common>

<@common.page>
    <#if isCUrrentUser>
        <#include "../messages/parts/edit.ftl" />
    </#if>
    <#include "../messages/parts/list.ftl" />
</@common.page>