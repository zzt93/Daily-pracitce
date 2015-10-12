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

/*
 In the global execution context (outside of any function),
 this refers to the global object, whether in strict mode or not.
 */
console.log(this.document === document); // true

// In web browsers, the window object is also the global object:
console.log(this === window); // true

this.a = 37;
console.log(window.a); // 37

/*
 Inside a function, the value of this depends on how the function is called.
 */

function f1() {
    return this;
}

var res = f1() === window;

/*
 In the second example, this should be undefined,
 because f2 was called without providing any base (e.g. window.f2()).
 This feature wasn't implemented in some browsers
 when they first started to support strict mode.
 As a result, they incorrectly returned the window object.
 */
function f2() {
    "use strict";
    return this;
}


/*
 Note that this behavior is not at all affected by
 how or where the function was defined.
 */
var o = {
    prop: 37,
    f: function () {
        return this.prop;
    }
};

console.log(o.f()); // logs 37

var o1 = {prop: 37};

function independent() {
    return this.prop;
}

o1.f = independent;

console.log(o1.f()); // logs 37


o.b = {
    g: independent,
    prop: 42
};
console.log(o.b.g()); // logs 42