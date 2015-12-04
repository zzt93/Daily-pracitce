/**
 * Created by zzt on 12/4/15.
 */
$.getScript("healthData.js");

function getQuestion() {
    $.get(
        '../php/Controller/QuestionController.class.php',
        {
            funcName: "getAll"
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            console.log(data);

            function makeAQblock(id, data) {
                var block = $(id).find('.question-block');
                data.forEach(function (a) {
                    var tmp = block.clone().show();
                    tmp.find('h3.vote').text(a['vote']);
                    tmp.find('a').text(a['title']).attr('href', 'question.php?qid=' + a['qid']);
                    tmp.find('p.q-content').text(a['content']);
                    block.after(tmp[0].outerHTML);
                });
            }

            try {
                var question = $.parseJSON(data);
                makeAQblock('#advice', question);
            } catch (e) {
                console.error(e);
            }

        }
    );
}

function getHealth() {

}

function getActivity() {

}