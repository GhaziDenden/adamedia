<?php

require(dirname(__FILE__).'/../config/config.inc.php');

$name = Tools::getValue('name');
$address = Tools::getValue('address');
$city = Tools::getValue('city');
$zipcode = Tools::getValue('zipcode');
$country = Tools::getValue('country');
$phone = Tools::getValue('phone');
$email = Tools::getValue('email');
$latitude = Tools::getValue('latitude');
$longitude = Tools::getValue('longitude');


$state = NULL;
$result = NULL;
$errors = NULL;

if (empty($name))
	$errors[] = 'You need to fill at last the name of the restaurant';

if (!empty($email) && !Validate::isEmail($email))
	$errors[] = 'Incorrect email';

if (!empty($phone) && !Validate::isPhoneNumber($phone))
	$errors[] = 'Incorrect phone number';
	
if (!empty($zipcode) && !Validate::isPostCode($zipcode))
	$errors[] = 'Incorrect zipcode';
	
if (!empty($latitude) && !Validate::isCoordinate($latitude))
	$errors[] = 'Incorrect latitude';
	
if (!empty($longitude) && !Validate::isCoordinate($longitude))
	$errors[] = 'Incorrect longitude';

if (count($errors) == 0){
	
	$restaurant = new Restaurant();
	$restaurant->name = $name;
	$restaurant->address = $address;
	$restaurant->city = $city;
	$restaurant->zipcode = $zipcode;
	$restaurant->country = $country;
	$restaurant->phone = $phone;
	$restaurant->email = $email;
	$restaurant->latitude = $latitude;
	$restaurant->longitude = $longitude;
	
	if ($restaurant->save()){
		
		$state = 'OK';
		$result['restaurant'] = $restaurant;
		
	}else{
		
		$state = 'KO';
		$errors[] = 'Error when creating restaurant';
	}
	
}else $state = 'KO';

$reply = NULL;
$reply['state'] = $state;
$reply['result'] = $result;
$reply['errors'] = $errors;


echo json_encode($reply);

?>