$(function() {

	var TOKEN_KEY = "X_Auth_Token";

	function setJwtToken(token) {
		localStorage.setItem(TOKEN_KEY, token);
	}

	function getJwtToken() {
		return localStorage.getItem(TOKEN_KEY);
	}

	function removeJwtToken(){
		localStorage.removeItem(TOKEN_KEY);
	}

	$("#loginForm").submit(function (event) {
		event.preventDefault();

		var $form = $(this);
		var formData = {
			username: $form.find('input[name="username"]').val(),
			password: $form.find('input[name="password"]').val()
		};
		ToLogin(formData);
	});


	function ToLogin(loginData) {
		$.ajax({
			url: "/toLogin",
			type: "POST",
			data: JSON.stringify(loginData),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {
				console.log(data.data.X_Auth_Token);
				setJwtToken(data.data.X_Auth_Token);

				showUserInformation();
			}
		});
	}

	function doLogout() {

	}

	function showUserInformation() {
		$.ajax({
			url: "/loginSuccess",
			type: "GET",
			contentType: "application/json; charset=utf-8",
			dataType: "html",
			headers: createAuthorizationTokenHeader(),
			success: function (data) {
				console.log(data);
				$('html').html(data);
				// var $userInfoBody = $userInfo.find("#userInfoBody");
				//
				// $userInfoBody.append($("<div>").text("Username: " + data.username));
				// $userInfoBody.append($("<div>").text("Email: " + data.email));
				//
				// var $authorityList = $("<ul>");
				// data.authorities.forEach(function (authorityItem) {
				// 	$authorityList.append($("<li>").text(authorityItem.authority));
				// });
				// var $authorities = $("<div>").text("Authorities:");
				// $authorities.append($authorityList);
				//
				// $userInfoBody.append($authorities);
				// $userInfo.show();
			}
		});
	}

	function createAuthorizationTokenHeader() {
		var token = getJwtToken();
		if(token){
			return {"Authorization": token};
		}else{
			return {};
		}
	}


});
