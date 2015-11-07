/**
 * Created by zzt on 11/6/15.
 */
function getActivity() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {

    };

    xhr.open('GET', 'getSearch.php', true);

    xhr.send();
}
