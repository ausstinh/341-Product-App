<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>
<h2>User Register</h2>
<form:form method= "POST" modelAttribute= "user" action= "registeruser">
	<table>
	
		<tr>
			<td><form:label path="firstName">First Name:</form:label>
			<td><form:input path="firstName" /><form:errors path="firstName"/></td>
		</tr>
		
		<tr>
			<td><form:label path="lastName">Last Name:</form:label>
			<td><form:input path="lastName" /><form:errors path="lastName"/></td>
		</tr>
		<tr>
			<td><form:label path="username">UserName:</form:label>
			<td><form:input path="username" /><form:errors path="username"/></td>
		</tr>
		<tr>
			<td><form:label path="password">Password:</form:label>
			<td><form:input path="password" /><form:errors path="password"/></td>
		</tr>	
	</table>
		<input style="width:275px; height: 24px;" type= "submit" value= "Submit">
	<br/>
	<form:errors path="*"/>
	
</form:form>