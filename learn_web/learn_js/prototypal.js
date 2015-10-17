/**
 * Created by zzt on 10/14/15.
 */

var myMammal = {
    name: 'herb the Mammal',
    saying: '',
    get_name: function () {
        return this.name;
    },
    say: function () {
        return this.saying || '';
    }
};

var myCat = Object.create(myMammal);
myCat.name = 'Henrietta';
myCat.saying = 'meow';
myCat.get_name = function () {
    return this.name + '' + this.saying + '' + this.name;
};


