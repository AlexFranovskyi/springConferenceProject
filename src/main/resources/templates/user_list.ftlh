<#import "parts/common.ftlh" as common>
<@common.page>
	<h3>List of users</h3>
	
<table>
	<thead>
	<tr>
		<th>User</th>
		<th>Role</th>
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