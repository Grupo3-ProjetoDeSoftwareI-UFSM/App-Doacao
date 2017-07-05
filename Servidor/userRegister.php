<?php

if (isset($_POST['nome']) && isset($_POST['tipo']) && isset($_POST['cpfcnpj']) && isset($_POST['email']) && isset($_POST['senha']) 
	&& isset($_POST['cep']) && isset($_POST['endereco']) && isset($_POST['enderecoNumero']) && isset($_POST['complemento'])
	&& isset($_POST['bairro']) && isset($_POST['cidade']) && isset($_POST['estado'])) {
    $nome = $_POST['nome'];
    $tipo = $_POST['tipo'];
    $cpfcnpj = $_POST['cpfcnpj'];
	$email = $_POST['email'];
	$upassword = $_POST['senha'];
	$cep = $_POST['cep'];
	$endereco = $_POST['endereco'];
	$numero = $_POST['enderecoNumero'];
	$complemento = $_POST['complemento'];
	$bairro = $_POST['bairro'];
	$cidade = $_POST['cidade'];
	$estado = $_POST['estado'];
	//corrige "'"
	$endereco = str_replace("'", "''", $endereco);
	$bairro = str_replace("'", "''", $bairro);
	//verifica email e cpf ja cadastrado
	require_once('dbConnect.php');
	$result = mysqli_query($con, "SELECT uid FROM usuario WHERE email = '$email' OR cpfcnpj = '$cpfcnpj' LIMIT 1");
	if(mysqli_num_rows($result) < 1){
		//crypt password
		require_once __DIR__ . '/bcrypt.php';
		$upassword = Bcrypt::hash($upassword);
		//validate email
		$hash = md5(rand(0,1000));
		$to      = $email; // Email do usuário
		$subject = 'Cadastro | Verificação'; // Assunto	
		$message = '
	 
		Olá '.$nome.'!
		Sua conta foi criada, você poderá usar o aplicativo após ter ativado sua conta apertando no link abaixo.
		
		Por favor clique nesse link para ativar sua conta:
		http://www.appdoacao.esy.es/verify.php?email='.$email.'&hash='.$hash.'
	 
		'; // Mensagem com link
						 
		$headers = 'From:noreply@appdoacao.esy.es' . "\r\n"; // Set from headers
			 
		//connection do db
		require_once('dbConnect.php');
		// mysql inserting a new row
		$result = mysqli_query($con, "INSERT INTO usuario(nome, tipo, cpfcnpj, email, upassword, cep, endereco, numero, complemento, bairro, cidade, estado, hash)
			VALUES('$nome', '$tipo', '$cpfcnpj', '$email', '$upassword', '$cep', '$endereco', '$numero', '$complemento','$bairro', '$cidade', '$estado', '$hash')");
	 
		// check if row inserted or not
		if ($result) {
			// successfully inserted into database
			mail($to, $subject, $message, $headers); // Send our email
			$response["success"] = 1;
			$response["message"] = "Usuário cadastrado com sucesso.";
	 
			// echoing JSON response
			echo json_encode($response);
		} else {
			// failed to insert row
			$response["success"] = 0;
			$response["message"] = "Erro ao cadastrar usuário. Values: '$nome', '$tipo', '$cpfcnpj', '$email', '$upassword', '$cep', '$endereco', '$numero', '$complemento','$bairro', '$cidade', '$estado', '$hash'";
	 
			// echoing JSON response
			echo json_encode($response);
		}
	}
	else{
		// required field is missing
		$response["success"] = 0;
		$response["message"] = "Email ou CPF/CNPJ já cadastrado.";
		// echoing JSON response
		echo json_encode($response);
	}
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Campo não preenchido.";
 
    // echoing JSON response
    echo json_encode($response);
}
?>