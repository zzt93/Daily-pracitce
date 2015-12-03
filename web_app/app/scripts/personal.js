/**
 * Created by zzt on 11/18/15.
 */

var idCount = function () {
    var count = 1;
    return {
        getIdCount: function () {
            return count;
        },

        incrementAndGet: function () {
            count++;
            return count;
        }
    }
}();

function addPlan(link) {
    'use strict';
    function setCaption(table, i) {
        var caption = table.find('caption');
        var text = caption.text();
        var indexOf = text.indexOf(i - 1 + '');
        caption.text(text.substr(0, indexOf) + i + text.substr(indexOf + 1));
    }

    // append a new table
    var table = $(link).parents('table');
    var id = table.attr('id');
    var nTable = table.clone()
        .attr('id', id.substr(0, id.length - 1) + idCount.incrementAndGet());
    table.after(nTable[0].outerHTML);
    // update caption of following table
    var captionNum = parseInt(table.find('caption').text().replace(/\D/g, ''));
    var i = captionNum + 1;
    var next = table;
    while ((next = next.next('table[id^="plan"]')).is('table')) {
        setCaption(next, i);
        i++;
    }
}

function enableTDEdit(link) {
    'use strict';
    var table = $(link).parents('table');
    var tds = table.find('td');
    tds.attr('contenteditable', 'true').toggleClass('editable');
    tds[0].focus();
    // hide edit link
    $(link).hide();
    // show submit link
    $(link).next().show();
}

function submitUpdate(link, funcName) {
    'use strict';
    var table = $(link).parents('table');
    // make it can't edit
    var tds = table.find('td');
    tds.attr('contenteditable', 'false').removeClass('editable');
    var tdsval = tds.val();
    $.post(
        '../php/Controller/AnalysisController.class.php',
        {
            funcName: funcName,
            data: tdsval
        },
        function (data) {
            console.log(data);
            if (data === 'true') {
                $(link).hide();
                $(link).prev().show();
            }
        }
    );

}

function readTodayData(link) {
    'use strict';
    $.get(
        '../php/Controller/AnalysisController.class.php',
        {
            funcName: 'readTodayData'
        },
        function (data) {
            console.log(data);
            try {
                var statistic = $.parseJSON(data);
            } catch (e) {
                console.error(e);
                //window.location.replace(data);
                return;
            }
            $('caption a').removeClass('fa-spin');

            $('#sta_weight').html(statistic['weight']);
            $('#sta_heart-rate').html(statistic['heart-rate']);
            $('#sta_slumber').html(statistic['slumber']);
            $('#sta_walk').html(statistic['walk']);
            $('#sta_upper').html(statistic['upper_limb']);
            $('#sta_lower').html(statistic['lower_limb']);
        }
    );
    $('caption a').addClass('fa-spin');
}
