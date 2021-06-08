$noRuns = $args[0]

$average = 0

for($i = 0; $i -lt $noRuns; $i++){
    
    $startTime = (Get-Date).Millisecond

    mpiexec -n 4 MPI.exe

    $endTime = (Get-Date).Millisecond

    $diff = $endTime - $startTime
    Write-Host $diff
    $average += $diff

}

$media = $average/$noRuns
Write-Host "Timp executie mediu: " $media