<a class="btn btn-primary" data-toggle="collapse" href="#collapse-form" role="button" aria-expanded="false" aria-controls="collapse-form">
    Add new message
</a>

<div class="collapse <#if message??>show</#if>" id="collapse-form">
    <div class="form-group mt-3">
        <form action="/messages/add" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <div class="form-group">
                <input type="text" name="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>"
                       placeholder="Input message" />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" name="tag" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.tag}</#if>"
                       placeholder="Tag name" />
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
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