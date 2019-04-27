<#macro login path isRegisterForm>
    <form action="${path}" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Username:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="username" placeholder="Username">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" name="email" placeholder="some@example.com">
                </div>
            </div>
        </#if>

        <#if !isRegisterForm>
            <div class="form-group row">
                <a href="/registration">Create new user</a>
            </div>
        </#if>

        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign in</#if></button>
            </div>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-secondary">Sign Out</button>
    </form>
</#macro>
