<?php

function __autoload($className)
{
	$className = str_replace(chr(0), '', $className);
	$classDir = dirname(__FILE__).'/../classes/';
	$overrideDir = dirname(__FILE__).'/../override/classes/';
	$file_in_override = file_exists($overrideDir.$className.'.php');
	$file_in_classes = file_exists($classDir.$className.'.php');
	
	// This is a Core class and its name is the same as its declared name
	if (substr($className, -4) == 'Core')
		require_once($classDir.substr($className, 0, -4).'.php');
	else
	{
		if ($file_in_override && $file_in_classes)
		{
			require_once($classDir.str_replace(chr(0), '', $className).'.php');
			require_once($overrideDir.$className.'.php');
		}
		elseif (!$file_in_override && $file_in_classes)
		{
			require_once($classDir.str_replace(chr(0), '', $className).'.php');
			$classInfos = new ReflectionClass($className.((interface_exists($className, false) or class_exists($className, false)) ? '' : 'Core'));
			if (!$classInfos->isInterface() && substr($classInfos->name, -4) == 'Core')
				eval(($classInfos->isAbstract() ? 'abstract ' : '').'class '.$className.' extends '.$className.'Core {}');
		}
		elseif ($file_in_override && !$file_in_classes)
			require_once($overrideDir.$className.'.php');
	}
}

