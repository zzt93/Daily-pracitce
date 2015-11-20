/**
 * Created by zzt on 11/15/15.
 */

var password;

var passState = function () {
    var same = false;
    var len = false;
    return {
        isSucc: function () {
            return same && len;
        },
        setState: function (s) {
            same = s;
        },
        setLen: function (l) {
            len= l;
        }
    }
}();

function validatePassword(input) {
    var value = input.value;
    if (value.length < 6) {
        $(input).data('tooltip').getTip().text("too short password");
        $(input).css('color', 'red');
        passState.setLen(false);
    } else {
        $(input).data('tooltip').getTip().text("Must be at least 6 characters");
        $(input).css('color', 'black');
        passState.setLen(true);
    }
    password = input.value;
}

function samePassword(input) {
    if (password !== input.value) {
        $(input).data('tooltip').getTip().text("Passwords doesn't match");
        $(input).css('color', 'red');
        passState.setState(false);
    } else {
        $(input).data('tooltip').getTip().text("Please confirm your password");
        $(input).css('color', 'black');
        passState.setState(true);
    }
}

function checkInput() {
    return passState.isSucc();
}