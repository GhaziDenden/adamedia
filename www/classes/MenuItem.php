<?php


class MenuItemCore extends ObjectModel
{
	public 		$id;
	public 		$id_menu_group;
	public 		$name;
	public 		$date_upd;
	public 		$date_add;
	
	protected   $tables = array ('menu_item');
	protected 	$table = 'menu_item';
	protected 	$identifier = 'id_menu_item';

	
	public	function getFields()
	{
	 	parent::validateFields();
		$fields['id_menu_item'] 	= (int)$this->id;
		$fields['id_menu_group'] 	= (int)$this->id_menu_group;
		$fields['name'] 			= pSQL($this->name);
		$fields['date_upd'] 		= pSQL($this->date_upd);
		$fields['date_add'] 		= pSQL($this->date_add);
		return $fields;
	}
	
	
	static public function getItemsByGroup($id_menu_group)
	{
		return Db::getInstance()->ExecuteS('
			SELECT *
			FROM `'._DB_PREFIX_.'menu_item`
			WHERE `id_menu_group` = '.$id_menu_group.'
			ORDER BY `id_menu_item` ASC');
	}
	
}