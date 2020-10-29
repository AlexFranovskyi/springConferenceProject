<#include "locales/locale.ftlh">
<#include "security.ftlh">
<#import "paginator.ftlh" as pager>

<#macro reportList path>

<#if page.content?size != 0>
    	<div>${listOfReports}</div>
	    	<table class="table table-striped">
			  <thead>
			    <tr>
			      <#if isAdmin>
			      	<th scope="col">Id</th>
			      </#if>
			      <th scope="col">${reportName}</th>
			      <th scope="col">${speakerName}</th>
			      <th scope="col">${conferenceName}</th>
			      <th scope="col">${status}</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
	   			 <#list page.content as report>
			    	<tr>
			    	  <#if isAdmin>
				      	<th scope="row">${report.id}</th>
				      </#if>
				      <td>${report.name}</td>
				      <td>${report.speakerName}</td>
				      <td>${report.conferenceName}</td>
				      <td>${report.approved?string(approved, notApproved)}</td>
				      <td>
				        <#if isSpeaker && report.speakerName == "">
				    	<form action="propose_speaker" method="post">
		           			<input type="hidden" name="reportId" value="${report.id}">
		           			<input type="hidden" name="_csrf" value="${_csrf.token}">
		        			<div class="form-group my-2">
				            <button type="submit" class="btn btn-primary">${takeReport}</button>
			           		 </div>
		        		</form>
		        		</#if>
		        		<#if isAdmin && report.speakerName !="">
		        			<#if report.approved == false>
			        			<div class="btn-toolbar" role="toolbar">
					        		<form action="approve" method="post">
					           			<input type="hidden" name="reportId" value="${report.id}">
					           			<input type="hidden" name="_csrf" value="${_csrf.token}">
					        			<div class="form-group my-2">
							            <button type="submit" class="btn btn-primary mr-2">${approve}</button>
						           		 </div>
					        		</form>
					        		<form action="clear_speaker" method="post">
					           			<input type="hidden" name="reportId" value="${report.id}">
					           			<input type="hidden" name="_csrf" value="${_csrf.token}">
					        			<div class="form-group my-2">
							            <button type="submit" class="btn btn-info">${clearSpeaker}</button>
						           		 </div>
					        		</form>
					        	</div>
				        	<#else>
				        		<form action="disapprove" method="post">
				           			<input type="hidden" name="reportId" value="${report.id}">
				           			<input type="hidden" name="_csrf" value="${_csrf.token}">
				        			<div class="form-group my-2">
						            <button type="submit" class="btn btn-secondary">${disapprove}</button>
					           		 </div>
				        		</form>
				        	</#if>
		        		</#if>
		        	  </td>
				    </tr>
   				  </#list>
			   </tbody>
			</table>
		<@pager.paginator path page />
	<#else> 
		<div>
			<b>${noReportsToDisplay}</b>
		</div>
	</#if>

</#macro>