<#import "parts/common.ftlh" as common>
<#import "parts/sorting.ftlh" as sorting>
<#import "parts/conference_list.ftlh" as confList>

<@common.page>
    <div>Main page</div>
    ${message?ifExists}
    
    <@sorting.sortForm "/main" />
    <@confList.conferenceList "/main"/>    
</@common.page>