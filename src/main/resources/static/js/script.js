function toggleForm(formType) {
    const loginForm = document.getElementById("login-form");
    const signupForm = document.getElementById("signup-form");
    const loginBtn = document.getElementById("login-btn");
    const signupBtn = document.getElementById("signup-btn");
  
    if (formType === "login") {
      loginForm.style.display = "block";
      signupForm.style.display = "none";
      loginBtn.classList.add("active");
      signupBtn.classList.remove("active");
    } else {
      loginForm.style.display = "none";
      signupForm.style.display = "block";
      loginBtn.classList.remove("active");
      signupBtn.classList.add("active");
    }
  }
  
  function logoutFunction() {
    // Add your logout logic here
  }

  $(function(){
    $("#login-form").on("submit",function(event){
		var email = $("#email-login").val();
		var password = $("#password-login").val()
		var role = $("input:radio[name='login-user-type']:checked").val();
		submitLoginData(email,password,role,event);
	})
	$("#signup-form").on("submit",function(event){
		
		var username = $("#username").val();
		var email = $("#email").val();
		var number = $("#number").val();
		var password = $("#password").val();
		var role= $("input[name='signup-user-type']:checked").val();

		submitFunction(username,email,number,password,role,event);
	})
  });
  
function submitFunction(username,email,number,password,role,event){
	event.preventDefault()
	var jsonObj = {
		"name" : username,
		"email" : email,
		"mobileNumber":number,
		"password" : password,
		"role" : role
	}
	$.ajax({
		data : JSON.stringify(jsonObj),
		url :"http://localhost:8080/user-register",
		type : "post",
		processData : false,
		contentType : "application/json",
		success : function(response,errorThorwn,request){
			console.log(response)
			
			var userId=request.getResponseHeader('User-Id');
			var userName=request.getResponseHeader('User-Name');
			var role = request.getResponseHeader('Role');
			localStorage.setItem("userId",userId);
			localStorage.setItem("userName",userName);
			localStorage.setItem("role",role);
			console.log(request.getAllResponseHeaders());
			console.log(userName);
			window.location ="http://localhost:8080/Html/index.html";
		},
		error: function(textStatus){
			alert(textStatus.responseText);
		}
	})
}

function submitLoginData(email,password,role,event){
	event.preventDefault();
	var jsonObj ={
		"email" : email,
		"password" : password,
		"role" : role
	}
	$.ajax({
		data : JSON.stringify(jsonObj),
		url :"http://localhost:8080/user-login",
		type : "post",
		processData : false,
		contentType : "application/json",
		success : function(response,errorThorwn,request){
			//
			
			var userId=request.getResponseHeader('User-Id');
			var userName=request.getResponseHeader('User-Name');
			var role = request.getResponseHeader('Role');
			localStorage.setItem("userId",userId);
			localStorage.setItem("userName",userName);
			localStorage.setItem("role",role);
			console.log(request);
			console.log(userName);

			window.location ="http://localhost:8080/Html/index.html";
		},
		error: function(textStatus){
			alert(textStatus.responseText);
			
		}
	})
}
  