<?php
 
require_once('config.php');


 if($_SERVER['REQUEST_METHOD']=='POST'){


	 $datem = $_POST['datem'];
	 $timem = $_POST['timem'];
	 $emailt = $_POST['emailt'];
	$emails = $_POST['emails'];
	$names = $_POST['names'];		 
	 $msg = $_POST['msg'];
	
 
$sql1 = "SELECT * FROM users WHERE email='".$emailt."' ";

$result1 = $con->query($sql1);

if ($result1->num_rows >0) {

$sql = "INSERT INTO meeting(datem,timem,emailt,emails,names,msg,acceptm) VALUES ('$datem','$timem','$emailt','$emails','$names','$msg',0)";
 
 
 if(mysqli_query($con,$sql)){
 	
 	echo "Meeting Request sent !";

 }
 else{
 
 echo "Could not register";
 
 }

}
else{




echo "email id is not register";
}






 
 }else{

echo 'error';
}