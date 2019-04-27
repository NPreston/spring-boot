<#import "../layouts/common.ftl" as common>
<#import "../parts/auth.ftl" as auth>

<@common.page>

    <h3>Create new user</h3>
    ${message!}
    <@auth.login "/registration" true />

</@common.page>