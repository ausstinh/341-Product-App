<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>

<h2>Edit Product</h2>
<form:form method= "POST" modelAttribute= "product" action= "editProductPost">
	<table>
	<tr>
		<td><form:input type="hidden" path="id" value="${product.id}"/><form:errors path="id"/></td>
	</tr>
	<tr>
		<td><form:label path="name">Laptop Name:</form:label>
		<td><form:input path="name" value="${product.name}"/><form:errors path="name"/></td>
	</tr>
	<tr>
		<td><form:label path="desc">Description</form:label><br>
		<td><form:textarea path="desc" value="${product.desc}"></form:textarea><form:errors path="desc"/></td>
	</tr>
	<tr>
		<td><form:label path="price">Price</form:label><br>
		<td><form:textarea path="price" value="${product.price}"></form:textarea><form:errors path="price"/></td>
	</tr>
	<tr>
		<td colspan= "2">
		<div align="center">
		<input type= "submit" value= "Save Changes">
		</div>
		</td>
	</tr>	
	</table>
	<br/>
</form:form>
${message}
<br/>