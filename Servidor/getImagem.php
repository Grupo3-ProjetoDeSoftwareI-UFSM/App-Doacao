<?php
if (isset($_POST['imageId']))	{
    $filename = $_POST['imageId'];
	//get image
	$imagem_path = "images/";
	$filepath = $imagem_path.$filename.".png";
	$imagedata = file_get_contents($filepath);
	if($imagedata){
		$image = base64_encode($imagedata);
		$response["returnCode"] = 0;
		$response["message"] = "Sucess";
		$response["image"] = $image;
		echo json_encode($response);
	}
	else{
		$response["returnCode"] = 1;
		$response["message"] = "Imagem não cadastrada.";
		echo json_encode($response);
	}
}
?>