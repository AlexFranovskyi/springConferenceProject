<#include "locales/locale.ftlh">
<#macro paginator url page>
	<#if page.getTotalPages() gt 7>
		<#assign
			totalPages = page.getTotalPages()
			pageNumber = page.getNumber() + 1
			
			head = (pageNumber gt 4)?then([1, -1], [1, 2, 3])
			tail = (pageNumber lt totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
			bodyBefore = (pageNumber gt 4 && pageNumber lt totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
			bodyAfter = (pageNumber gt 2 && pageNumber lt totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])
			body = head + bodyBefore + (pageNumber gt 3 && pageNumber lt totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
		>
	<#else>
		<#assign body = 1..page.getTotalPages()>
	</#if>
	<div>
		<ul class="pagination">
    		<li class="page-item disabled">
      			<a class="page-link" href="#" tabindex="-1">${pages}</a>
    		</li>
    		<#list body as p>
    			<#if (p - 1) == page.getNumber()>
	    			<li class="page-item active">
		      			<a class="page-link" href="#" tabindex="-1">${p}</a>
		    		</li>
		    	<#elseif p == -1>
		    		<li class="page-item disabled">
		      			<a class="page-link" href="#" tabindex="-1">...</a>
		    		</li>
    			<#else>
		    		<li class="page-item">
		      			<a class="page-link" href="${url}?page=${p-1}&sort=${sort?replace(': ', ',')}" tabindex="-1">${p}</a>
		    		</li>   
		    	</#if> 		
    		</#list>
    	</ul>
	</div>
</#macro>