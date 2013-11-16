<?php

require(dirname(__FILE__).'/config/config.inc.php');

if (Tools::isSubmit('addRestaurant')){
	
	$restaurant = new Restaurant();
	$restaurant->name = Tools::getValue('name');
	$restaurant->address = Tools::getValue('address');
	$restaurant->city = Tools::getValue('city');
	$restaurant->zipcode = Tools::getValue('zipcode');
	$restaurant->country = Tools::getValue('country');
	$restaurant->type = Tools::getValue('type');
	$restaurant->phone = Tools::getValue('phone');
	$restaurant->email = Tools::getValue('email');
	$restaurant->latitude = Tools::getValue('latitude');
	$restaurant->longitude = Tools::getValue('longitude');
	
	$restaurant->save();
	Tools::redirectLink('restaurants.php');
}

?>

<!DOCTYPE html>
<html>
<head>
	<title>Adamedia restaurants</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	
	
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

.form{
	margin:0 auto;
	width:500px;
	padding:14px;
}

#stylized{
	border:solid 2px #b7ddf2;
}

#stylized h1 {
	font-size:14px;
	font-weight:bold;
	margin-bottom:8px;
	margin-bottom:20px;
	border-bottom:solid 1px #b7ddf2;
	padding-bottom:10px;
}

#stylized label{
	display:block;
	font-weight:bold;
	text-align:right;
	width:180px;
	float:left;
}

#stylized .small{
	color:#666666;
	display:block;
	font-size:11px;
	font-weight:normal;
	text-align:right;
	width:180px;
}

#stylized input{
	float:left;
	font-size:12px;
	padding:4px 2px;
	border:solid 1px #aacfe4;
	width:260px;
	margin:2px 0 20px 10px;
}

#stylized button{
	clear:both;
	margin-left:150px;
	width:125px;
	height:31px;
	text-align:center;
	line-height:31px;
	color:#FFFFFF;
	font-size:11px;
	font-weight:bold;
	background: #4E9CAF;
}

#map-canvas {
	float:left;
	height:200px;
	width:280px;
	display:block;
	margin:20px;
}

</style>

	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBRwFIQPfcA6pbS9rzDywOI-VPCdzYINY0&sensor=true"/></script>
	
    <script type="text/javascript">
	
		var marker;
		function initialize() {
			var mapOptions = {
				center: new google.maps.LatLng(39.96344, 32.87315),
				zoom: 12,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
			var marker = new google.maps.Marker({
				position: map.getCenter(),
				draggable: true,
				map: map
			});
			
			google.maps.event.addDomListener(marker, 'drag', function(){
				document.getElementById("latitude").value = this.getPosition().lat();
				document.getElementById("longitude").value = this.getPosition().lng();
			});
		}
		
		google.maps.event.addDomListener(window, 'load', initialize);
    </script>

</head>

<body>

<div class="center">

<div id="stylized" class="form">
<form id="form" name="form" method="post" action="add.php">
<h1>Add restaurant informations</h1>

<label>Name
<span class="small">Name of the restaurant</span>
</label>
<input type="text" name="name" id="name" value="" />

<label>Address
<span class="small">Address of the restaurant</span>
</label>
<input type="text" name="address" id="address" value="" />

<label>City
<span class="small">City of the restaurant</span>
</label>
<input type="text" name="city" id="city" value="" />

<label>Zipcode
<span class="small">Valid Zipcode of the restaurant</span>
</label>
<input type="text" name="zipcode" id="zipcode" value="" />

<label>Country
<span class="small">Country of the restaurant</span>
</label>
<input type="text" name="country" id="country"value="" />

<label>Type
<span class="small">Restaurant type (oriental, french ...)</span>
</label>
<input type="text" name="type" id="type" value="" />

<label>Email
<span class="small">Restaurant email address</span>
</label>
<input type="text" name="email" id="email" value="" />

<label>Phone
<span class="small">Restaurant phone number</span>
</label>
<input type="text" name="phone" id="phone" value="" />

<label>Latitude
<span class="small">Latitude coordinate</span>
</label>
<input type="text" name="latitude" id="latitude" value="" />

<label>Longitude
<span class="small">Longitude coordinate</span>
</label>
<input type="text" name="longitude" id="longitude" value="" />


<label>Maps
<span class="small">Choose your coordinates</span>
</label>
<div id="map-canvas"></div>

<button type="submit" name="addRestaurant">Add</button>
<div class="spacer"></div>

</form>
</div>

</div>

</body>
</html>