<?php


class UserCore extends ObjectModel
{
	public 		$id;
	public 		$firstname;
	public 		$lastname;
	public 		$password;
	public 		$email;
	public 		$phone;
	public 		$fb_uid;
	public 		$date_add;
	public 		$date_upd;
	
	protected   $tables = array ('users');
	protected 	$table = 'users';
	protected 	$identifier = 'id_user';

	
	public	function getFields()
	{
	 	parent::validateFields();
		$fields['id_user'] 		= (int)$this->id;
		$fields['firstname'] 	= pSQL($this->firstname);
		$fields['lastname'] 	= pSQL($this->lastname);
		$fields['password'] 	= pSQL($this->password);
		$fields['email'] 		= pSQL($this->email);
		$fields['phone'] 		= pSQL($this->phone);
		$fields['fb_uid'] 		= pSQL($this->fb_uid);
		$fields['date_add'] 	= pSQL($this->date_add);
		$fields['date_upd'] 	= pSQL($this->date_upd);
		return $fields;
	}
	
	
	public function getByEmail($email, $passwd = null)
	{
	
		$result = Db::getInstance()->getRow('
				SELECT *
				FROM `'._DB_PREFIX_	.'users`
				WHERE `email` = \''.pSQL($email).'\'
				'.(isset($passwd) ? 'AND `password` = "'.$passwd.'"' : ''));
		
		if (!$result)
			return false;
		$this->id = $result['id_user'];
		foreach ($result AS $key => $value)
			if (key_exists($key, $this))
				$this->{$key} = $value;

		return $this;
	}
	
}