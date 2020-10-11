<#include "security.ftlh">
<#import "login.ftlh" as lg>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">Conference App</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/">Main <span class="sr-only">(current)</span></a>
      </li>
      
      <#if isAdmin>
      <li class="nav-item">
        <a class="nav-link" href="/admin">Admin cabinet<span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="/user">User list<span class="sr-only">(current)</span></a>
      </li>
      </#if>
      
      <#if isSpeaker>
      <li class="nav-item">
        <a class="nav-link" href="/speaker">Speaker cabinet<span class="sr-only">(current)</span></a>
      </li>
      </#if>
      
    </ul>
    
    <div class="navbar-text mr-3">${name}</div>
    <@lg.logout />
  </div>
</nav>