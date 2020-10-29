<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<@common.page>
	<h3>${listOfAccounts}</h3>
	
<table class="table table-striped">
	<thead>
	<tr>
		<th>${user}</th>
		<th>${role}</th>
	</tr>
	</thead>
	<tbody>
	<#list users as user>
		<tr>
			<td>
			<span>${user.username}</span>
			</td>
			<td>
			<#list user.roles as role><span>${role}<#sep>, </span></#list>
			</td>
		</tr>
	</#list>
	</tbody>
</table>
</@common.page>