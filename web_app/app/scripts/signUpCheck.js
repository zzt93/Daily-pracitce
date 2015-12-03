/**
 * Created by zzt on 11/15/15.
 */

var password;

var signState = function () {
    var same = false;
    var pwLen = false;
    var hasUser = false;
    return {
        isSucc: function () {
            return same && pwLen && !hasUser;
        },
        setState: function (s) {
            same = s;
        },
        setLen: function (l) {
            pwLen = l;
        },
        setUser: function (u) {
            hasUser = u;
        },
        hasUser: function () {
            return hasUser;
        }
    }
}();

function checkUserName(input) {
    var value = input.value;
    $.get(
        '../php/Controller/SignController.class.php',
        {
            funcName: "hasUserName",
            uname: value
        },
        function (data, textStatus) {
            //alert("status is: " + textStatus + " Response from server: " + data);
            console.log("status is: " + textStatus + " Response from server: " + data);
            signState.setUser(data === "true");
        }
    );
}

function validatePassword(input) {
    var value = input.value;
    if (value.length < 6) {
        $(input).data('tooltip').getTip().text("too short password");
        $(input).css('color', 'red');
        signState.setLen(false);
    } else {
        $(input).data('tooltip').getTip().text("Must be at least 6 characters");
        $(input).css('color', 'black');
        signState.setLen(true);
    }
    password = input.value;
}

function samePassword(input) {
    if (password !== input.value) {
        $(input).data('tooltip').getTip().text("Passwords doesn't match");
        $(input).css('color', 'red');
        signState.setState(false);
    } else {
        $(input).data('tooltip').getTip().text("Please confirm your password");
        $(input).css('color', 'black');
        signState.setState(true);
    }
}

function checkSign() {
    var text;
    var color;
    if (signState.hasUser()) {
        text = 'Already used user name';
        color = 'red';
    } else {
        text = 'how to name you in our website?';
        color = 'black';
    }
    var $name = $('uname');
    $name.data('tooltip').getTip().text(text);
    $name.css('color', color);
    return signState.isSucc();
}