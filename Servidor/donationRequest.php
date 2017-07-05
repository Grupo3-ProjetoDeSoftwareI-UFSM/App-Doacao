<?php

$doacao = $_POST['doacao'];
$interessado = $_POST['interessado'];
$justificativa = $_POST['justificativa'];

$query = "INSERT INTO solicitacao(doacao, interessado, justificativa) VALUES('$doacao', '$interessado', '$justificativa')";

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

?>