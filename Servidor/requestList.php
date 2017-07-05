<?php

if (isset($_POST['uid']) && isset($_POST['authToken'])){
	$uid = $_POST['uid'];
	$authToken = $_POST['authToken'];
	
	//conecta ao banco de dados
	require_once('dbConnect.php');
	$query = "SELECT produto.*, solicitacao.sid 
	FROM usuario INNER JOIN solicitacao ON usuario.uid = solicitacao.interessado 
	INNER JOIN produto ON solicitacao.doacao = produto.pid
	WHERE uid = '$uid' AND authToken = '$authToken'";
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