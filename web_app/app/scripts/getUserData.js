/**
 * Created by zzt on 12/1/15.
 */

function getUserAccountInfo() {
    $.get(
        '../php/Controller/AccountController.class.php',
        {
            funcName: "getUserData"
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            data = eval(data);
            console.log(data);
            $('#DisplayName').val(data['useName']);
            $('#Location').val(data['location']);
            var roles = data[''];
            for (var role in roles.split(";")) {
                $('#roles').appendChild("<li>" + role + "</li>");
            }
            $('#age').val(data['age']);
            $('#email').val(data['email']);
            $('#gender').next('input[type="radio"]');
            $("#avatar").attr("src", data['icon_url']);
        }
    );
}