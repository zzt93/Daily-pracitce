/**
 *
 * Created by zzt on 2/18/16.
 */

function handleDragOver(e) {
    if (e.preventDefault) {
        e.preventDefault(); // Necessary. Or drop action have no effect
    }

    e.dataTransfer.dropEffect = 'move';  // See the section on the DataTransfer object.

    return false;
}

var imgNum = 0;
var date;
var sameDate;

function handleDragStart(ev) {
    ev.dataTransfer.effectAllowed = 'move';
    ev.dataTransfer.setData('text/html', 'blah'); // needed for FF.
    ev.dropEffect = "move";

    // prepare dessert id
    var fields = ev.target.src.split('/');
    var imgName = fields[fields.length - 1];
    imgNum = +imgName.split('\.')[0];
    // prepare buy date
    var date2 = $(ev.target.parentElement).prevAll('h4:first').text();
    if (typeof date === 'undefined') {
        date = date2;
        sameDate = true;
    } else {
        sameDate = date === date2;
    }
}

function initDragDrop() {
    //$('img.dessert').bind('dragstart', handleDragStart);
    var cols = document.querySelectorAll('img.dessert');
    [].forEach.call(cols, function (col) {
        col.addEventListener('dragstart', handleDragStart, false);
    });


    var target = document.querySelector('#drop');
    target.addEventListener('dragover', handleDragOver, false);
    target.addEventListener('drop', function (e) {
        if (e.stopPropagation) {
            e.stopPropagation();
        }

        if (!sameDate) {
            $('#date-box').dialog();
            return false;
        }
        console.info(imgNum);
        console.info(date);

        $.post('OrderAdd',
            {
                did: imgNum,
                num: 1,
                bdate: date
            }, function (response) {
                console.log("Response: " + response);
            });

        var currentOrder = $('#current-order');
        currentOrder.jtable('load');
        //you'll need to prevent the browser's default behavior for drops,
        // which is typically some sort of annoying redirect
        return false;

    }, false);
}