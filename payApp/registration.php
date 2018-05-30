<?php
require "init.php";

$user_name=$_POST["name"];
$user_id=$_POST["userId"];
$user_pass= $_POST["pass"];
$user_email=$_POST["email"];
$user_mob=$_POST["mob"];
$table_name="stu_info";

/*
$user_name="jo";
$user_id="20122123";
$user_pass= "45456";
$user_email="454";
$user_mob="10231651";
$table_name="stu_info";
*/

	

	// to handle duplicate registration
	$result= mysqli_query($con,"SELECT id FROM ".$table_name." WHERE id='".$user_id."' ;");
	
	if(mysqli_num_rows($result)>0){
		echo "already exists ";
	}
	else{
	
		$sql_query= "INSERT INTO " . $table_name . " (id,name,email,mob,pass) ". " VALUES('$user_id','$user_name','$user_email','$user_mob','$user_pass') ;" ;

		$is_ok = mysqli_query($con,$sql_query); // return true or false 
		
		if($is_ok){
			echo " INSERT sucess...";
		}
		else {
			echo " INSERT error ". mysqli_error($con);
		}
	}

?>