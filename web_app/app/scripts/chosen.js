/**
 * Created by zzt on 10/21/15.
 */


var addliChosenLisener = function () {
    'use strict';

    var side = document.getElementById('side_nav_list');
    var i = 0;

    function setChosen(event) {
        var target = this;

        function setTab() {
            target.classList.add('tab-chosen');
            var now = nowSelected;
            side.children[now.getNow()].classList.remove('tab-chosen');
            //side.children[now.changeTo()];
        }
        setTab();

        function setContent() {
            document.getElementById('personal').style.display = 'none';
        }
        setContent();
    }

    for (; i < side.childElementCount; i++) {
        side.children[i].addEventListener('click', setChosen);
    }
};

var nowSelected = function () {
    var now = 0;

    return {
        getNow: function () {
            return now;
        },

        changeTo: function (i) {
            now = i;
        }
    };
}();

addliChosenLisener();