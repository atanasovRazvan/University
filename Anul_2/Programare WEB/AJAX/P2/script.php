<?php

header("Access-Control-Allow-Origin: *");


$mysqli = new mysqli("localhost", "root", "", "amir2451");

if($mysqli->connect_error) {
  exit('Could not connect');
}

$sql = "SELECT * FROM subscriber;";

$result = $mysqli->query($sql);

echo "<table>";

$count = 0;
$isThereMore = 0;
$atBeginning = 0;
if($_GET['id'] == 0)
  $atBeginning = 1;

while($row = mysqli_fetch_array($result, MYSQLI_NUM)){

  if($count < $_GET['id'] * 3);
  else
    if($count < ($_GET['id']+1) *3){
      echo "<tr>";
      echo "<td>" . $row[0] . "</td>";
      echo "<td>" . $row[1] . "</td>";
      echo "<td>" . $row[2] . "</td>";
      echo "<td>" . $row[3] . "</td>";
      echo "</tr>";
    }
    else $isThereMore = 1;

  $count = $count + 1;
}

echo "</table>";
if($atBeginning == 1)
  echo "<button disabled onclick=\"prev()\"> Prev </button>";
else
  echo "<button onclick=\"prev()\"> Prev </button>";
if($isThereMore == 0)
  echo "<button disabled onclick=\"next()\"> Next </button>";
else
  echo "<button onclick=\"next()\"> Next </button>";

?>
