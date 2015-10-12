/**
 * Created by zzt on 9/25/15.
 */

var add = function (a, b) {
    if (typeof a !== 'number' || typeof b !== 'number') {
        throw {
            name: 'TypeError',
            message: 'add needs numbers'
        };
    }
    return a + b;
};

var try_it = function () {
    try {
        add("seven")
    } catch (e) {
        document.write(e.name + ': ' + e.message);
    }
};

try_it();