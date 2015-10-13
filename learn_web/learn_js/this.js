/**
 * Created by zzt on 9/25/15.
 * example and explanation is from (here)
 * [https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/this]
 */

"use strict";


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


/**
 The same notion holds true for methods defined
 somewhere on the object's prototype chain.
 If the method is on an object's prototype chain,
 this refers to the object the method was called on,
 as if the method was on the object.
 */
var o2 = {
    f: function () {
        return this.a + this.b;
    }
};
var p = Object.create(o2);
p.a = 1;
p.b = 4;

console.log(p.f()); // 5


/*
 * Constructors work like this:
 *
 * function MyConstructor(){
 *   // Actual function body code goes here.
 *   // Create properties on |this| as
 *   // desired by assigning to them.  E.g.,
 *   this.fum = "nom";
 *   // et cetera...
 *
 *   // If the function has a return statement that
 *   // returns an object, that object will be the
 *   // result of the |new| expression.  Otherwise,
 *   // the result of the expression is the object
 *   // currently bound to |this|
 *   // (i.e., the common case most usually seen).
 * }
 */

function C() {
    this.a = 37;
}

var o3 = new C();
console.log(o3.a); // logs 37


function C2() {
    this.a = 37;
    return {a: 38};
}

o = new C2();
console.log(o.a); // logs 38


/**
 * As a dom listener
 */
// When called as a listener, turns the related element blue
function bluify(e) {
    // Always true
    console.log(this === e.currentTarget);
    // true when currentTarget and target are the same object
    console.log(this === e.target);
    this.style.backgroundColor = '#A5D9F3';
}

// Get a list of every element in the document
var elements = document.getElementsByTagName('*');

// Add bluify as a click listener so when the
// element is clicked on, it turns blue
for (var i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', bluify, false);
}