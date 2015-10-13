/**
 * Created by zzt on 10/12/15.
 */

var hanoi = function (disk, from, to, aux) {
    hanoi(disk - 1, from, aux, to);
    document.writeln('move ' + disk + 'from #' + from
        + 'to #' + to);
    hanoi(disk - 1, aux, to, from);
};

hanoi(5, 0, 2, 1);

var walk_DOM = function walk(node, func) {
    func(node);
    node = node.firstChild;
    while (node) {
        walk(node, func);
        node = node.nextSibling;
    }
};

var getElementByAttribute = function (name, value) {
    var res = [];
    walk_DOM(this, function (node) {
        var actual = node.nodeType === 1 && node.getAttribute(name);
        if (typeof actual === 'string' &&
            (actual === value || typeof value !== 'string')) {
            res.push(node);
        }
    });

    return res;
};
