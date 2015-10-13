/**
 * Created by zzt on 10/13/15.
 *
 * The general pattern of a module is a function
 * that defines private variables and functions;
 * creates privileged functions which, through closure,
 * will have access to the private variables and functions;
 * and that returns the privileged functions or stores them
 * in an accessible place.
 */

var random_str_maker = function (len) {
    function random_char() {

    }

    var str;
    var i;
    for (i = 0; i < len; i++) {
        str += random_char();
    }
    return {
        name: str,
        id: i
    };
};

var serial_maker = function () {

    /*
     Produce an object that produces unique strings. A
     unique string is made up of two parts: a prefix
     and a sequence number. The object comes with
     methods for setting the prefix and sequence
     number, and a gensym method that produces unique strings.
     */
    var prefix = '';
    var seq = 0;
    return {
        set_prefix: function (p) {
            prefix = String(p);
        },
        set_seq: function (s) {
            seq = s;
        },
        gensym: function () {
            var result = prefix + seq;
            seq += 1;
            return result;
        }
    };
};

var seqer = serial_maker();
seqer.set_prefix = ('Q');
seqer.set_seq = (1000);
var unique = seqer.gensym();
// unique is "Q1000"