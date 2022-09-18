/**
 * 
 */
 
function toggleExcericeSideBar(idName) {
  var x = document.getElementById(idName);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}


function openNav() {
  document.getElementById("mySidenav").style.width = "15%";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}

function dynamicOpenNav() {
  document.getElementById("mySidenav").style.width = "15%";

}

function dynamicCloseNav() {
  document.getElementById("mySidenav").style.width = "0";
}
