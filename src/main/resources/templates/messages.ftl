<#import "parts/common.ftl" as common>
<#import "parts/auth.ftl" as auth>

<@common.page>

    <@auth.logout />

    <div>
        <form action="/messages/add" method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Input message" />
            <input type="text" name="tag" placeholder="Tag name" />
            <input type="file" name="file" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    </div>


    <div>Messages list</div>

    <form action="/messages/" method="get">
        <input type="text" name="filter" value="${filter!}" />
        <button type="submit">Filter</button>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.getUser().getUsername()}</strong>
            <div>
                <#if message.filename??>
                    <img src="/images/${message.filename}" alt="">
                </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>

</@common.page>