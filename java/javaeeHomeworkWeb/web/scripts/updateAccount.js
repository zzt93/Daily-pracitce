/**
 * Created by zzt on 2/28/16.
 *
 */

function setUserAccountInfo(submit) {
    var $input = $('input[name="gender"]');
    var $gender = $('input[name="gender"]:checked');
    var account = {
        uname: $('#DisplayName').val(),
        location: $('#Location').val(),
        age: $('#age').val(),
        gender: $input.index($gender)
    };
    updateAccount(account, submit);
}


function updateAccount(data, submit) {
    var original = $(submit).css('background-color');
    $(submit).css('background-color', '#4c84d4');

    $.post(
        'UpdateAccount',
        data,
        function (res, textStatus) {
            //console.log("status is: " + textStatus + " Response from server: " + res);
            if (textStatus === "success") {
                $(submit).css('background-color', original);
            }
        }
    );
}