<#import "parts/common.ftlh" as common>
<@common.page>
    
    <div class="mb-4"><h4>Registration form</h4></div>
    
    ${message?ifExists}
    
    
        <form method="post">
            <div class="form-group row">
	        <label class="col-sm-1 col-form-label">Username:</label>
		        <div class="col-sm-6">
		        	<input type="text" name="username" class="form-control"/>
		        </div>
        	</div>
            
            <div class="form-group row">
        	<label class="col-sm-1 col-form-label">Email:</label>
		        <div class="col-sm-6">
		        	<input type="email" name="email" class="form-control"/>
		        </div>
       		</div>
            
            
            <div class="form-group row">
        	<label class="col-sm-1 col-form-label"> Password: </label>
		        <div class="col-sm-6">
		        	<input type="password" name="password" class="form-control"/>
		        </div>
       		</div>
            
            <div class="form-group row">
            <label class="col-sm-1 col-form-label">Set role:</label>
            	<div class="col-sm-2">
            		<select name="role" class="form-control">
    					<#list roles as role>
        					<option value="${role}">${role}</option>
    					</#list>
					</select>
				</div>
            </div>
            
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-primary mb-2" type="submit">Create</button>
        </form>
        
       <a href="/login">Back to login page</a>
</@common.page>