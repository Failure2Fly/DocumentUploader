function RegisterValidate() {
    var first_name = document.querySelector(".first-name").value;
    var last_name = document.querySelector(".last-name").value;
    var user_name = document.querySelector(".user-name").value;
    var email = document.querySelector(".email").value;
    var password = document.querySelector(".password").value;;
    var password_confirm = document.querySelector(".password-confirm").value;

    if (first_name == "") {
        window.alert("Please enter your first name");
        return false;
    }

    if (last_name == "") {
        window.alert("Please enter your last name");
        return false;
    }

    if (user_name == "") {
        window.alert("Please enter a username");
        return false;
    }

    if (email.indexOf("@", 0) < 0) {
        window.alert("Please enter a valid e-mail address");
        email.focus();
        return false;
    }

    if (email.indexOf(".", 0) < 0) {
        window.alert("Please enter a valid e-mail address");
        email.focus();
        return false;
    }

    if (password == "") {
        window.alert("Please enter your password");
        return false;
    }

    if (password_confirm == "") {
        window.alert("Please confirm your password");
        return false;
    }

    if (password_confirm !== password) {
        window.alert("Passwords do not match");
        return false;
    }
    else{
    return true;
    }
}
function cancelDisplayProperty(){
	if (info.style.removeProperty) {
	    info.style.removeProperty('display');
	} else {
	    info.style.removeAttribute('display');
	}
}
function loginValidate(){
    var user_name = document.querySelector(".user-name").value;
    var password = document.querySelector(".password").value;

    if (user_name == "") {
        window.alert("Please enter a username");
        return false;
    }
    if (password == "") {
        window.alert("Please enter your password");
        return false;
    }
    else{
    	return true;
    }
    

function TradeFormValidate(){
	
	}

	function checkAlert(message, username){
		var success = "Success, Welcome ";
		success += username;
		if(message == success){
			window.alert(message);
			return true;
		}
		else{
			window.alert(message);
			return false;
		}
		
	}

}
