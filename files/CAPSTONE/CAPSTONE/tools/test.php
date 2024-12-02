<?php
require_once '../system/connect.php';
$username = "Administrator"; // The administrator who received the message
$selectedUser = "User"; // The sender (user)

$query = $conn->query("SELECT * FROM `message` WHERE `receiver` = '$username' AND `sender` = '$selectedUser'") or die(mysqli_error($conn));

// Loop through the messages and display them
while ($message = $query->fetch_array()) {
    echo 'Sender: ' . $message['sender'] . '<br>';
    echo 'Message: ' . $message['message'] . '<br>';
    echo 'MessageID: ' . $message['messageid'] . '<br>';
    // Parse and format the timestamp
    $timestamp = strtotime($message['timestamp']);
    echo 'Time: ' . date('F j, Y g:iA', $timestamp) . '<br><br>';
}

?>