<?php

if (isset($_POST['idSolicitacao']) && isset($_POST['idAvaliador']) && isset($_POST['idAvaliado']) && isset($_POST['avaliacao']) && isset($_POST['comentario'])){
	$idSolicitacao = $_POST['idSolicitacao'];
	$idAvaliado = $_POST['idAvaliado'];
	$idAvaliador = $_POST['idAvaliador'];
	$avaliacao = $_POST['avaliacao'];
	$comentario = $_POST['comentario'];
	$status = "Doado";
	
	//connection do db
	require_once('dbConnect.php');
	//insere avaliacao
	$query = "INSERT INTO avaliacao(idSolicitacao, idAvaliado, idAvaliador, avaliacao, comentario)
	VALUES('$idSolicitacao','$idAvaliado', '$idAvaliador', '$avaliacao', '$comentario')";
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