$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$("#login-form").on("submit",function(event){
		var email = $("#email-login").val();
		var password = $("#password-login").val()
		var role = $("input:radio[name='role-login']:checked").val();
		submitLoginData(email,password,role,event);
	})
	$("#register-form").on("submit",function(event){
		
		var username = $("#username").val();
		var email = $("#email").val();
		var number = $("#number").val();
		var password = $("#password").val();
		var role= $("input[name='role-registration']:checked").val();

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
		success : function(response,request){
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
			//setCookie()
			var userId=request.getResponseHeader('User-Id');
			var userName=request.getResponseHeader('User-Name');
			var role = request.getResponseHeader('Role');
			//var sessionId = request.getResponseHeader('Session-Id');
			
			localStorage.setItem("userId",userId);
			localStorage.setItem("userName",userName);
			localStorage.setItem("role",role);
			console.log(request.getAllResponseHeaders());
			console.log(userName);
			//window.location ="http://127.0.0.1:5500/Html/index.html";
		},
		error: function(textStatus){
			alert(textStatus.responseText);
			
		}
	})
}
function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
