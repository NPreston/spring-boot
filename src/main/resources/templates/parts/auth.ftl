<#macro login path>
    <form action="${path}" method="POST">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="POST">
        <input type="submit" value="Sign Out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>
