<!DOCTYPE html>
<!--
-------------CAPSTONE PROJECT-------------
"Full Stack PHP Business Management System"
Developer: Sean Charles Pugosa 
Business Reference: Lapu-lapu Sizzle & Sweets
Date Started: September 5, 2023

Please be respectful. My website is not a template. 
I'd like to encourage you to make something that is truly your own. 

Copyright ©2023 Sean Charles Pugosa. All Rights Reserved.

                  .----.
      .---------. | == |
      |.-"""""-.| |----|
      ||       || | == |
      ||       || |----|
      |'-.....-'| |::::|
      `"")---(""` |___.|
     /:::::::::::\" _  "
    /:::=======:::\`\`\
jgs `"""""""""""""`  '-'

-->

<?php
if (!isset($_SESSION)) {
    session_start();
}
?>

<?php include '../system/validate.php'; ?>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

    <!--  TITLE -->
    <title>LLS | Dashboard</title>
    <link rel="stylesheet" href="../res/css/owl.carousel.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="../res/css/style.css">
    <link rel="stylesheet" href="../res/css/fontawesome.min.css">
    <link rel="stylesheet" href="../res/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="../res/css/all.min.css"> -->
    <link rel="stylesheet" href="../res/css/animate.css">
    <link rel="stylesheet" href="../res/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../res/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="../res/css/select2.min.css">
    <link rel="stylesheet" href="../res/css/dragula.min.css">
    <link rel="stylesheet" href="../res/css/dataTables.bootstrap4.min.css">

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="../res/js/jquery-3.6.0.min.js"></script>
    <script src="../res/js/main.js"></script>
    <script src="../res/js/bootstrap.bundle.min.js"></script>
    <script src="../res/js/feather.min.js"></script>
    <script src="../res/js/jquery.slimscroll.min.js"></script>
    <script src="../res/js/select2.min.js"></script>
    <script src="../res/js/moment.min.js"></script>
    <script src="../res/js/bootstrap-datetimepicker.min.js"></script>
    <script src="../res/js/apexcharts.min.js"></script>
    <script src="../res/js/chart-data.js"></script>
    <script src="../res/js/owl.carousel.min.js"></script>
    <script src="../res/js/fileupload.min.js"></script>
    <script src="../res/js/sweetalert2.all.min.js"></script>
    <script src="../res/js/sweetalerts.min.js"></script>
    <script src="../res/js/script.js"></script>

</head>