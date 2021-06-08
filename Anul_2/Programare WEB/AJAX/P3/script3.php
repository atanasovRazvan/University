<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "amir2451");

$sql = "UPDATE student SET Nume=?, Prenume=?, nrTelefon=? WHERE nrMatricol=?;";
$stmt = $mysqli->prepare($sql);
$stmt -> bind_param("sssd", $_GET['lname'], $_GET['fname'], $_GET['pnum'], $_GET['id']);
$stmt -> execute();

 ?>
