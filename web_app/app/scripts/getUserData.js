/**
 * Created by zzt on 12/1/15.
 */

var count = function () {
    var health = 0;
    var advice = 0;
    var activity = 0;
    var setting = 0;

    function increaseAndTest(t) {
        t++;
        return t == 1;
    }

    return {
        healthFirst: function () {
            return increaseAndTest(health);
        },
        adviceFirst: function () {
            return increaseAndTest(advice);
        },
        activityFirst: function () {
            return increaseAndTest(activity);
        },
        settingFirst: function () {
            return increaseAndTest(setting);
        }
    }
}();

const SEPARATOR = "\n";

function getUserAccountInfo() {
    $.get(
        '../php/Controller/AccountController.class.php',
        {
            funcName: "getUserData"
        },
        function (data, textStatus) {
            //console.log("status is: " + textStatus + " Response from server: " + data);
            //console.log(data);
            try {
                data = $.parseJSON(data);
            } catch (e) {
                window.location.replace(data);
                return;
            }
            //console.log(data);
            $('#DisplayName').val(data['uname']);
            $('#Location').val(data['location']);
            var roles = data['role'].split(";");
            roles.forEach(
                function (role) {
                    $('#roles').append("<li>" + role + "</li>");
                }
            );
            $('#age').val(data['age']);
            $('#email').val(data['email']);
            var radios = $('#gender').find('input[type="radio"]');
            radios[data['gender']].checked = true;
            $("#avatar").attr("src", data['icon_url']);

        }
    );
}

/**
 * related to HSMapper, HGMapper, PlanMapper
 */
$.getScript("healthData.js");

function getAdviceInfo() {
    if (!count.adviceFirst()) {
        return;
    }
    $.get(
        '../php/Controller/QuestionController.class.php',
        {
            funcName: "getUserAQ"
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            console.log(data);
            var infos = data.split(SEPARATOR);
            console.log(infos.length);

            function makeAQblock(id, data) {
                var block = $(id).find('.question-block');
                data.forEach(function (a) {
                    var tmp = block.clone().show();
                    tmp.find('h3').text(a['vote']);
                    tmp.find('a').text(a['title']).attr('href', 'question.php?qid=' + a['qid']);
                    tmp.find('p').text(a['content']);
                    block.after(tmp[0].outerHTML);
                });
            }

            try {
                var question = $.parseJSON(infos[0]);
                makeAQblock('#user-question-list', question);
            } catch (e) {
                console.error(e);
            }

            try {
                var advice = $.parseJSON(infos[1]);
                makeAQblock('#user-answer-list', advice);
            } catch (e) {
                console.error(e);
            }

        }
    );
}


function getActivity() {
    if (!count.activityFirst()) {
        return;
    }
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
                post.forEach(
                    function (p) {
                        var notice = $('#post').find('div.notice').clone().show();
                        notice.find('span');
                    }
                );
            } catch (e) {
                console.error(e);
            }

            try {
                var act = $.parseJSON(infos[1]);
                post.forEach(
                    function (p) {
                    }
                );
            } catch (e) {
                console.error(e);
            }
        }
    )
}

function getSetting() {
    if (!count.settingFirst()) {
        return;
    }
    $.get(
        '../php/Controller/AccountController.class.php',
        {
            funcName: "getSetting"
        },
        function (data) {
            console.log(data);

            try {
                var post = $.parseJSON(data);
                var $settings = $('#settings');
                var input = $settings.find('input[type="checkbox"]');
                var i;
                for (i = 0; i < input.length; i++) {
                    $(input[i]).attr('checked', (post[i + 1] === 1));
                }
                $settings.find('input[type="number"]').val(post[i]);
            } catch (e) {
                console.error(e);
            }
        }
    )
}