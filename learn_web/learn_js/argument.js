/**
 * Created by zzt on 9/25/15.
 */

var sum = function () {
    var i, sum = 0;
    for (i = 0; i < arguments.length; i ++) {
        sum += arguments[i];
    }
    return sum;
};

document.writeln(sum(1, 2, 3, 4, 5));