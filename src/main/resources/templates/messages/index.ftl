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

    <#include "parts/edit.ftl">
    <#include "parts/list.ftl">

</@common.page>