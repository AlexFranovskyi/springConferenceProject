<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<#import "parts/paginator.ftlh" as pager>

<@common.page>
    <h2>${speakerReports}</h2>
    
    <#if page.content?size != 0>
	    	<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">${reportName}</th>
			      <th scope="col">${conferenceName}</th>
			      <th scope="col">${status}</th>
			    </tr>
			  </thead>
			  <tbody>
	   			 <#list page.content as report>
			    	<tr>
				      <td>${report.name}</td>
				      <td>${report.conferenceName}</td>
				      <td>${report.approved?string(approved, notApproved)}</td>
				    </tr>
   				  </#list>
			   </tbody>
			</table>
		<@pager.paginator "/speaker/own_reports" page />
	<#else> 
		<div>
			<b>${noReportsToDisplay}</b>
		</div>
	</#if>    
</@common.page>