<#include "locales/locale.ftlh">
<#include "security.ftlh">
<#import "sorting.ftlh" as sorting>
<#import "paginator.ftlh" as pager>

<#macro conferenceList path>
	<@sorting.sortForm path />
	
	<#if page??>
    	<div><h5>${listOfConferences}</h5></div>
	    	<table class="table table-striped">
			  <thead>
			    <tr>
			      <#if isAdmin>
			      	<th scope="col">Id</th>
			      </#if>
			      <th scope="col">${conferenceName}</th>
			      <th scope="col">${dateAndTime}</th>
			      <th scope="col">${location}</th>
			      <th scope="col">${amountOfReports}</th>
			      <th scope="col">${amountOfRegisteredVisitors}</th>
			      <th scope="col">${amountOfArrivedVisitors}</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
	   			 <#list page.content as conference>
			    	<tr>
			    	  <#if isAdmin>
				      	<th scope="row">${conference.id}</th>
				      </#if>
				      <td>${conference.conferenceName}</td>
				      <td>${conference.localDateTime?replace("T", " ")}</td>
				      <td>${conference.location}</td>
				      <td>${conference.reportCounter}</td>
				      <td>${conference.visitorCounter}</td>
				      <td>${conference.arrivedVisitorsAmount}</td>
				      <td>
				      <#if showFutureEvents == "yes">
				        <#if isSimpleUser>
				        	<form action=${path} method="post">
				           			<input type="hidden" name="conferenceId" value="${conference.id}">
				           			<input type="hidden" name="_csrf" value="${_csrf.token}">
				        			<div class="form-group my-2">
						            <button type="submit" class="btn btn-primary">${visit}</button>
					           		 </div>
				        	</form>
		        		</#if>
		        	  </#if>
		        	  
		        		<#if isSpeaker||isAdmin>
		        		<#assign newPath=path + "/" + conference.id>
		        		<form action=${newPath} method="get">
		        			<div class="form-group my-2">
				            <button type="submit" class="btn btn-primary">${details}</button>
			           		 </div>
		        		</form>
		        		</#if>
		        	  </td>
				    </tr>
   				  </#list>
			   </tbody>
			</table>
		<@pager.paginator path page />
	</#if> 
	<#if page??>
		<#if page.content?size = 0>
		<div>
			<b>No conferences to display</b>
		</div>
		</#if>
	</#if>
</#macro>