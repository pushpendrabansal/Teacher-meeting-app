<?php
 
require_once('config.php');


 if($_SERVER['REQUEST_METHOD']=='POST'){
	 $id = $_POST['id'];

	$punu = (int)$id;

	

  $sql = "UPDATE meeting SET acceptm='2' WHERE id='$punu'";
 
 
 if(mysqli_query($con,$sql)){
 	
 	echo "meeting cancel";

 }
 else{
 
 echo "error";
 
 }
 
 }else{

echo 'error';
}