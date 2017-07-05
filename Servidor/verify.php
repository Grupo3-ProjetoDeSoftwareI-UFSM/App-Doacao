<?php

if(isset($_GET['email']) && !empty($_GET['email']) AND isset($_GET['hash']) && !empty($_GET['hash'])){
    // Verify data
    $email = $_GET['email']; // Set email variable
    $hash = $_GET['hash']; // Set hash variable
	//connection do db
	require_once('dbConnect.php');
	//$query = "SELECT email, hash, active FROM usuario WHERE email='".$email."' AND hash='".$hash."' AND active='0'" or die("Email não encontrado ou já ativado" . mysqli_error($db));
	$query = "SELECT email, hash, active FROM usuario WHERE email='$email' AND hash='$hash' AND active='0'";
	$search = mysqli_query($con, $query);
	if(mysqli_num_rows($search) > 0){
		mysqli_query($con, "UPDATE usuario SET active='1' WHERE email='$email' AND hash='$hash' AND active='0'");
		echo '<div class="statusmsg">Sua conta foi ativada, você já pode usar o aplicativo.</div>';
	}
	else{
		echo '<div class="statusmsg">Conta não encontrada.</div>';
	}
	
}
?>