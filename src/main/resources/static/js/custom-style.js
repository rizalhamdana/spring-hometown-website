	function setActiveLink(link){
		var activeLink = document.getElementById(link);
		var allLink = document.getElementsByClassName("nav-item");
		
		for(var i = 0; i< allLink.length ; i++){
			allLink[i].setAttribute("class", "nav-item")
		}
		activeLink.setAttribute("class", "nav-item active");
	}
	
	function validate(formId, alertId){
		var isValid=true;
		form = document.getElementById(formId);
		error_span = document.getElementById(alertId);
		if(form.type==="text" || form.type==="password"){
			if(form.value.length <= 2){
				error_span.innerHTML = "* cannot empty and should be more than three characters";
				isValid=false;
			}
			if(formId === "number-form" || formId==="post-form"){
				if(isNaN(form.value)){
					error_span.innerHTML = "* should be numeric"
					isValid=false;
				}
			}	
		}
		
		else if(form.type==="number"){
			if(form.value <= 0){
				error_span.innerHTML = "* cannot empty and the value should be more than 0";	
				isValid=false;
				
			}
		}
		if (isValid){
			error_span.innerHTML="";
		}
		
	}
	
	function validation(){
		var check_alert = true;
		error = document.getElementsByClassName('alert');
		for (var i=0; i<error.length; i++){
			if(error[i].innerHTML !==""){
				check_alert=false;
				break;
			}
		}
		console.log(check_alert)
		if(check_alert){
			return true
		}else{
			alert("Your input is not valid, please check the alert on the form")
			return false;
		}	
	}
	
	function checkSame(passwordID, confirmPasswordID){
		var formPassword = document.getElementById(passwordID);
		var formConfirm = document.getElementById(confirmPasswordID);
		var error_span = document.getElementById(confirmPasswordID+"-alert")
		var pass = formPassword.value;
		var confirmPass = formConfirm.value;
		
		if(pass !== confirmPass){
			error_span.innerHTML = "* Your confirm password is invalid";	
			return false
		}else {
			return true
		}
	}
	
	function changeAction(page){
		var form = document.getElementById("myForm");
		form.action = page;
		console.log(form.getAttribute("action"));
	}