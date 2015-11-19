/**
 * Created by zzt on 11/15/15.
 */

var password;

function validatePassword(input) {
    var value = input.value;
    if (value.length < 6) {
        $(input).data('tooltip').getTip().text("too short password");
        $(input).css('color', 'red');
        //$(input).tooltip({
        //    events: {
        //        tooltip: "mouseenter"
        //    }
        //});
    } else {
        $(input).data('tooltip').getTip().text("Must be at least 6 characters");
        $(input).css('color', 'black');
    }

    password = input.value;
}

function samePassword(input) {
    if (password !== input.value) {
        prompt('not same with upper password');
    }
}