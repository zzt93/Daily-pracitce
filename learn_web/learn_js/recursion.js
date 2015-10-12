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
