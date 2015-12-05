/**
 * Created by zzt on 12/4/15.
 */
const SEPARATOR = '\n';



function getHealthInfo() {
    $.get(
        '../php/Controller/AnalysisController.class.php',
        {
            funcName: "getAnalysisData"
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            if (data.replace(/\s/g, '').length === 0) {
                // mean data is empty
                return;
            }
            var infos = data.split(SEPARATOR);
            //console.log(infos.length);

            try {
                var goal = $.parseJSON(infos[0]);

                $('#goal_walk').html(goal['walk']);
                $('#goal_upper').html(goal['upper_limb']);
                $('#goal_lower').html(goal['lower_limb']);

            } catch (e) {
                console.log(e);
            }

            try {
                var statistic = $.parseJSON(infos[1]);

                $('#sta_weight').html(statistic['weight']);
                $('#sta_heart-rate').html(statistic['heart_rate']);
                $('#sta_slumber').html(statistic['slumber']);
                $('#sta_walk').html(statistic['walk']);
                $('#sta_upper').html(statistic['upper_limb']);
                $('#sta_lower').html(statistic['lower_limb']);

            } catch (e) {
                console.error(e);
                return;
            }

            try {
                var plan = $.parseJSON(infos[2]);

                $('#breakfast').html(plan['breakfast']);
                $('#lunch').html(plan['lunch']);
                $('#dinner').html(plan['dinner']);
                $('#exercise').html(plan['exercise']);
            } catch (e) {
                console.log(e);
            }

        }
    );
}


function getQuestion() {
    $.get(
        '../php/Controller/QuestionController.class.php',
        {
            funcName: "getAll"
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            //console.log(data);

            function makeAQblock(id, data) {
                var block = $(id).find('.question-block');
                if (!Array.isArray(data)) {
                    data = [data];
                }
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


function getActivity() {
    $.get(
        '../php/Controller/ActivityController.class.php',
        {
            funcName: "getAllAP"
        },
        function (data) {
            console.log(data);
            var infos = data.split(SEPARATOR);
            console.log(infos.length);

            try {
                var post = $.parseJSON(infos[0]);
                if (!Array.isArray(post)) {
                    post = [post];
                }
                var notice = $('#post').find('div.notice');
                post.forEach(
                    function (p) {
                        var n = notice.clone().show();
                        var spans = n.find('span');
                        spans[0].innerHTML = p['post_time'];
                        spans[1].innerHTML = p['content'];
                        notice.after(n[0].outerHTML);
                    }
                );
            } catch (e) {
                console.error(e);
            }

            try {
                var act = $.parseJSON(infos[1]);
                if (!Array.isArray(act)) {
                    act = [act];
                }
                var activity = $('#campaign').find('section.activity');
                act.forEach(
                    function (a) {
                        var nAct = activity.clone().show();
                        nAct.find('p').text(a['title']);
                        nAct.find('article').text(a['content']);
                        activity.after(nAct[0].outerHTML);
                    }
                );
            } catch (e) {
                console.error(e);
            }
        }
    )
}
