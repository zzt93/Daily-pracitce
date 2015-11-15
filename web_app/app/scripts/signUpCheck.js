/**
 * Created by zzt on 11/15/15.
 */

var password;

function validatePassword(input) {
    var value = input.value;
    if(value.length < 6) {
        input.title = 'too short password';
        input.tooltip({
            // place tooltip on the right edge
            position: "center right",
            // a little tweaking of the position
            offset: [-2, 10],
            // use the built-in fadeIn/fadeOut effect
            effect: "fade",
            // custom opacity setting
            opacity: 0.7
        });
    }

    password = input.value;
}

function samePassword(input) {
    if (password !== input.value) {
        prompt('not same with upper password');
    }
}