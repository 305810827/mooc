$(function () {

    var TOKEN_KEY = "Authorization";

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function removeJwtToken() {
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
            success: function (data, textStatus, jqXHR) {
                console.log(data.token);
                if(data.token === "usernameNoExist")
                {
                    alert("账号错误");
                    return false;
                }
                else if(data.token === "falsePassword"){
                    alert("密码错误");
                    return false;
                }
                setJwtToken(data.token);
                showUserInformation();
                // if(data.roleId == 1){
                //     showUserInformation("/user");
                // }else if(data.roleId == 2){
                //     showUserInformation("/courseList");
                // }else if(data.roleId == 3){
                //     showUserInformation("/course");
                // }

            },
            fail: function (data) {
                console.log(data);
            }
        });
    }

    function doLogout() {

    }

    // function standardPost (url, args)
    // {
    //     var form = $("<form method='post'></form>");
    //     form.attr({"action":url});
    //     for (arg in args)
    //     {
    //         var input = $("<input type='hidden'>");
    //         input.attr({"name":arg});
    //         input.val(args[arg]);
    //         form.append(input);
    //     }
    //     $("html").append(form);
    //     form.submit();
    // }
    //
    function showUserInformation() {
        $.ajax({
            url: "/loginSuccess",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data) {
                //$('html').html(data);
                console.log(data)
                if (data.roleId === 1){
                    self.location.href = "/course?username="+data.username;
                }
                else if (data.roleId === 2) {
                    self.location.href = "/courseList?username="+data.username;
                }
                else if(data.roleId === 3){
                    self.location.href="/loginSuccess/user?username="+data.username;
                }
                //self.location.href="/user";
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
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }


});
