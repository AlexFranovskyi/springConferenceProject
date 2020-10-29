<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<@common.page>
    
    <div class="mb-4"><h4>${registrationForm}</h4></div>
    
    ${message?ifExists}
    
    
        <form method="post">
            <div class="form-group row">
	        <label class="col-sm-2 col-form-label">${username}:</label>
		        <div class="col-sm-6">
		        	<input type="text" name="username" class="form-control" required/>
		        </div>
        	</div>
            
            <div class="form-group row">
        	<label class="col-sm-2 col-form-label">${email}:</label>
		        <div class="col-sm-6">
		        	<input type="email" name="email" class="form-control" required/>
		        </div>
       		</div>
            
            
            <div class="form-group row">
        	<label class="col-sm-2 col-form-label">${password}: </label>
		        <div class="col-sm-6">
		        	<input type="password" name="password" class="form-control" required/>
		        </div>
       		</div>
            
            <div class="form-group row">
            <label class="col-sm-2 col-form-label">${setRole}:</label>
            	<div class="col-sm-2">
            		<select name="role" class="form-control">
    					<#list roles as role>
        					<option value="${role}">${role}</option>
    					</#list>
					</select>
				</div>
            </div>
            
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div>
            	<button class="btn btn-primary mb-2 mr-3" type="submit">${register}</button>
            	<a href="/login">${backToLoginPage}</a>
            </div>
        </form>
</@common.page>