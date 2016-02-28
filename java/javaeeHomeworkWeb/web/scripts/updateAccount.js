/**
 * Created by zzt on 2/28/16.
 *
 */

function setUserAccountInfo(submit) {
    var $gender = $('#gender').find(':checked');
    var account = {
        funcName: "setUserData",
        uname: $('#DisplayName').val(),
        location: $('#Location').val(),
        age: $('#age').val(),
        gender: $('input[name="gender"]').index($gender)
    };
    updateAccount(account, submit);
}


function updateAccount(data, submit) {
    var original = $(submit).css('background-color');
    $(submit).css('background-color', 'blue');

    $.post(
        '../php/Controller/AccountController.class.php',
        data,
        function (res, textStatus) {
            //console.log("status is: " + textStatus + " Response from server: " + res);
            if (res === 'true') {
                $(submit).css('background-color', original);
            }
        }
    );
}