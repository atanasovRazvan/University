$param1 = "PPD_Lab1_CPP.exe" # Nume fisier exe
$param2 = 1 # No of threads
$param3 = 5 # No of runs

# Executare exe in cmd mode

$suma = 0

for ($i = 0; $i -lt $param3; $i++){
    Write-Host "Rulare" ($i+1)
    $a = (cmd /c .\$param1 $param2 2`>`&1)
    Write-Host $a
    Write-Host $a[$a.length-1]
    $suma += $a[$a.length-1]
    Write-Host ""
}
$media = [double]([double]$suma / [double]$i)
#Write-Host $suma
Write-Host "Timp de executie mediu:" $media

# Creare fisier .csv
if (!(Test-Path outC.csv)){
    New-Item outC.csv -ItemType File
    #Scrie date in csv
    Set-Content outC.csv 'Tip Matrice,Tip alocare,Nr threads,Timp executie'
}

# Append
Add-Content outC.csv ",,$($param2),$($media)"

Write-Host "Press any key to continue..."
$Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")