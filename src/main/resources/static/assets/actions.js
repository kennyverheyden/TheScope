function show_alert(index) {
 if(document.getElementById(index).checked == true)
 {
	 
  	if(confirm("Do you really want to check for delete?")) {
		  document.getElementById(index).checked = true;
	  }
  		else
  		{
  document.getElementById(index).checked = false;
	}
}
}
	

function changeSubmitButton(index)
{
	if(document.getElementById(index).checked)
	{
	document.getElementById("submit"+index).innerHTML="DELETE user";
	}
	else
	document.getElementById("submit"+index).innerHTML="Update details";
}