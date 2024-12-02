<!DOCTYPE html>

<?php include("login-header.php")
    ?>

<body>
    <?php
    if (isset($_GET['error']) && $_GET['error'] == 2) {
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
            title: "Wrong Incantation! 💫🗺️"
        });
    </script>';
    } elseif (isset($_GET['error']) && $_GET['error'] == 1) {
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
           title: "Your name can\'t be found in the book of life!  💫🗺️"
        });
    </script>';
    }
    ?>

    <body class="account-page">

        <?php
        $queryString = $_SERVER['QUERY_STRING'];

        if (strpos($queryString, 'error=1') === false && strpos($queryString, 'error=2') === false) {
            echo '<div id="global-loader">';
            echo '<img src="res/assets/img/logo.png">';
            echo '</div>';
        }
        ?>


        <!-- MAIN  -->
        <div class="main-wrapper">



            <!-- FORM START -->
            <form class="account-content" method="POST" action="system/login.php">

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
                                <h1 class="d-flex justify-content-center">Welcome Back!</h1>
                                <h4 class="d-flex justify-content-center">🚪🌟 Cast your magic! 🚪🌟</h4>
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
                                <button name="login" class="btn btn-primary col-md-12">Login</button>
                            </div>

                            <div class="signinform text-center">
                                <h4>Don’t have an account? <a href="register.php" class="hover-a">Sign Up</a></h4>
                            </div>

                            <div class="form-setlogin">
                                <h4>Or login with</h4>
                            </div>

                            <div class="form-sociallink">
                                <ul>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <img src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/icons/google.png"
                                                class="me-2" alt="google">
                                            Login using Google
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <img src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/icons/facebook.png"
                                                class="me-2" alt="google">
                                            Login using Facebook
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