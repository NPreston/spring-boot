<#import "../parts/common.ftl" as common>

<@common.page>

    <div>User show</div>

    <form action="/users/${user.getId()}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="text" name="username" value="${user.getUsername()}" placeholder="Input username" />
        <#list roles as role>
            <di>
                <label><input type="checkbox" name="${role}" ${user.getRoles()?seq_contains(role)?string("checked", "")}>${role}</label>
            </di>
        </#list>
        <button type="submit">Update</button>
    </form>

</@common.page>