<#macro login path>
	<form action=${path} method="post">
        <div class="form-group row">
	        <label class="col-sm-1 col-form-label"> Username: </label>
	        <div class="col-sm-6">
	        	<input type="text" name="username" class="form-control"/>
	        </div>
        </div>
        
        <div class="form-group row">
        	<label class="col-sm-1 col-form-label"> Password: </label>
	        <div class="col-sm-6">
	        	<input type="password" name="password" class="form-control"/>
	        </div>
        </div>
        
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        
        <button class="btn btn-primary mr-3" type="submit">Sign in</button>
        <a href="/registration">Register</a>
    </form>
</#macro>

<#macro logout>
	<form action="/logout" method="post">
       <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Sign out</button>
    </form>
</#macro>