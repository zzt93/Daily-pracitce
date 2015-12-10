/**
 * Created by zzt on 11/30/15.
 */


var logState = function () {
    var hasUser = false;
    var trial = 0;
    return {
        isSucc: function () {
            trial++;
            return hasUser;
        },
        setUser: function (u) {
            hasUser = u;
        },
        firstTrial: function () {
            return trial === 1;
        }
    }
}();


function checkUser() {
    var name = $("#name");
    var pw = $("#pw");
    $.get(
        '../php/Controller/SignController.class.php',
        {
            funcName: "hasUser",
            uname: name.val(),
            password: pw.val()
        },
        function (data, textStatus) {
            console.log("status is: " + textStatus + " Response from server: " + data);
            logState.setUser(data === 'true');
        }
    );
}


function checkLogIn() {
    if (!logState.isSucc() && logState.firstTrial()) {
        var $failAuth = $('#failAuth');
        $failAuth.after('<section> wrong password or user name</section>');
        $failAuth.css('font-size', '1.5em');
    }
    return logState.isSucc();
}
