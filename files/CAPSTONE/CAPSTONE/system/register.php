<?php
require 'connect.php';

if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['nickname'])) {
    $nickname = $_POST['nickname'];
    $username = $_POST['username'];
    $password = password_hash($_POST['password'], PASSWORD_BCRYPT); // Hash the password
    $account_type = "user";

    $checkUsernameQuery = $conn->prepare("SELECT * FROM `account` WHERE `username` = ?");
    $checkUsernameQuery->bind_param("s", $username);
    $checkUsernameQuery->execute();
    $checkUsernameResult = $checkUsernameQuery->get_result();

    $checkNicknameQuery = $conn->prepare("SELECT * FROM `account` WHERE `name` = ?");
    $checkNicknameQuery->bind_param("s", $nickname);
    $checkNicknameQuery->execute();
    $checkNicknameResult = $checkNicknameQuery->get_result();

    if ($checkUsernameResult->num_rows > 0) {
        header('location: ../register.php?error=1');
        exit();
    } elseif ($checkNicknameResult->num_rows > 0) {
        header('location: ../register.php?error=2');
        exit();
    } else {
        $insertQuery = $conn->prepare("INSERT INTO `account` (`username`, `password`, `name`, `account_type`, `image`) VALUES (?, ?, ?, ?, ?)");
        $insertQuery->bind_param("sssss", $username, $password, $nickname, $account_type, $defaultImage);
        $defaultImage = 'default.png';

        if ($insertQuery->execute()) {
            header('location: ../register.php?success=1');
            exit();
        } else {
            echo "Error: " . $insertQuery->error;
        }
    }
}
?>