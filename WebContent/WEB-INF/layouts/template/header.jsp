<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<c:choose>
<c:when test="${not empty user && user.getId() != -1}">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <ul class="navbar-nav">
     <li class="nav-item navbar-right">
      <a class="nav-link" href="/ProductApp/product/userProducts">My Products</a>
    </li>
    <li class="nav-item navbar-right">
      <a class="nav-link" href="/ProductApp/product/createProduct">Post Product</a>
    </li>
    <li class="nav-item navbar-right">
      <a class="nav-link" href="/ProductApp/user/logout">Logout</a>
    </li>
  </ul>
</nav>
</c:when>
<c:otherwise>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="/ProductApp/user/login">Login</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/ProductApp/user/registration">Registration</a>
    </li>
  </ul>
</nav>
</c:otherwise>
</c:choose>