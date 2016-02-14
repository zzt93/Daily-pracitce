/**
 * Created by zzt on 10/21/15.
 *
 * Use to emulate the behaviour of tabs
 */
function readGet() {
    var parts = window.location.search.substr(1).split("&");
    var $_GET = {};
    for (var i = 0; i < parts.length; i++) {
        var temp = parts[i].split("=");
        $_GET[decodeURIComponent(temp[0])] = decodeURIComponent(temp[1]);
    }
    return $_GET;
}


function indexOfChildren(childrens, target) {
    'use strict';

    var i = 0;
    for (; i < childrens.length; i++) {
        if (childrens[i] === target) {
            return i;
        }
    }
}

const NOW = 'now';

var addListChosenListener = function (listId, contentsId, now) {
    var $_GET = readGet();
    if (typeof now === 'undefined') {
        if (NOW in $_GET) {
            now = $_GET[NOW];
        } else {
            now = 0;
        }
    }
    'use strict';

    var side = document.getElementById(listId);
    var i = 0;
    const chosen = 'tab-chosen';
    var block = document.getElementById(contentsId);


    function setChosen(event) {
        var target = this;


        var index = indexOfChildren(side.children, target);
        if (index === nowSelected.getNow()) {
            return;
        }
        setTab();

        setContent(index);

        nowSelected.changeTo(index);

        function setTab() {
            target.classList.add(chosen);
            side.children[nowSelected.getNow()].classList.remove(chosen);
        }

        function setContent(index) {
            block.children[index].style.display = 'block';
            block.children[nowSelected.getNow()].style.display = 'none';
        }

    }

    for (; i < side.childElementCount; i++) {
        side.children[i].addEventListener('click', setChosen);
    }

    nowSelected.changeTo(now);
    side.children[now].classList.add(chosen);
    // set specific block content visible
    block.children[now].style.display = 'block';
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

