<?php

if (isset($_POST['idSolicitacao'])){
	$idSolicitacao = $_POST['idSolicitacao'];
	
	//conecta ao banco de dados
	require_once('dbConnect.php');
	$query = "SELECT avaliacao.*
	FROM solicitacao INNER JOIN avaliacao ON solicitacao.interessado = avaliacao.idAvaliado
	WHERE solicitacao.sid = '$idSolicitacao'";
	$result = mysqli_query($con, $query);
	if($result){
		$myArray = array();
		while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
		}
		$response["listaAvaliacao"] = $myArray;
		echo json_encode($response);
	}
	
}
?>