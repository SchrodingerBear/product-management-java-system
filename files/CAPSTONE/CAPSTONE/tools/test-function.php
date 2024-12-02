<?php
include '../system/connect.php'; // Include the database connection script

if (isset($_POST['insert'])) {
    $currentDateTime = date("F j, Y g:iA"); // Get the current date and time in the desired format

    // SQL query to insert the formatted current date and time into the 'message' table
    $sql = "INSERT INTO message (timestamp) VALUES ('$currentDateTime')";

    if (mysqli_query($conn, $sql)) {
        echo "Date and time inserted successfully.";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
}

mysqli_close($conn); // Close the database connection
?>