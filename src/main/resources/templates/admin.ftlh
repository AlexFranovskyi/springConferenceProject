<#import "parts/common.ftlh" as common>
<#import "parts/sorting.ftlh" as sorting>
<#import "parts/conference_list.ftlh" as confList>

<@common.page>
	<div class="mb-2"><h4>Admin cabinet</h4></div>
   	
    
   	<@sorting.sortForm "/admin" />
	<@confList.conferenceList "admin"/>
	
	
	<a class="btn btn-primary mt-4" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
  	Post new event
  	</a>

	<div class="collapse" id="collapseExample">
		<div class="form-group mt-3">
	    	<form method="post" action="admin">
	    		<div class="form-group my-2 w-50">
	            	<input type="text" class="form-control" name="name" required placeholder="name"/>
	    		</div>
	            <div class="form-group my-2 w-50">
	            	<input type="text" class="form-control" name="localDateTime" required placeholder="yyyy-mm-dd hh:mm"/>
	    		</div>
	    		<div class="form-group my-2 w-50">
	            	<input type="text" class="form-control" name="location" required placeholder="location"/>
	    		</div>	            
	            <input type="hidden" name="_csrf" value="${_csrf.token}">
	            <div class="form-group my-2">
		            <button type="submit" class="btn btn-primary">Create new conference</button>
	            </div>
	        </form>
	    </div>
	</div>
    
</@common.page>