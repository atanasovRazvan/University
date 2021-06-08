$file = $args[0] # Nume fisier java

$runtype = $args[1]

if($runtype -eq "check"){
    $a = java $file $runtype
    Write-Host $a
    return
}


$N = $args[2] # N value

$M = $args[3] # M value

$n = $args[4] # n, m value

if($runtype -eq "create"){
    $a = java $file $runtype $N $M $n
    Write-Host $a
    return
}

$p = $args[5] # no threads

$noRuns = $args[6] # no runs

# Executare class Java

$suma = 0

for ($i = 0; $i -lt $noRuns; $i++){
    $a = java $file $runtype $N $M $n $p # rulare class java
    $suma += [int]$a
    Write-Host "Rulare" ($i+1)  $a
}

$media = $suma / $i
Write-Host "Timp de executie mediu:" $media

# Creare fisier .csv
if (!(Test-Path outJava.csv)){
    New-Item outJava.csv -ItemType File
    #Scrie date in csv
    Set-Content outJava.csv 'Rulare,N,M,n,m,p,Time'
}

# Append
Add-Content outJava.csv "$($args[1]),$($args[2]),$($args[3]),$($args[4]),$($args[4]),$($args[5]),$($media)"