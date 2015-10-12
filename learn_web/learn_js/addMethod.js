/**
 * Created by zzt on 10/9/15.
 */

Function.prototype.method = function (name, func) {
    this.prototype[name] = func;
    return this;
};

Number.method('integer', function () {
    return Math[this < 0 ? 'ceiling' :'floor'](this);
});

document.writeln((-10 / 3).integer());

String.method('trim', function () {
    return this.replace(/^\s+|\s+$/g, '');
});

document.writeln('"' + "    neat    ".trim() + '"');