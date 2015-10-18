// JavaScript demonstration
var changeBg = function (event) {
    console.log("method called");
    var me = event.target
        , square = document.getElementById("square");
    square.style.backgroundColor = "#ffee33";
    square.style.marginLeft = "20em";
    me.setAttribute("disabled", "disabled");
    setTimeout(function () {
        clearDemo(me)
    }, 2000);
};

function clearDemo(button) {
    var square = document.getElementById("square");
    square.style.backgroundColor = "transparent";
    square.style.marginLeft = "0";
    button.removeAttribute("disabled");
}

var button = document.querySelector("button");
button.addEventListener("click", changeBg);
console.log(button);