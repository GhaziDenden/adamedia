<?php

require(dirname(__FILE__).'/config/config.inc.php');

$id_restaurant = Tools::getValue('id');
$restaurant = new Restaurant($id_restaurant);
$restaurant->delete();

Tools::redirectLink('restaurants.php');
?>
