<?php
session_start();
require 'connect.php';

if (isset($_POST['username']) && isset($_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Check if the username exists
    $stmt = $conn->prepare("SELECT * FROM `account` WHERE `username` = ?");
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        // Username exists, now check the password
        $userRow = $result->fetch_assoc();
        $storedPassword = $userRow['password'];

        if (password_verify($password, $storedPassword)) {
            // Password is correct
            $accountType = ucfirst($userRow['account_type']);
            $name = ucfirst($userRow['name']);
            $img = '../res/assets/profile/' . $userRow['image'];
            $accountType = $userRow['account_type'];

            $_SESSION['account_type'] = $accountType;
            $_SESSION['username'] = $_POST['username'];
            $_SESSION['account_type'] = $accountType;
            $_SESSION['name'] = $name;
            $_SESSION['img'] = $img;
            $_SESSION['active'] = "active";

            // SESSIONS
            // foreach ($_SESSION as $key => $value) {
            //     echo $key . ' = ' . $value . '<br>';
            // }

            header('location: ../pages/dashboard.php');
            exit();
        } else {
            header('location: ../login.php?error=2');
            exit();
        }
    } else {
        // Username doesn't exist
        header('location: ../login.php?error=1');
        exit();
    }
}
?>