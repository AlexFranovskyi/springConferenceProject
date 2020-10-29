<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<#import "parts/conference_item.ftlh" as confItem>

<@common.page>
	<div class="mb-2"><h4>${conferenceDetails}</h4></div>
	<strong>${message?ifExists}</strong>
	
	<#if conference??>
		<@confItem.conferenceItem conference />
	<#else>
		<h4>${nothingToDisplay}</h4>
	</#if>

</@common.page>