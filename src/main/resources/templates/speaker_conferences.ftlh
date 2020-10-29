<#import "parts/common.ftlh" as common>
<#import "parts/conference_list.ftlh" as confList>

<@common.page>
    
    ${message?ifExists}
    
    <@confList.conferenceList "/speaker"/>    
</@common.page>