<?php

$busca = $_POST['nome'];
$tipo = $_POST['tipo'];
$categoria = $_POST['categoria'];

/*if(strlen($busca) < 1){
	$busca = '%'
}
if(strlen($tipo) < 1){
	$tipo = '%'
}
if(strlen($categoria) < 1){
	$categoria = '%'
}*/

$query = "SELECT * FROM produto WHERE titulo LIKE '%$busca%' AND tipo LIKE '%$tipo%' AND categoria LIKE '%$categoria%' ORDER BY datahora ASC LIMIT 100";

//connection do db
require_once('dbConnect.php');
$result = mysqli_query($con, $query);
$myArray = array();
if ($result) {
    while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
    }
	$response["listaProduto"] = $myArray;
    echo json_encode($response);
}

?>