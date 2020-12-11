<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>

	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"></link>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>

<h2>My Products</h2>

<input type="hidden" id="userId" value="${userId}">

<table style="width:60%" border="1">
	<tr>
		<th><label>Name</label></th>
		<th><label>Description</label></th>
		<th><label>Price</label></th>
		<th><label>View Product</label></th>
	</tr>
	
	<c:forEach var="product" items="${product}">
		<tr>
			<td><label>${product.name}</label></td>
			<td><label>${product.desc}</label></td>
			<td><label>$${product.price}</label></td>
			<td><form:form method= "POST" action="/ProductApp/product/viewProductPost">
				<input type="hidden" name="productId" value="${product.id}">
				<input type= "submit" value= "View Post">
			</form:form></td>
		</tr>
	</c:forEach>
</table>
<br><br>
