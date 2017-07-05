<?php

if (isset($_POST['uid']) && isset($_POST['authToken']) && isset($_POST['status'])){
	$uid = $_POST['uid'];
	$authToken = $_POST['authToken'];
	$status = $_POST['status'];
	
	//conecta ao banco de dados
	require_once('dbConnect.php');
	$query = "SELECT produto.* 
	FROM produto INNER JOIN usuario ON produto.doador = usuario.uid 
	WHERE doador = '$uid' AND authToken = '$authToken' AND status = '$status'";
	$result = mysqli_query($con, $query);
	if($result){
		$myArray = array();
		while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
		}
		$response["listaProduto"] = $myArray;
		echo json_encode($response);
	}
	
}
?>