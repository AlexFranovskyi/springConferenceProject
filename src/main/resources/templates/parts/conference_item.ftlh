<#include "locales/locale.ftlh">
<#include "security.ftlh">
<#macro conferenceItem item>

	<div>${conferenceDetails}</div>
	    	<table class="table">
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
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
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
				      	<form action="${conference.id}/reports" method="get">
		        			<div class="form-group my-2">
				            	<button type="submit" class="btn btn-primary">${reports}</button>
			           		</div>
		        		</form>
				      </td>
				      
				    </tr>
			   </tbody>
			</table>
	<#if isAdmin>
	
		<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
	  	${updateData}
	  	</a>
		<div class="collapse" id="collapseExample">
			<div class="form-group mt-3">
		    	<form method="post" action="/admin/${conference.id}">
		            <div class="form-group my-2 w-50">
		            	<input type="text" class="form-control" name="localDateTime" required 
		            	value = "${conference.localDateTime?replace('T', ' ')}" placeholder="yyyy-mm-dd hh:mm"/>
		    		</div>
		    		<div class="form-group my-2 w-50">
		            	<input type="text" class="form-control" name="location" value = "${conference.location}"
		            	required placeholder="${location}"/>
		    		</div>
		    		<div class="form-group my-2 w-50">
			           	<input type="text" class="form-control" name="arrivedVisitorsAmount" 
			           	value = "${conference.arrivedVisitorsAmount}" placeholder="${amountOfArrivedVisitors}"/>
			    	</div>	       
		            <input type="hidden" name="_csrf" value="${_csrf.token}">
		            <div class="form-group my-2">
			            <button type="submit" class="btn btn-primary">${updateConference}</button>
		            </div>
		        </form>
		    </div>
		</div>
		
       		<form action="${conference.id}/delete" method="post">
       			<div class="form-group my-2">
       				<input type="hidden" name="conferenceId" value = ${conference.id}>
       				<input type="hidden" name="_csrf" value="${_csrf.token}">
	            	<button type="submit" class="btn btn-danger">${delete}</button>
           		</div>
       		</form>
	</#if>

</#macro>