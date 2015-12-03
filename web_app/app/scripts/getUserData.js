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
                var statistic = $.parseJSON(infos[1]);

                $('#sta_weight').html(statistic['weight']);
                $('#sta_heart-rate').html(statistic['heart_rate']);
                $('#sta_slumber').html(statistic['slumber']);
                $('#sta_walk').html(statistic['walk']);
                $('#sta_upper').html(statistic['upper_limb']);
                $('#sta_lower').html(statistic['lower_limb']);

            } catch (e) {
                console.error(e);
                window.location.replace(data);
                return;
            }
            try {
                var goal = $.parseJSON(infos[0]);
                var plan = $.parseJSON(infos[2]);

                $('#goal_walk').html(goal['walk']);
                $('#goal_upper').html(goal['upper_limb']);
                $('#goal_lower').html(goal['lower_limb']);

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
            funcName: "getAdviceData"
        },
        function (data) {
            //console.log("status is: " + textStatus + " Response from server: " + eval(data));
            var infos = data.split(SEPARATOR);
            console.log(infos.length);
            try {
                var question = $.parseJSON(infos[0]);
            } catch (e) {
                console.error(e);
            }
            try {
                var advice = $.parseJSON(infos[1]);
            } catch (e) {
                console.error(e);
            }


            $('#DisplayName').val(data['DomainObjectkey']);

        }
    );
}