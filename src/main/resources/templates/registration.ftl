<#import "parts/common.ftl" as common>
<#import "parts/auth.ftl" as auth>

<@common.page>

    <h3>Add new user</h3>

    <@auth.login "/registration" />

</@common.page>