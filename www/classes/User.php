<?php


class UserCore extends ObjectModel
{
	public 		$id;
	public 		$name;
	public 		$key;
	
	protected   $tables = array ('users');
	protected 	$table = 'users';
	protected 	$identifier = 'id_user';

	
	public	function getFields()
	{
	 	parent::validateFields();
		$fields['id_user'] 	= (int)$this->id;
		$fields['name'] 	= pSQL($this->name);
		$fields['key'] 		= pSQL($this->key);
		return $fields;
	}
	      
	
}