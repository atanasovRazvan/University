<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "amir2451");

$sql = "SELECT nrMatricol FROM student;";
$result = $mysqli -> query($sql);

echo "<select id=\"select\" onchange=\"fillform(this.value)\">";

while($row = mysqli_fetch_array($result, MYSQLI_NUM)){

  echo "<option value=\"" . $row[0] . "\"> " . $row[0] . " </option>";

}

echo "</select>";

?>
