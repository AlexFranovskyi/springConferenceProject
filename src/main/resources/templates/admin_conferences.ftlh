<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<#import "parts/conference_list.ftlh" as confList>

<@common.page>
	<div class="mb-2"><h4>${adminCabinet}</h4></div>
	<@confList.conferenceList "/admin"/>
	
	<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
  	${createNewEvent}
  	</a>
  	
	<div class="collapse" id="collapseExample">
		<div class="form-group mt-3">
	    	<form method="post" action="admin">
	    		<div class="form-group my-2 w-50">
	            	<input type="text" class="form-control" name="name" required placeholder="${name}"/>
	    		</div>
	            <div class="form-group my-2 w-50">
	            	<input type="text" class="form-control" name="localDateTime" required placeholder="yyyy-mm-dd hh:mm"/>
	    		</div>
	    		<div class="form-group my-2 w-50">
	            	<input type="text" class="form-control" name="location" required placeholder="${location}"/>
	    		</div>	            
	            <input type="hidden" name="_csrf" value="${_csrf.token}">
	            <div class="form-group my-2">
		            <button type="submit" class="btn btn-primary">${postNewConference}</button>
	            </div>
	        </form>
	    </div>
	</div>
	
  	<div><strong>${message!""}</strong></div>
    
</@common.page>