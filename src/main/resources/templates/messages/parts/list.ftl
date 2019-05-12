<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <#if message.filename??>
                <img src="/images/${message.filename}" class="card-img-top" alt="">
            </#if>

            <div class="card-body">
                <p class="card-text">${message.text}</p>
                <p class="card-title"><i>${message.tag}</i></p>
            </div>
            <div class="card-footer text-muted">
                ${message.getUser().getUsername()}
            </div>
        </div>
    <#else>
        No messages
    </#list>
</div>