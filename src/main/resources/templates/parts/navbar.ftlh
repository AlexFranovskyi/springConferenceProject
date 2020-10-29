<#include "locales/locale.ftlh">
<#include "security.ftlh">
<#import "login.ftlh" as lg>

<#assign lang = .locale>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">${conferences}</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <#if isSimpleUser>
      <li class="nav-item">
        <a class="nav-link" href="/user_page">${userPage}<span class="sr-only">(current)</span></a>
      </li>
      </#if>
      
      <#if isAdmin>
	      <li class="nav-item">
	        <a class="nav-link" href="/admin">${adminCabinet}<span class="sr-only">(current)</span></a>
	      </li>
      </#if>
      
      <#if isSpeaker>
      <li class="nav-item">
        <a class="nav-link" href="/speaker">${speakerCabinet}<span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="/speaker/own_reports">${speakerReports}<span class="sr-only">(current)</span></a>
      </li>
      </#if>
      
      <li class="nav-item">
	        <a class="nav-link" href="/user_list">${listOfAccounts}<span class="sr-only">(current)</span></a>
	  </li>
      
    </ul>
    
	<form method="get" action="/locale" class="form-inline mr-5">
	   <button type="submit" class="btn btn-info mr-1">${switchLanguage}</button>
	   <select name="lang" class="custom-select mr-sm-2">
	   	 <option value="en_US" ${lang?matches("en_US")?string("selected", "")}>EN</option>
	     <option value="uk_UA" ${lang?matches("uk_UA")?string("selected", "")}>UA</option>
		 </select>		
	</form>

    <div class="navbar-text mr-3 ml-3">${name}</div>
    <#if known>
    	<@lg.logout />
    </#if>
    <#if !known && !springMacroRequestContext.requestUri?contains("/login")>
    	<form action="/login">
        <button class="btn btn-primary" type="submit">${signIn}</button>
    </form>
    </#if>
  </div>
</nav>