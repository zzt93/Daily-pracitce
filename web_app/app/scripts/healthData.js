/**
 * Created by zzt on 12/4/15.
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
                window.location.replace(data);
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

