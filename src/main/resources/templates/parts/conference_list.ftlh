<#include "security.ftlh">

<#macro conferenceList path>
	<#if conferences??>
    	<div>Conference list</div>
	    	<table class="table">
			  <thead>
			    <tr>
			      <#if isAdmin>
			      	<th scope="col">Id</th>
			      </#if>
			      <th scope="col">Conference name</th>
			      <th scope="col">Date and time</th>
			      <th scope="col">Location</th>
			      <th scope="col">Amount of reports</th>
			      <th scope="col">Amount of visitors</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
	   			 <#list conferences as conference>
			    	<tr>
			    	  <#if isAdmin>
				      	<th scope="row">${conference.id}</th>
				      </#if>
				      <td>${conference.name}</td>
				      <td>${conference.localDateTime?replace("T", " ")}</td>
				      <td>${conference.location}</td>
				      <td>${conference.reportCounter}</td>
				      <td>${conference.registerCounter}</td>
				      <td>
				        <#if isSimpleUser>
				    	<form action=${path} method="post">
		           			<input type="hidden" name="conferenceId" value="${conference.id}">
		           			<input type="hidden" name="_csrf" value="${_csrf.token}">
		        			<div class="form-group my-2">
				            <button type="submit" class="btn btn-primary">Visit</button>
			           		 </div>
		        		</form>
		        		</#if>
		        		<#if isSpeaker||isAdmin>
		        		<#assign newPath=path + "/" + conference.id>
		        		<form action=${newPath} method="post">
		           			<input type="hidden" name="conferenceId" value="${conference.id}">
		           			<input type="hidden" name="_csrf" value="${_csrf.token}">
		        			<div class="form-group my-2">
				            <button type="submit" class="btn btn-primary">Details</button>
			           		 </div>
		        		</form>
		        		</#if>
		        	  </td>
				    </tr>
	    		  <#else>
				    <div>
				    	<b>No conferences planned</b>
				    </div>
   				  </#list>
		      </tbody>
		    </table>
	</#if>  
</#macro>