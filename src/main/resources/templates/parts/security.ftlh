<#assign
	known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
	<#assign
		user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
		name = user.getUsername()
		isAdmin = user.isAdmin()
		isSpeaker = user.isSpeaker()
		isSimpleUser = user.isSimpleUser()
	>
<#else>
	<#assign
		name = "Guest"
		isAdmin = false
		isSpeaker = false
		isSimpleUser = false
	>
</#if>