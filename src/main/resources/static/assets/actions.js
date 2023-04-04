function show_alert(index) {
  if(confirm("Do you really want to check for delete?")) {
	  document.getElementById(index).checked = true;
  }
  else
  document.getElementById(index).checked = false;
}