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
                console.log(e);
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

function setUserAccountInfo(submit) {
    var $gender = $('#gender').find(':checked');
    var account = {
        funcName: "setUserData",
        uname: $('#DisplayName').val(),
        location: $('#Location').val(),
        age: $('#age').val(),
        email: $('#email').val(),
        gender: $('input[name="gender"]').index($gender)
    };
    updateAccount(account, submit);
}

function setUserSetting(submit) {
    var data = {};
    var $settings = $('#settings');
    var input = $settings.find('input[type="checkbox"]');
    var i;
    for (i = 0; i < input.length; i++) {
        data[input[i].name] = input[i].checked;
    }
    var num = $settings.find('input[type="number"]');
    data[num.attr('name')] = num.val();
    var hid = $settings.find('input[type="hidden"]');
    data[hid.attr('name')] = hid.val();
    updateAccount(data, submit);
}

function updateAccount(data, submit) {
    var original = $(submit).css('background-color');
    $(submit).css('background-color', 'blue');

    $.post(
        '../php/Controller/AccountController.class.php',
        data,
        function (res, textStatus) {
            //console.log("status is: " + textStatus + " Response from server: " + res);
            if (res === 'true') {
                $(submit).css('background-color', original);
            }
        }
    );
}

/**
 * related to HSMapper, HGMapper, PlanMapper
 */
//$.getScript("../scripts/healthData.js")
//    .done(function (script, textStatus) {
//        console.log(textStatus);
//    })
//    .fail(function (jqxhr, settings, exception) {
//        console.log(textStatus);
//    });

function getHealthInfo() {
    if (!count.healthFirst()) {
        return;
    }
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
                if (!Array.isArray(data)) {
                    data = [data];
                }
                data.forEach(function (a) {
                    var tmp = block.clone().show();
                    tmp.find('h3').text(a['vote']);
                    tmp.find('a').text(a['title']).attr('href', 'question.php?qid=' + a['qid']);
                    tmp.find('p.q-content').text(a['content']);
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
                // plus one for skip uid
                $settings.find('input[type="number"]').val(post[i + 1]);
            } catch (e) {
                console.error(e);
            }
        }
    )
}


var fileState = function () {
    var state = false;
    return {
        fileFine: function() {
            return state;
        },
        setState: function(s) {
            state = s;
        }
    }
}();

function checkFile(change) {
    var file = change.files[0];
    var size = file.size;
    var type = file.type;
    if (size > 100 * 1024) {
        alert('file too large');
        fileState.setState(false);
        return;
    }
    if (!type.contains('image')) {
        alert('file type not permitted');
        fileState.setState(false);
    }
    fileState.setState(true);
}

function uploadFile(file) {
    $.post(
        '../php/Controller/AccountController.class.php',
        {
            funcName: 'updateAvatar'
        },
        function (res, textStatus) {
            //console.log("status is: " + textStatus + " Response from server: " + res);
            if (res === 'true') {
                $(submit).css('background-color', res);
            }
        }
    );
}