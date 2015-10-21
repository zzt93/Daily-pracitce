/**
 * Created by zzt on 10/21/15.
 */

function validate(obj, lowval, hival) {
    if ((obj.value < lowval) || (obj.value > hival))
        console.log("Invalid Value!");
}

//<p>Enter a number between 18 and 99:</p>
//<input type="text" name="age" size=3 onChange="validate(this, 18, 99);">