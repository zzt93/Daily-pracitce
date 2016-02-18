/**
 * Created by zzt on 2/18/16.
 */

function handleDragOver(e) {
    if (e.preventDefault) {
        e.preventDefault(); // Necessary. Or drop action have no effect
    }

    e.dataTransfer.dropEffect = 'move';  // See the section on the DataTransfer object.

    return false;
}

var imgNum = 0;

function dragstart_handler(ev) {
    ev.preventDefault();
    // Add the target element's id to the data transfer object
    var fields = ev.target.src.split('/');
    var imgName = fields[fields.length - 1];
    imgNum = +imgName.split('\.')[0];
    ev.dropEffect = "move";
}

function initDragDrop() {
    $('img.dessert').bind('dragstart', dragstart_handler);

    var target = document.querySelector('#drop');
    target.addEventListener('dragover', handleDragOver, false);
    target.addEventListener('drop', function (e) {
        if (e.stopPropagation) {
            e.stopPropagation();
        }
        //e.target.appendChild(document.getElementById(imgNum));
        console.info(imgNum);

        return false;
    }, false);
}