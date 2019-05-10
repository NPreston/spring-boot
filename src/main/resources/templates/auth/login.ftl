<#import "../layouts/common.ftl" as common>
<#import "../parts/auth.ftl" as auth>

<@common.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <h3>Login</h3>
    ${message!}
    <@auth.login "/login" false />
</@common.page>