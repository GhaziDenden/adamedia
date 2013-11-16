<?php

require(dirname(__FILE__).'/../config/config.inc.php');

$email = Tools::getValue('email');
$password = Tools::getValue('password');


$state = "";
$result = NULL;
$errors = NULL;

if (empty($email) || empty($password))
	$errors = 'You need to fill both email and password';

if (!empty($email) && !Validate::isEmail($email))
	$errors = 'Incorrect email';

if (!empty($password) && !Validate::isPasswd($password))
	$errors = 'Incorrect password';

if (count($errors) == 0){	
	$user = new User();
	if ($user->getByEmail($email,$password)){
		
		$state = 'OK';
		$result['user'] = $user;
		
	}else{
		
		$state = 'KO';
		$errors = 'Incorrect email or and password';
	}

}else $state = 'KO';

$reply = NULL;
$reply['state'] = $state;
$reply['result'] = ($result == NULL ? "" : $result);
$reply['errors'] = ($errors == NULL ? "" : $errors);


echo json_encode($reply);

?>