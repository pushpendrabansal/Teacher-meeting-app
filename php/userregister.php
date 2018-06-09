<?php
 
require_once('config.php');


 if($_SERVER['REQUEST_METHOD']=='POST'){


	 $name = $_POST['username'];
	 $email = $_POST['email'];
	 $userid = $_POST['userid'];
 
 
 	$sql1 = "SELECT * FROM users WHERE userid= '".$userid."' ";
 	$res=mysqli_query($con,$sql1);
 	$res1 = mysqli_fetch_array($res);
	 if($res1){
	 		echo "Welcome";
	 		
	 }
	else{
 		$sql = "INSERT INTO users(userid,name,email) VALUES ('$userid','$name','$email')";
 
 
			 if(mysqli_query($con,$sql)){
			 	
			 	echo "Successfully Registered";

			 }
			 else{
			 
				 echo "Could not register";
			 
			 }
			 
 	}

}
else{

echo 'error';
}