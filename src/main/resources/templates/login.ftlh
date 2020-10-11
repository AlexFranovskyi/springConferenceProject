<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as log>
<@common.page>

	<div class="mb-4"><h4>Login page</h4></div>
	
    ${message!""}
    
        <@log.login "/login" />
</@common.page>