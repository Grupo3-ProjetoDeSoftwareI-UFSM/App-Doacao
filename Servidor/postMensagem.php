<?php

if (isset($_POST['idSolicitacao']) && isset($_POST['idRemetente']) && isset($_POST['message'])){
	$idSolicitacao = $_POST['idSolicitacao'];
	$idRemetente = $_POST['idRemetente'];
	$message = $_POST['message'];
	
	$query = "INSERT INTO chat(idSolicitacao, idRemetente, mensagem) 
	VALUES('$idSolicitacao','$idRemetente', '$message')";
	//connection do db
	require_once('dbConnect.php');
	$result = mysqli_query($con, $query);
	if($result){
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