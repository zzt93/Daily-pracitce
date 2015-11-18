/**
 * Created by zzt on 11/15/15.
 */

function useToolTip(formId) {
    // select all desired input fields and attach tooltips to every input of them
    $(formId).find("input").tooltip({
        // place tooltip on the right edge
        position: "center right",
        // a little tweaking of the position
        offset: [-2, 10],
        // use the built-in fadeIn/fadeOut effect
        effect: "fade",
        // custom opacity setting
        opacity: 0.7
    });
}
