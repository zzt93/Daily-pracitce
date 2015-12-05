/**
 * Created by zzt on 12/4/15.
 */

const SEPARATOR = '\n';

function readGet() {
    var parts = window.location.search.substr(1).split("&");
    var $_GET = {};
    for (var i = 0; i < parts.length; i++) {
        var temp = parts[i].split("=");
        $_GET[decodeURIComponent(temp[0])] = decodeURIComponent(temp[1]);
    }
    return $_GET;
}

var qid;

function getAdviceInfo() {
    qid = readGet()['qid'];
    $.get(
        '../php/Controller/QuestionController.class.php',
        {
            funcName: "getQuestionAndAdvice",
            qid: qid
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            console.log(data);
            var infos = data.split(SEPARATOR);
            console.log(infos.length);

            function makeAQblock(id, data) {
                var $id = $(id).find('div.asked-question');
                data.forEach(function (a) {
                    var tmp = $id.clone().css('display', 'flex');
                    tmp.find('span.vote-count').text(a['vote']);
                    tmp.find('article').text(a['content']);
                    var find = tmp.find('span.question-info');
                    find[0].textContent = (a['uname']);
                    find[1].textContent = (a['time']);

                    $id.after(tmp[0].outerHTML);
                });
                return $id;
            }

            try {
                var question = $.parseJSON(infos[0]);
                var qBlock = makeAQblock('#question', [question]);
                qBlock.prev('h3').html(question['title']);
                // add tags
                //var tags = qBlock.find('span.tag');
                //var texts = question[0]['tags'];
                //var i;
                //for (i = 0; i <tags.length; i++) {
                //    tags[i].textContent = (texts[i]);
                //}
            } catch (e) {
                console.error(e);
            }


            try {
                var advice = $.parseJSON(infos[1]);
                if (!Array.isArray(advice)) {
                    makeAQblock('#advice', [advice]);
                } else {
                    makeAQblock('#advice', advice);
                }
            } catch (e) {
                console.error(e);
            }

        }
    );
}

function setQid() {
    $('#sub_answer').find('input[name="qid"]').val(qid);
}