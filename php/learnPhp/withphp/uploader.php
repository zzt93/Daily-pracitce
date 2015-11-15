<?php
/*

uploadedfile - uploadedfile is the reference we assigned in our HTML form.
We will need this to tell the
$_FILES array which file we want to play around with.

$_FILES['uploadedfile']['name'] - name contains the original path of the user uploaded file.
$_FILES['uploadedfile']['tmp_name'] - tmp_name contains the path to the temporary file that resides on the server. The file should exist on the server in a temporary directory with a temporary name.

*/

$uploaded_file = "uploaded/";
$uploaded_file = $uploaded_file . basename($_FILES['uploadedfile']['name']);

if (move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $uploaded_file)) {
    echo "The file " . basename($_FILES['uploadedfile']['name']) .
        " has been uploaded";
} else {
    echo "There was an error uploading the file, please try again!";
}