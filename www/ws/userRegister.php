<?php

require(dirname(__FILE__).'/../config/config.inc.php');

$firstname = Tools::getValue('firstname');
$lastname = Tools::getValue('lastname');
$email = Tools::getValue('email');
$password = Tools::getValue('password');
$phone = Tools::getValue('phone');
$fb_uid = Tools::getValue('fb_uid');


$state = NULL;
$result = NULL;
$errors = NULL;

if (empty($email) || empty($password))
	$errors[] = 'You need to fill both email and password';

if (!empty($email) && !Validate::isEmail($email))
	$errors[] = 'Incorrect email';

if (!empty($password) && !Validate::isPasswd($password))
	$errors[] = 'Incorrect password';

if (count($errors) == 0){
	
	$user = new User();
	$user->firstname = $firstname;
	$user->lastname = $lastname;
	$user->email = $email;
	$user->password = $password;
	$user->phone = $phone;
	$user->fb_uid = $fb_uid;
	
	if ($user->save()){
		
		$state = 'OK';
		$result['user'] = $user;
		
	}else{
		
		$state = 'KO';
		$errors[] = 'Error when creating user';
	}
	
}else $state = 'KO';

$reply = NULL;
$reply['state'] = $state;
$reply['result'] = $result;
$reply['errors'] = $errors;


echo json_encode($reply);

?>