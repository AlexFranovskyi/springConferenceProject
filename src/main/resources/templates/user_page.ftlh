<#include "parts/locales/locale.ftlh">
<#import "parts/common.ftlh" as common>
<#import "parts/conference_list.ftlh" as confList>

<@common.page>
    <h2>${userPage}</h2>
    ${message?ifExists}
    
    <@confList.conferenceList "/user_page"/>    
</@common.page>