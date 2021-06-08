<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "amir2451");

if($mysqli->connect_error) {
  exit('Could not connect');
}

$sql = "SELECT Sosire FROM routes WHERE Plecare = ?;";

$stmt = $mysqli->prepare($sql);
$stmt -> bind_param('s', $_GET['q']);
$stmt -> execute();
$stmt -> bind_result($result);

echo "<table>";

while($stmt -> fetch()){
  echo "<tr>";
    echo "<td>" . $result . "</td>";
  echo "</tr>";
}

echo "</table>";

$stmt->close();

?>
