<?php

header("Access-Control-Allow-Origin: *");

$array = str_split($_GET['arr']);

$isfinished = 1;

for($i = 0; $i < 9; $i ++)
  if($array[$i] == "-")
    $isfinished = 0;

if($isfinished == 0){
  $index = rand(0, 8);
  while($array[$index] != "-")
    $index = rand(0, 8);

  $array[$index] = "0";
}

  $xwin = 0;
  $owin = 0;

  //   0 1 2
  //   3 4 5
  //   6 7 8
  // Verify the winner

  if($array[0] == 'X' and $array[1] == 'X' and $array[2] == 'X')
    $xwin = 1;

  if($array[3] == 'X' and $array[4] == 'X' and $array[5] == 'X')
    $xwin = 1;

  if($array[1] == 'X' and $array[4] == 'X' and $array[7] == 'X')
    $xwin = 1;

  if($array[0] == 'X' and $array[3] == 'X' and $array[6] == 'X')
    $xwin = 1;

  if($array[2] == 'X' and $array[5] == 'X' and $array[8] == 'X')
    $xwin = 1;

  if($array[6] == 'X' and $array[7] == 'X' and $array[8] == 'X')
    $xwin = 1;

  if($array[0] == 'X' and $array[4] == 'X' and $array[8] == 'X')
    $xwin = 1;

  if($array[2] == 'X' and $array[4] == 'X' and $array[6] == 'X')
    $xwin = 1;

  if($array[0] == '0' and $array[1] == '0' and $array[2] == '0')
      $owin = 1;

  if($array[0] == '0' and $array[3] == '0' and $array[6] == '0')
      $owin = 1;

  if($array[2] == '0' and $array[5] == '0' and $array[8] == '0')
      $owin = 1;

  if($array[6] == '0' and $array[7] == '0' and $array[8] == '0')
      $owin = 1;

  if($array[0] == '0' and $array[4] == '0' and $array[8] == '0')
      $owin = 1;

  if($array[2] == '0' and $array[4] == '0' and $array[6] == '0')
      $owin = 1;

  if($array[3] == '0' and $array[4] == '0' and $array[5] == '0')
    $owin = 1;

  if($array[1] == '0' and $array[4] == '0' and $array[7] == '0')
    $owin = 1;
  //----------------

if($isfinished == 0)
  $isfinished = 1;

for($i = 0; $i < 9; $i ++)
  if($array[$i] == "-")
    $isfinished = 0;

if($xwin == 1) echo "fx";

else if ($owin == 1) echo "f0";

else if($isfinished == 1){

  echo "fr";

}

else echo $index;
?>
