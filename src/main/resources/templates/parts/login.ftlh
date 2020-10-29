<#include "locales/locale.ftlh">
<#macro login path>
	<form action=${path} method="post">
        <div class="form-group row">
	        <label class="col-sm-2 col-form-label"> ${username}: </label>
	        <div class="col-sm-6">
	        	<input type="text" name="username" class="form-control" required/>
	        </div>
        </div>
        
        <div class="form-group row">
        	<label class="col-sm-2 col-form-label"> ${password}: </label>
	        <div class="col-sm-6">
	        	<input type="password" name="password" class="form-control" required/>
	        </div>
        </div>
        
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        
        <button class="btn btn-primary mr-3" type="submit">${signIn}</button>
        <a href="/registration">${register}</a>
    </form>
</#macro>

<#macro logout>
	<form action="/logout" method="post">
       <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">${signOut}</button>
    </form>
</#macro>