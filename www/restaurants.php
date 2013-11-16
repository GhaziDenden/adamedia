<?php

require(dirname(__FILE__).'/config/config.inc.php');

$restaurants = Restaurant::getRestaurantsList();

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Adamedia restaurants</title>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />

<style type="text/css">	
.button {
	display: block;
	width: 115px;
	height: 20px;
	background: #4E9CAF;
	padding: 10px;
	text-align: center;
	border-radius: 5px;
	color: white;
	font-weight: bold;
}
.center {
	width:60%; 
	height:400px;
	margin:auto;
	
	position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;
}
.list{
	width:100%; 
	height:400px;
	margin-top:20px;
}
.table{
	width:100%; 
}
.table .id {width:10%;}
.table .name {width:20%;}
.table .address {width:20%;}
.table .phone {width:15%;}
.table .email {width:15%;}
.table .change {width:10%;}
.table .delete {width:10%;}
</style>

<script>

function confirmDelete(id_restaurant){
	if (confirm('Are you sure you want to delete this restaurant ?')) {
		window.location.href = "delete.php?id="+id_restaurant;
	}
}

</script>

</head>

<body>
<div class="center">

<a href="add.php" class="button">Add Restaurant</a>
<div class="list">
<table class="table">
<tr>
	<td class="id"><b>ID</b></td>
	<td class="name"><b>Restaurant name</b></td>
	<td class="address"><b>Restaurant address</b></td>
	<td class="phone"><b>Phone</b></td>
	<td class="email"><b>Email</b></td>
	<td class="change"><b>Change</b></td>
	<td class="delete"><b>Delete</b></td>
</tr>

<?php foreach($restaurants as $rest){ 
	$restaurant = new Restaurant($rest['id_restaurant']);
?>
<tr>
	<td class="id"><?php echo $restaurant->id; ?></td>
	<td class="name"><?php echo $restaurant->name; ?></td>
	<td class="address"><?php echo $restaurant->city.', '.$restaurant->country; ?></td>
	<td class="phone"><?php echo $restaurant->phone; ?></td>
	<td class="email"><?php echo $restaurant->email; ?></td>
	<td class="change" style="text-align:center;"><a href="edit.php?id=<?php echo $restaurant->id; ?>"><img src="img/admin/edit.gif" /></a></td>
	<td class="delete" style="text-align:center;"><a href="#" onclick="javascript:confirmDelete(<?php echo $restaurant->id; ?>);"><img src="img/admin/delete.gif" /></a></td>
</tr>
<?php }?>
</table>
</div>
</div>
</body>
</html>