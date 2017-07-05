<?php

if (isset($_POST['idSolicitacao'])){
	$idSolicitacao = $_POST['idSolicitacao'];
	$status = "Doado";
	//connection do db
	require_once('dbConnect.php');
	//update produto status
	$query = "UPDATE produto SET status = '$status' WHERE produto.pid = (SELECT solicitacao.doacao FROM solicitacao WHERE solicitacao.sid = '$idSolicitacao')";
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