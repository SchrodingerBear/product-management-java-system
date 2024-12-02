<?php
if ($_SESSION['active'] !== "active") {
    header("Location: ../login.php");
    exit();
}
