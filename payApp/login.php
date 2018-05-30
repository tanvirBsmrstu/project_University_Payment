<?php
	require "init.php";
	
	$user_id=$_POST["userId"];
	$user_pass=$_POST["pass"];
	$table_name= "stu_info";
	
	$sql_query= "SELECT name FROM ".$table_name. " WHERE id='".$user_id."' && pass='".$user_pass."' ;" ;
	$result=mysqli_query($con,$sql_query);
	
	if(mysqli_num_rows($result)>0){
		$row= mysqli_fetch_assoc($result);
		$name= $row["name"];
		echo " hello ". $name;
	}
	else {
		echo " no available info  ";
	}
	

?>