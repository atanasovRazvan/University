<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "amir2451");


$sql2 = "SELECT * FROM student WHERE nrMatricol = ?;";
$stmt = $mysqli->prepare($sql2);
$stmt -> bind_param('d', $_GET['id']);
$stmt -> execute();
$stmt -> bind_result($aux, $col1, $col2, $col3);

echo "<form>";

while($stmt -> fetch()){

  echo "<label for=\"fname\">First name:</label><br>";
  echo "<input type=\"text\" id=\"fname\" name=\"fname\" value=" . $col2 . "><br>";
  echo "<label for=\"lname\">Last name:</label><br>";
  echo "<input type=\"text\" id=\"lname\" name=\"lname\" value=" . $col1 . "><br>";
  echo "<label for=\"pnum\">Phone number:</label><br>";
  echo "<input type=\"text\" id=\"pnum\" name=\"pnum\" value=" . $col3 . "><br>";

}

echo "<input type=\"submit\" value=\"Submit\" onclick=\"save()\"";
echo "</form>";


 ?>
