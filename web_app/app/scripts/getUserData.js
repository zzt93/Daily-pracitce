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
            data = $.parseJSON(data);
            console.log(data);
            $('#DisplayName').val(data['DomainObjectkey']);
            $('#Location').val(data['Userlocation']);
            var roles = data['Userrole'].split(";");
            roles.forEach(
                function (role) {
                    $('#roles').append("<li>" + role + "</li>");
                }
            );
            $('#age').val(data['Userage']);
            $('#email').val(data['Useremail']);
            var radios = $('#gender').find('input[type="radio"]');
            radios[data['Usergender']].checked = true;
            $("#avatar").attr("src", data['Usericon_url']);
        }
    );
}