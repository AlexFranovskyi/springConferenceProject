<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<#include "parts/security.ftlh" >
<#import "parts/task.ftlh" as task>
<@common.page>
<@task.techTask />

	<#if !known>
		<Strong>${messageForGuest} <a href="/login">${signInProposal}</a></Strong>
	</#if>
</@common.page>