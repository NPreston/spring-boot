<#import "../layouts/common.ftl" as common>

<@common.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/messages/" method="get" class="form-inline">
                <input type="text" class="form-control" name="filter" value="${filter!}" placeholder="Search">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapse-form" role="button" aria-expanded="false" aria-controls="collapse-form">
        Add new message
    </a>

    <div class="collapse" id="collapse-form">
        <div class="form-group mt-3">
            <form action="/messages/add" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <div class="form-group">
                    <input type="text" name="text" class="form-control" placeholder="Input message" />
                </div>
                <div class="form-group">
                    <input type="text" name="tag" class="form-control" placeholder="Tag name" />
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" />
                        <label class="custom-file-label" for="file">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

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
</@common.page>