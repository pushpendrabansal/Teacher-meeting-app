<?php
 
require_once('config.php');


 if($_SERVER['REQUEST_METHOD']=='POST'){
	 $id = $_POST['id'];

	$punu = (int)$id;

	

  $sql = "UPDATE meeting SET acceptm='1' WHERE id='$punu'";
 
 
 if(mysqli_query($con,$sql)){
 	
 	echo "meeting fix";

 }
 else{
 
 echo "error";
 
 }
 
 }else{

echo 'error';
}