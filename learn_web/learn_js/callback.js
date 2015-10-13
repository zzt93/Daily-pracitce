/**
 * Created by zzt on 10/13/15.
 */

function prepare_the_request() {

}
var synchronize = function () {
    var request = prepare_the_request();

    function send_request_synchronously(request) {

    }

    var response = send_request_synchronously(request);
    display(response);
};

var asynchronize = function () {

    request = prepare_the_request();
    function send_request_asynchronously(request, f) {

    }

    send_request_asynchronously(request, function (response) {
        display(response);
    });
};