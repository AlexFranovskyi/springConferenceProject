<#macro sortForm path>

	<#if !sorter??>
		<#assign sorter = "date">
	</#if>
    
<div class="form-row mt-2">
	<div class="form-group col-md-6">
	    <form method="get" action=${path} class="form-inline">
	    	<button type="submit" class="btn btn-primary mr-2">show conferences</button>
	    	<label>Sort option:</label>
	        
	        <select name="sorter" class="custom-select my-1 mr-sm-2 ml-2">
	    		<option value="date" ${sorter?matches("date")?string("selected", "")}>by date</option>
	    		<option value="reports" ${sorter?matches("reports")?string("selected", "")}>by reports amount</option>
	    		<option value="participants" ${sorter?matches("participants")?string("selected", "")}>by participants amount</option>
			</select>
	    </form>
	</div>
</div>

</#macro>