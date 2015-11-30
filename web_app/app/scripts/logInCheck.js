/**
 * Created by zzt on 11/30/15.
 */


var logState = function () {
    var hasUser = false;
    return {
        isSucc: function () {
            return hasUser;
        },
        setUser: function (u) {
            hasUser = u;
        }
    }
}();


function checkUser() {
    $.get(
        '../php/Controller/SignController.class.php',
        {
            funcName: "hasUser",
            uname: $('#name').text(),
            password: $('#pw').text()
        },
        function (data, textStatus) {
            alert("status is: " + textStatus + " Response from server: " + data);
            logState.setUser(data === 'true');
        }
    );
}


function checkLogIn() {
    if (!logState.isSucc()) {
        alert($('#failAuth').text());
    }
    return logState.isSucc();
}
