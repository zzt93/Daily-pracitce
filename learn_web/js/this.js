/**
 * Created by zzt on 9/25/15.
 */
var myObject = {
    value: 3
};

myObject.double = function () {
    var that = this;

    var helper = function () {
        that.value = that.value * 2;
    };
    helper();
};