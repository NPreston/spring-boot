<#import "../layouts/common.ftl" as common>
<#import "../parts/auth.ftl" as auth>

<@common.page>
    <h3>Login</h3>

    <@auth.login "/login" false />
</@common.page>