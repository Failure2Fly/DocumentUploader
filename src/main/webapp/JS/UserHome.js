
function DisplayAccounts(){
	
	var  x;
	
	

	
		var obj = document.getElementById('accountList');
		x = obj[0].accountName;
		document.getElementById("TestField").innerHTML = x;
		document.getElementById("TestField2").innerHTML = document.getElementById('accountList');
	   document.getElementById('outputList').remove();
	   var myUl = document.createElement('ul');

	   obj.forEach(item) {

	       var listItem = document.createElement('li');
	       myUl.appendChild(li);

	   } 


		
		
}



