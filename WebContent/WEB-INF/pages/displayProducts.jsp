<html>
</head>
	<body>
		<table id="products" style= "width:50%" border = "1">
			<thead>
				<tr style="background-color:#A0A0A0">
					<th width="25%">Product Name</th>
					<th width="25%">Description</th>
					<th width="25%">Price</th>
				</tr>
			</thead>	
			<tbody>
			</tbody>
		</table>
	</body>
<script type="text/javascript">
$(document).ready(getOrders);
function displayUsers(){
	var x = 0;
	var y = 0;
	var table = document.getElementById("products");
	for (var order of orders)
	{
	var row = table.insertRow(-1);
	row.setAttribute("align", "center");
	var cell1 = row.insertCell();
	var cell2 = row.insertCell();
	var cell3 = row.insertCell();
	row.id = x;
	cell1.id = x + "_" + y++;
	cell2.id = x + "_" + y++;
	cell3.id = x + "_" + y++;
	cell2.innerHTML = product.name;
	cell3.innerHTML = product.desc;
	cell4.innerHTML = product.price;
	row.style.backgroundColor = x & 1 ? "#FFFFFF" : "#F0F0F0";
	++x;
	y = 0;
	}
}
function getOrders(){
	$.ajax(
			{
			type: "GET",
			url: "/ProductApp/service/products",
			dataType: "json",
			success: function(data)
			{
			displayOrders(data);
			   },
			error: function (xhr, ajaxOptions, thrownError) 
			{
			alert(xhr.status);
			alert(thrownError);
			}
	});

}
</script>
</html>
