<?php


class MenuGroupCore extends ObjectModel
{
	public 		$id;
	public 		$id_restaurant;
	public 		$name;
	public 		$date_upd;
	public 		$date_add;
	
	protected   $tables = array ('menu_group');
	protected 	$table = 'menu_group';
	protected 	$identifier = 'id_menu_group';

	
	public	function getFields()
	{
	 	parent::validateFields();
		$fields['id_menu_group'] 	= (int)$this->id;
		$fields['id_restaurant'] 	= (int)$this->id_restaurant;
		$fields['name'] 			= pSQL($this->name);
		$fields['date_upd'] 		= pSQL($this->date_upd);
		$fields['date_add'] 		= pSQL($this->date_add);
		return $fields;
	}
	
	
	static public function getGroupByRestaurant($id_restaurant)
	{
		return Db::getInstance()->ExecuteS('
			SELECT *
			FROM `'._DB_PREFIX_.'menu_group`
			WHERE `id_restaurant` = '.$id_restaurant.'
			ORDER BY `id_menu_group` ASC');
	}
	
}