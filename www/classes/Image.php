<?php


class ImageCore extends ObjectModel
{
	public 		$id;
	public 		$id_restaurant;
	public 		$date_add;
	
	protected   $tables = array ('images');
	protected 	$table = 'images';
	protected 	$identifier = 'id_image';

	
	public	function getFields()
	{
	 	parent::validateFields();
		$fields['id_image'] 		= (int)$this->id;
		$fields['id_restaurant'] 	= (int)$this->id_restaurant;
		$fields['date_add'] 		= pSQL($this->date_add);
		return $fields;
	}
	
	
	static public function getImageByRestaurant($id_restaurant)
	{
		return Db::getInstance()->ExecuteS('
			SELECT *
			FROM `'._DB_PREFIX_.'images`
			WHERE `id_restaurant` = '.$id_restaurant.'
			ORDER BY `id_image` ASC');
	}
	
	static public function getThumbnailByRestaurant($id_restaurant)
	{
		return Db::getInstance()->getRow('
			SELECT id_image
			FROM `'._DB_PREFIX_.'images`
			WHERE `id_restaurant` = '.$id_restaurant);
	}
	
	
}