<#include "locales/locale.ftlh">
<#macro sortForm path>

	<#if sort??>
		<#assign sorter = sort?replace(": ", ",")>
	<#else>
		<#assign
		sorter = "localDateTime,DESC"
		showFutureEvents = "yes"
		>
	</#if>
    
<div class="form-row mt-2">
	<div class="form-group">
	    <form method="get" action=${path} class="form-inline">
	    	<button type="submit" class="btn btn-primary mr-5">${showConferences}</button>
	    	  
	        <label class="mr-2 ml-4">${sortOptions}:</label>
	        <select name="sort" class="custom-select my-1 mr-sm-2">
	    		<option value="localDateTime,DESC" ${sorter?matches("localDateTime,DESC")?string("selected", "")}>${fromLatestToEarliest}</option>
	    		<option value="localDateTime,ASC" ${sorter?matches("localDateTime,ASC")?string("selected", "")}>${fromEarliestToLatest}</option>
	    		
	    		<option value="reportCounter,DESC" ${sorter?matches("reportCounter,DESC")?string("selected", "")}>${fromUtmostReportsToLeast}</option>
	    		<option value="reportCounter,ASC" ${sorter?matches("reportCounter,ASC")?string("selected", "")}>${fromLeastReportsToUtmost}</option>
	    		
	    		<option value="visitorCounter,DESC" ${sorter?matches("visitorCounter,DESC")?string("selected", "")}>${fromUtmostRegisteredVisitorsToLeast}</option>
	    		<option value="visitorCounter,ASC" ${sorter?matches("visitorCounter,ASC")?string("selected", "")}>${fromLeastRegisteredVisitorsToUtmost}</option>
			</select>
			
			<select name="showFutureEvents" class="custom-select my-1 mr-sm-2">
	    		<option value="yes" ${showFutureEvents?matches("yes")?string("selected", "")}>${futureEvents}</option>
	    		<option value="no" ${showFutureEvents?matches("no")?string("selected", "")}>${pastEvents}</option>
	    	</select>
	    </form>
	</div>
</div>

</#macro>