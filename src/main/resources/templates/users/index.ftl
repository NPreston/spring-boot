<#import "../layouts/common.ftl" as common>

<@common.page>

    <div>Users list</div>

    <table>
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Role</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getUsername()}</td>
                <td><#list user.getRoles() as role>${role}<#sep> ,</#list></td>
                <td><a href="/users/${user.getId()}">Edit</a></td>
            </tr>
        <#else>
            <tr><td colspan="3">No users</td></tr>
        </#list>
        </tbody>
    </table>



</@common.page>