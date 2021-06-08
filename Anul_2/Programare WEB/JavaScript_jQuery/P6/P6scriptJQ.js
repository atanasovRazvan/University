function shuffle(array) {
  var tmp, current, top = array.length;
  if(top) while(--top) {
    current = Math.floor(Math.random() * (top + 1));
    tmp = array[current];
    array[current] = array[top];
    array[top] = tmp;
  }
  return array;
}

$(document).ready(function(){

  for (var a=[],i=0;i<9;++i) a[i]=i;
  a = shuffle(a);

  var list = document.getElementsByTagName("td");
  for(var i = 0; i < list.length; i ++){
    if(a[i] == 0) {
      list[i].innerHTML = "";
      continue;
    }
    list[i].innerHTML = a[i];
  }

  $(document).keydown(function(e){

    e = e || window.event;
    var list = document.getElementsByTagName("td");
    var pozx = 0, pozy = 0;
    for(var i = 0; i < list.length; i ++){
      if(list[i].innerHTML == ""){
        pozx = i%3;
        pozy = Math.floor(i/3);
      }
    }

    if (e.keyCode == '38' && pozy > 0) {
        // up arrow
        [list[pozy*3+pozx].innerHTML, list[(pozy-1)*3+pozx].innerHTML] = [list[(pozy-1)*3+pozx].innerHTML, list[pozy*3+pozx].innerHTML];
    }
    else if (e.keyCode == '40' && pozy < 2) {
        // down arrow
        [list[pozy*3+pozx].innerHTML, list[(pozy+1)*3+pozx].innerHTML] = [list[(pozy+1)*3+pozx].innerHTML, list[pozy*3+pozx].innerHTML];
    }
    else if (e.keyCode == '37' && pozx > 0) {
        // left arrow
        [list[pozy*3+pozx].innerHTML, list[pozy*3+(pozx-1)].innerHTML] = [list[pozy*3+(pozx-1)].innerHTML, list[pozy*3+pozx].innerHTML];
    }
    else if (e.keyCode == '39' && pozx < 2) {
        // right arrow
        [list[pozy*3+pozx].innerHTML, list[pozy*3+pozx+1].innerHTML] = [list[pozy*3+pozx+1].innerHTML, list[pozy*3+pozx].innerHTML];
    }

  });

});
