/**
 * Created by zzt on 10/13/15.
 */

var quo = function f(val) {
    return {
        get_val: function () {
            return val;
        }
    }
};

var t = quo('closure');
document.write(t.get_val());

var fade = function (node) {
    var level = 1;
    var step = function () {
        var hex = level.toString(16);
        node.style.backgroundColor = '#FFFF' + hex + hex;
        if (level < 15) {
            level += 1;
            setTimeout(step, 100);
        }
    };
    setTimeout(step, 100);
};
fade(document.body);

/**
 * Bad example
 *
 * The time when add_handler(node) is invoked(on click() is invoked),
 * i is already
 * become nodes.length and that is when closure
 */
var add_handler = function (nodes) {
    var i;
    for (i = 0; i < nodes.length; i++) {
        nodes[i].onclick = function (e) {
            alert(i);
        }
    }
};
/**
 * Right one
 *
 * Now, instead of assigning a function to onclick ,
 * we define a function and immediately invoke it, passing in i
 */
var n_add_handler = function (nodes) {
    var i;
    for (i = 0; i < nodes.length; i++) {
        nodes[i].onclick = function (i) {
            return function (e) {
                return alert(i);
            }
        }(i);
    }
};

// not the same closure
var addChosenLisener = function () {
    'use strict';
    var n1 = nowSelected();
    n1.changeTo(1);
    console.log(n1.getNow());

    var n2 = nowSelected();
    console.log(n2.getNow());
};
function nowSelected() {
    var now = 0;
    return {
        getNow: function () {
            return now;
        },
        changeTo: function (i) {
            now = i;
        }
    };
}
addChosenLisener();