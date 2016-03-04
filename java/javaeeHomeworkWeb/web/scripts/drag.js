/**
 *
 * Created by zzt on 2/18/16.
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

function handleDragOver(e) {
    if (e.preventDefault) {
        e.preventDefault(); // Necessary. Or drop action have no effect
    }

    e.dataTransfer.dropEffect = 'move';  // See the section on the DataTransfer object.

    return false;
}

var imgNum = 0;
var price = 0;
var name;
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
    // prepare price
    var title = ev.target.title.split(':');
    price = title[1];
    name = title[0];

    var rows = $('#current-order').find('tr').length - 1;
    if (rows == 0) {
        date = 'undefined';
    }
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

        var tmpId;
        $.post('ReserveDetailAdd',
            {
                did: imgNum,
                num: 1,
                price: price,
                bdate: date,
                bid: readGet()['branchNum'],
                dessertName: name
            }, function (response) {
                tmpId = response['tmpId'];
                console.log("Response: " + response);
            });

        var currentOrder = $('#current-order');
        currentOrder.jtable('addRecord', {
            record: {
                tmpId: tmpId,
                dessertName: name,
                price: price,
                num: 1
            },
            clientOnly: true
        });
        //you'll need to prevent the browser's default behavior for drops,
        // which is typically some sort of annoying redirect
        return false;

    }, false);
}


function payOrder() {
    var dialogSetting = {
        buttons: {
            "Get it" : function () {
                $(this).dialog("close");
            }
        }};
    $.ajax({
        type: "POST",
        url: "BranchUserReservePay",
        success: function(response){
            $('#donePaying').dialog(dialogSetting);
            console.log(response);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $('#errorPaying').dialog(dialogSetting);
        }
    });
}