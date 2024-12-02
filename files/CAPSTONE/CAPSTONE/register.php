<!DOCTYPE html>

<?php include("login-header.php")
    ?>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

    <!--  TITLE -->
    <title>LLS | Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="res/css/style.css">
    <link rel="stylesheet" href="res/css/bootstrap.min.css">

    <script src="res/js/jquery-3.6.0.min.js"></script>
    <script src="res/js/feather.min.js"></script>
    <script src="res/js/jquery.slimscroll.min.js"></script>
    <script src="res/js/sweetalert2.all.min.js"></script>
    <script src="res/js/script.js"></script>
</head>

<style>

</style>

<body>
    <?php
    if (isset($_GET['error']) && $_GET['error'] == 1) {
        echo '<script>
        const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 4000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener("mouseenter", Swal.stopTimer);
                toast.addEventListener("mouseleave", Swal.resumeTimer);
            }
        });

        Toast.fire({
            title: "Oh no! that username has been used! ❌"
        });
    </script>';
    } elseif (isset($_GET['error']) && $_GET['error'] == 2) {
        echo '<script>
        const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 4000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener("mouseenter", Swal.stopTimer);
                toast.addEventListener("mouseleave", Swal.resumeTimer);
            }
        });

        Toast.fire({
            title: "Oh no! It seems your name has been taken! 🙁"
        });
    </script>';
    } elseif (isset($_GET['success']) && $_GET['success'] == 1) {
        echo '<script>
        const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 4000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener("mouseenter", Swal.stopTimer);
                toast.addEventListener("mouseleave", Swal.resumeTimer);
            }
        });

        Toast.fire({
            title: "The doors to the mystical realm have opened. Great adventures await you! 🚪✨"
        });
    </script>';
    }
    ?>


    <body class="account-page">

        <?php
        $queryString = $_SERVER['QUERY_STRING'];

        if (strpos($queryString, 'error=1') === false && strpos($queryString, 'error=2') === false && strpos($queryString, 'success=1') === false) {
            echo '<div id="global-loader">';
            echo '<img src="res/assets/img/logo.png">';
            echo '</div>';
        }


        ?>

        <!-- MAIN  -->
        <div class="main-wrapper">



            <!-- FORM START -->
            <form class="account-content" method="POST" action="system/register.php">

                <div class="login-wrapper">

                    <!-- LOGIN FORM -->
                    <div class="login-content">
                        <div id="dark-mode-toggle">
                            <span class="fa fa-sun"></span>
                        </div>

                        <div class="login-userset">

                            <div class="d-flex justify-content-center">
                                <div class="login-logo logo-normal">
                                    <img src="res/assets/img/logo.png" alt="img">
                                </div>
                            </div>

                            <div class="login-userheading">
                                <h1 class="d-flex justify-content-center">Register</h1>
                                <h4 class="d-flex justify-content-center">
                                    🌟✨ "Unleash Your Imagination!" ✨🌟</h4>
                            </div>

                            <div class="form-login">
                                <label>Nickname</label>
                                <div class="user-group">
                                    <input type="text" name="nickname" required>
                                    <span class="user-icon fa fa-user-circle"></span>
                                </div>
                                <div class="text-danger pt-2">
                                </div>
                            </div>

                            <div class="form-login">
                                <label>Username</label>
                                <div class="user-group">
                                    <input type="text" name="username" required>
                                    <span class="user-icon fa fa-user"></span>
                                </div>
                                <div class="text-danger pt-2">
                                </div>
                            </div>

                            <div class="form-login">
                                <label>Password</label>
                                <div class="pass-group">
                                    <input type="password" class="pass-input" name="password" id="password" required>
                                    <span class="fas toggle-password fa-eye-slash"></span>
                                </div>
                                <div class="text-danger pt-2">
                                </div>
                            </div>

                            <div class="form-login">
                                <div class="forgot">
                                    <h4><a href="" class="hover-a">Forgot Password?</a></h4>
                                </div>
                            </div>

                            <div class="form-login">
                                <button name="login" class="btn btn-primary col-md-12">Register</button>
                            </div>

                            <div class="signinform text-center">
                                <h4>Already have an account?
                                    <a href="index.php" class="hover-a">Log in</a>
                                </h4>
                            </div>

                            <div class="form-setlogin">
                                <h4>Or register with</h4>
                            </div>

                            <div class="form-sociallink">
                                <ul>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <img src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/icons/google.png"
                                                class="me-2" alt="google">
                                            Register using Google
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <img src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/icons/facebook.png"
                                                class="me-2" alt="google">
                                            Register using Facebook
                                        </a>
                                    </li>
                                </ul>
                            </div>

                        </div>
                    </div>

                    <!-- RIGHT PANEL -->
                    <div class="login-img">
                        <img src="res/assets/img/loginbg.jpg" alt="img">
                    </div>

                </div>
            </form>

            <!-- FORM END -->

        </div>

        <script>
            function updateErrorMessage() {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "login-reload.php", true);

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // Extract the response from login-reload.php
                        var response = xhr.responseText;

                        // Check if the response contains the error message
                        if (response.includes("SweetAlertMessage")) {
                            // Assuming you have a container element with id "error-container" to update
                            document.getElementById("error-container").innerHTML = response;
                        }
                    }
                };

                xhr.send();
            }


            setTimeout(function () {
                var lpmessage = document.getElementById('alert');
                if (lpmessage) {
                    lpmessage.style.opacity = '0';
                    lpmessage.style.transition = 'opacity 0.5s ease';
                    setTimeout(function () {
                        lpmessage.style.display = 'none';
                    }, 500);
                }
            }, 3500);
        </script>

    </body>

    </html>