<?PHP
$server_name="localhost";
$database_name="webapp";
$mysql_username="root";
$mysql_pass="";

$con= mysqli_connect($server_name,$mysql_username,$mysql_pass,$database_name);
if($con !=null){
	echo "database access sucessfull ...";
}
else echo " database connection error .. ".mysqli_connect_error();

?>