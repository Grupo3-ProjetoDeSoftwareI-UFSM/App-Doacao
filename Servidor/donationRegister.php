<?php

if (isset($_POST['tipo']) && isset($_POST['categoria']) && isset($_POST['titulo']) && isset($_POST['descricao']) && isset($_POST['status']) && isset($_POST['authToken']))	{
    $tipo = $_POST['tipo'];
    $categoria = $_POST['categoria'];
    $titulo = $_POST['titulo'];
	$descricao = $_POST['descricao'];
	$imagem = $_POST['imagem'];
	$status = $_POST['status'];
	$authToken = $_POST['authToken'];
	
	//connection do db
	require_once('dbConnect.php');
	//verifica autenticação
	$result = mysqli_query($con, "SELECT uid FROM usuario WHERE authToken = '$authToken' LIMIT 1");
	if(mysqli_num_rows($result) > 0){
		$value = mysqli_fetch_object($result);
		$uid = $value->uid;
		//store image
		if($imagem){
			$imagem_path = "images/";
			$filename = md5(uniqid(""));
			$filepath = $imagem_path.$filename.".png";
			$imagem_data = base64_decode($imagem);
			//$myfile = fopen($filePath, "wb") or die("Unable to open file!");
			file_put_contents($filepath, $imagem_data);
		}else{
			$filename = "indisponivel";
		}
		// mysql inserting a new row
		$result = mysqli_query($con, "INSERT INTO produto(tipo, categoria, titulo, descricao, imageId, status, doador)
		VALUES('$tipo', '$categoria', '$titulo', '$descricao', '$filename', '$status', '$uid')");
		// check if row inserted or not
		if ($result) {
			// successfully inserted into database
			$response["uid"] = $uid;
			$response["imageId"] = $filename;
			$response["returnCode"] = 0;
			$response["pid"] = mysqli_insert_id($con);
			$response["message"] = "Doação cadastrada com sucesso.";
			// echoing JSON response
			echo json_encode($response);
		} else {
			// failed to insert row
			$response["returnCode"] = 3;
			$response["message"] = "Erro ao cadastrar doação.";
 
			// echoing JSON response
			echo json_encode($response);
		}
	}else{
		//invalid authToken
		$response["returnCode"] = 2;
		$response["message"] = "Chave de autenticação inválida.";
		echo json_encode($response);
	}
} else {
    // required field is missing
    $response["returnCode"] = 1;
    $response["message"] = "Campo não preenchido.";
 
    // echoing JSON response
    echo json_encode($response);
}
?>