<#include "parts/locales/locale.ftlh">
<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as common>
<#import "parts/report_list.ftlh" as reports>

<@common.page>

<div><strong>${message!""}</strong></div>

<div class="form-row mt-2">
	<div class="form-group">
	    <form method="get" action="reports" class="form-inline">
	    	<button type="submit" class="btn btn-primary mr-5">${showReports}</button>
	    </form>
	</div>
</div>

<#if isAdmin>
<@reports.reportList "/admin/${conferenceId}/reports" />
<#else>
	<@reports.reportList "/speaker/${conferenceId}/reports" />
</#if>
	<a class="btn btn-primary mt-4" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
	  	${createNewReport}
	  	</a>
	  	
		<div class="collapse" id="collapseExample">
			<div class="form-group mt-3">
		    	<form method="post" action="post_report">
		    		<div class="form-group my-2 w-50">
		            	<input type="text" class="form-control" name="name" required placeholder="${reportName}"/>
		    		</div>
		            <input type="hidden" name="_csrf" value="${_csrf.token}">
		            <div class="form-group my-2">
			            <button type="submit" class="btn btn-primary">${postNewReport}</button>
		            </div>
		        </form>
		    </div>
		</div>
	  	
</@common.page>