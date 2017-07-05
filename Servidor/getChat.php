<?php

if (isset($_POST['idSolicitacao']) && isset($_POST['lastMessage'])){
	$idSolicitacao = $_POST['idSolicitacao'];
	$lastMessage = $_POST['lastMessage'];
	
	//conecta ao banco de dados
	require_once('dbConnect.php');
	$query = "SELECT chat.mid, chat.idSolicitacao, chat.idRemetente, usuario.nome AS nomeRemetente, chat.mensagem  
	FROM chat INNER JOIN usuario ON chat.idRemetente = usuario.uid 
	WHERE idSolicitacao = '$idSolicitacao' AND mid > '$lastMessage'";
	$result = mysqli_query($con, $query);
	if($result){
		$myArray = array();
		while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
		}
		$response["mensagemList"] = $myArray;
		echo json_encode($response);
	}
	else{
		$response["mensagemList"] = [];
		echo json_encode($response);
	}
	
}
?>