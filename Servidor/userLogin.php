<?php

if (isset($_POST['email']) && isset($_POST['password'])) {
	$email = $_POST['email'];
	$password = $_POST['password'];
	//connection do db
	require_once('dbConnect.php');
	require_once __DIR__ . '/bcrypt.php';
	$result = mysqli_query($con, "SELECT uid, upassword, active FROM usuario WHERE email = '$email' LIMIT 1");
	if(mysqli_num_rows($result) > 0){
		$value = mysqli_fetch_object($result);
		$hash = $value->upassword;
		if(Bcrypt::check($password, $hash)){
			$active = $value->active;
			if($active == 1){
				$authToken = md5(uniqid());
				$insertToken = mysqli_query($con, "UPDATE usuario SET authToken = '$authToken' WHERE email = '$email'");
				if($insertToken){
					//login efetuado com sucesso
					$response["uid"] = $value->uid;
					$response["loginStatus"] = 1;
					$response["authToken"] = $authToken;
				}
				else{
					//erro de conexão ao banco de dados
					$response["loginStatus"] = 4;
				}
			}
			else{
				$response["loginStatus"] = 6;
			}
		}
		else{
			//senha incorreta
			$response["loginStatus"] = 3;
		}
	}else{
		//email não registrado
		$response["loginStatus"] = 2;
	}
	echo json_encode($response);
} else {
    // required field is missing
    $response["loginStatus"] = 5;
    $response["message"] = "Campo não preenchido.";
 
    // echoing JSON response
    echo json_encode($response);
}
	

?>