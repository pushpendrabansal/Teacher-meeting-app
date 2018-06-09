<?php
 
require_once('config.php');

if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $id  = $_GET['emailt'];

$sql = "SELECT * FROM meeting WHERE acceptm=1 and (emailt='".$id."' OR emails='".$id."')  ORDER BY datem DESC ";

$result = $con->query($sql);

 
// $res = mysqli_fetch_array($r);
 
// $result = array();
 
 // array_push($result,array(
	//  "names"=>$res['names'],
	//  "emails"=>$res['emails'],
	//  "msg"=>$res['msg'],
	//  "datem"=>$res['datem'],
	//  "timem"=>$res['timem']
	//   )
 // );
 
//  echo json_encode(array("result"=>$result));


		if ($result->num_rows >0) {
 
			 while($row[] = $result->fetch_assoc()) {
 
 				$tem = $row;
 
				 $json = json_encode($tem);
 
 			}
 		}


 		else {
 			echo "No Results Found.";
		}
	echo $json;
	$con->close();
}
 else{
 	echo "error";
 }

 