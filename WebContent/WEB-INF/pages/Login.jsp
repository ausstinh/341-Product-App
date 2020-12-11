<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>
<h2>User Login</h2>
<form:form method= "POST" modelAttribute= "credentials" action= "loginuser">
	<table>
		<tr>
			<td><form:label path="username">UserName:</form:label>
			<td><form:input path="username" /><form:errors path="username"/></td>
		</tr>
		
		<tr>
			<td><form:label path="password">Password:</form:label>
			<td><form:input path="password" /><form:errors path="password"/></td>
		</tr>
	</table>
	<br/>
	<input  style="width:275px; height: 24px;" type= "submit" value= "Submit"><br/><br/>
	<a  href= "registration">Register</a><br/>
	<form:errors path="*"/>
	
</form:form>