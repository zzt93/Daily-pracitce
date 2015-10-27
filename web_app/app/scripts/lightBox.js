/**
 * Created by zzt on 10/26/15.
 */

var addLightBox = function (linkId) {
    var logIn = document.getElementById(linkId);

    logIn.onclick = function () {

        var light_box = document.getElementById("light-box"),
            dimmer = document.createElement("div");

        dimmer.style.width = document.body.scrollWidth + 'px';
        dimmer.style.height = Math.max(document.body.scrollHeight, window.innerHeight) + 'px';
        dimmer.className = 'dimmer';

        dimmer.onclick = function () {
            light_box.style.display = 'none';
            document.body.removeChild(dimmer);
        };

        document.body.appendChild(dimmer);

        light_box.style.display = 'block';
        light_box.style.top = window.innerHeight / 2 - 140 + 'px';
        light_box.style.left = window.innerWidth / 2 - 140 + 'px';
    };
};

