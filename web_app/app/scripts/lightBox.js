/**
 * Created by zzt on 10/26/15.
 */

/*
 Call addLightBox() when body onload():

 The load event fires at the end of the document loading process.
 At this point, all of the objects in the document are in the DOM,
 and all the images and sub-frames have finished loading.
 */
var addLightBox = function (linkId) {
    var anchor = document.getElementById(linkId);

    anchor.onclick = function () {

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
        // make this link not jump or scroll
        return false;
    };
};

