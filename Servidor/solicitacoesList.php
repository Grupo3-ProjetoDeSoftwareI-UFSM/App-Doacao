<?php

if (isset($_POST['doacao']) && isset($_POST['authToken'])){
	$doacao = $_POST['doacao'];
	$authToken = $_POST['authToken'];
	$status = "Disponivel";
	
	//conecta ao banco de dados
	require_once('dbConnect.php');
	$query = "SELECT usuario.nome, solicitacao.justificativa, solicitacao.interessado, solicitacao.sid
	FROM solicitacao INNER JOIN usuario ON solicitacao.interessado = usuario.uid 
	WHERE doacao = '$doacao'";
	$result = mysqli_query($con, $query);
	if($result){
		$myArray = array();
		while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
		}
		$response["listaSolicitacao"] = $myArray;
		echo json_encode($response);
	}
	
}
?>