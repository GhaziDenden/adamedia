<?php

require(dirname(__FILE__).'/../config/config.inc.php');

define('SEARCH_BY_NAME', 1);
define('SEARCH_BY_ADDRESS', 2);
define('SEARCH_BY_POSITION', 3);



$type = Tools::getValue('type');
$name = Tools::getValue('name');
$address = Tools::getValue('address');
$latitude = Tools::getValue('latitude');
$longitude = Tools::getValue('longitude');


$state = NULL;
$result = NULL;
$errors = NULL;

if (empty($type))
	$errors[] = 'You need to choose a type of research';
	
if (!empty($latitude) && !Validate::isCoordinate($latitude))
	$errors[] = 'Incorrect latitude';
	
if (!empty($longitude) && !Validate::isCoordinate($longitude))
	$errors[] = 'Incorrect longitude';

if (count($errors) == 0){
	
	switch($type){
		
		case SEARCH_BY_NAME:
			$data = Restaurant::getRestaurantsByName($name);
			break;
			
		case SEARCH_BY_ADDRESS:
			$data = Restaurant::getRestaurantsByAddress($address);
			break;
			
		case SEARCH_BY_POSITION:
			$data = Restaurant::getRestaurantsByPosition($latitude, $longitude);
			break;
			
		default :
			break;
	
	}
	
	$state = 'OK';
	$result['restaurants'] = $data;
	
}else $state = 'KO';

$reply = NULL;
$reply['state'] = $state;
$reply['result'] = $result;
$reply['errors'] = $errors;


echo json_encode($reply);

?>