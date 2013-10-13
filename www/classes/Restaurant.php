<?php


class RestaurantCore extends ObjectModel
{
	public 		$id;
	public 		$name;
	public 		$address;
	public 		$city;
	public 		$zipcode;
	public 		$country;
	public 		$type;
	public 		$phone;
	public 		$email;
	public 		$latitude;
	public 		$longitude;
	public 		$date_add;
	public 		$date_upd;
	
	protected   $tables = array ('restaurants');
	protected 	$table = 'restaurants';
	protected 	$identifier = 'id_restaurant';

	
	public	function getFields()
	{
	 	parent::validateFields();
		$fields['id_restaurant'] 	= (int)$this->id;
		$fields['name'] 			= pSQL($this->name);
		$fields['address'] 			= pSQL($this->address);
		$fields['city'] 			= pSQL($this->city);
		$fields['zipcode'] 			= pSQL($this->zipcode);
		$fields['country'] 			= pSQL($this->country);
		$fields['type'] 			= pSQL($this->type);
		$fields['phone'] 			= pSQL($this->phone);
		$fields['email'] 			= pSQL($this->email);
		$fields['latitude'] 		= (double)$this->latitude;
		$fields['longitude'] 		= (double)$this->longitude;
		$fields['date_add'] 		= pSQL($this->date_add);
		$fields['date_upd'] 		= pSQL($this->date_upd);
		return $fields;
	}
	
	
	static public function getRestaurantsByName($name)
	{
		$restaurants = Db::getInstance()->ExecuteS('
				SELECT *
				FROM `'._DB_PREFIX_.'restaurants`
				WHERE `name` LIKE "%'.$name.'%"
				ORDER BY `id_restaurant` ASC');
		
		$i = 0;
		foreach($restaurants as $restaurant){
			$restaurants[$i]['thumb'] = Image::getThumbnailByRestaurant($restaurant['id_restaurant']);
			$restaurants[$i]['thumb'] = $restaurants[$i]['thumb']['id_image'];
			$restaurants[$i]['images'] = Image::getImageByRestaurant($restaurant['id_restaurant']);
			$i++;
		}
		return $restaurants;
	}
	
	
	static public function getRestaurantsByAddress($address)
	{
		return Db::getInstance()->ExecuteS('
			SELECT *
			FROM `'._DB_PREFIX_.'restaurants`
			WHERE `address` LIKE "%'.$address.'%"
			ORDER BY `id_restaurant` ASC');
	}
	
	
	static public function getRestaurantsByPosition($latitude, $longitude)
	{
		// @todo
		return NULL;
	}
	
	
}