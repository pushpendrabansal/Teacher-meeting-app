<?php
session_start();
$host = "45.58.119.114"; 
$user = "lusiplnmiit"; 
$pass = "Lusip123"; 
$db = "lusip";
$con=new mysqli($host,$user,$pass,$db) or die("Unable to Connect.".mysql_error());
?>
