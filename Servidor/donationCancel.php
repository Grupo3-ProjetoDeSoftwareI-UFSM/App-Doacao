<?php

if (isset($_POST['doacao']) && isset($_POST['authToken'])){
	$doacao = $_POST['doacao'];
	$authToken = $_POST['authToken'];
	//conecta ao banco de dados
	require_once('dbConnect.php');
	$queryverifica = "SELECT * FROM solicitacao INNER JOIN usuario ON solicitacao.interessado = usuario.uid WHERE doacao = '$doacao' AND authToken = '$authToken'";
	$result = mysqli_query($con, $queryverifica);
	if($result){
		$query = "DELETE FROM produto WHERE pid = '$doacao'";
		$result = mysqli_query($con, $query);
		$response["success"] = 0;
		echo json_encode($response);
	}
	else{
		$response["success"] = 1;
		echo json_encode($response);
	}
}
else{
	$response["success"] = 1;
	echo json_encode($response);
}
	
?>