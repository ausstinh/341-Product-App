<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>

<h2>Add Product</h2>
<form:form method= "POST" modelAttribute= "product" action= "postProduct">
	<table>
	<tr>
		<td><form:label path="name">Product Name:</form:label>
		<td><form:input path="name" /><form:errors path="name"/></td>
	</tr>
	<tr>
		<td><form:label path="price">Price</form:label><br>
		<td><form:input path="price"/><form:errors path="price"/></td>
	</tr>
	<tr>
		<td><form:label path="desc">Description</form:label><br>
		<td><form:textarea path="desc"></form:textarea><form:errors path="desc"/></td>
	</tr>
		
	<tr>
		<td colspan= "2">
		<div align="center"><br>
		<input type= "submit" value= "Post">
		</div>
		</td>
	</tr>	
	</table>
	<br/>
</form:form>
${message}
<br/>