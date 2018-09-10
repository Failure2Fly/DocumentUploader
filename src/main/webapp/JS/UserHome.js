function DisplayAccounts() {

	var x;
	var i;
	var obj = document.getElementById("accountList");
	for (i=0;i<obj.length;i++){
		x += obj[i].businessAccountId +" "+ obj[i].accountName + "<br>";
	}
	
	
	document.getElementById("TestField").innerHTML = x;
	document.getElementById("TestField2").innerHTML = document
			.getElementById("accountList");

	document.getElementById("outputList").remove();
	var myUl = document.createElement("ul");

	obj.forEach(item)
	{

		var listItem = document.createElement("li");
		myUl.appendChild(li);

	}

}
