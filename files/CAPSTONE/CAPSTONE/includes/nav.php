<div class="header">

    <div class="header-left">
        <span class="title">Lapu-Lapu Sizzle &
            Sweets</span>
    </div>

    <a id="mobile_btn" class="mobile_btn" href="#sidebar">
        <span class="bar-icon">
            <span></span>
            <span></span>
            <span></span>
        </span>
    </a>

    <ul class="nav user-menu">

        <li class="nav-item nav-searchinputs">
            <div id="dateTime"></div>
        </li>

        <?php
        $currentPageURL = basename($_SERVER['PHP_SELF']);
        if ($currentPageURL === 'pos.php') {
            ?>
            <li class="nav-item nav-item-box">
                <a href="dashboard.php">
                    <i data-feather="corner-down-left"></i>
                </a>
            </li>
            <?php
        }
        ?>

        <li class="nav-item nav-item-box">
            <a id="dark-mode-toggle">
                <i data-feather="moon"></i>
            </a>
        </li>

        <li class="nav-item nav-item-box">
            <a href="javascript:void(0);" id="btnFullscreen">
                <i id="full-screen-icon" data-feather="maximize"></i>
            </a>
        </li>

        <li class="nav-item dropdown nav-item-box">
            <a href="javascript:void(0);" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
                <i data-feather="bell"></i><span class="badge rounded-pill">2</span>
            </a>
            <div class="dropdown-menu notifications">
                <div class="topnav-dropdown-header">
                    <span class="notification-title">Notifications</span>
                    <a href="javascript:void(0)" class="clear-noti"> Clear All </a>
                </div>

                <div class="noti-content">
                    <ul class="notification-list">
                        <li class="notification-message">
                            <a href="https://dreamspos.dreamguystech.com/laravel/template/public/activities">
                                <div class="media d-flex">
                                    <span class="avatar flex-shrink-0">
                                        <img alt
                                            src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/profiles/avatar-02.jpg">
                                    </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">John Doe</span> added
                                            new task <span class="noti-title">Patient appointment booking</span>
                                        </p>
                                        <p class="noti-time"><span class="notification-time">4 mins ago</span>
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </li>

                        <li class="notification-message">
                            <a href="https://dreamspos.dreamguystech.com/laravel/template/public/activities">
                                <div class="media d-flex">
                                    <span class="avatar flex-shrink-0">
                                        <img alt
                                            src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/profiles/avatar-03.jpg">
                                    </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Tarah Shropshire</span>
                                            changed the task name <span class="noti-title">Appointment booking
                                                with payment gateway</span></p>
                                        <p class="noti-time"><span class="notification-time">6 mins ago</span>
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </li>

                        <li class="notification-message">
                            <a href="https://dreamspos.dreamguystech.com/laravel/template/public/activities">
                                <div class="media d-flex">
                                    <span class="avatar flex-shrink-0">
                                        <img alt
                                            src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/profiles/avatar-06.jpg">
                                    </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Misty Tison</span>
                                            added <span class="noti-title">Domenic Houston</span> and <span
                                                class="noti-title">Claire Mapes</span> to project <span
                                                class="noti-title">Doctor available module</span></p>
                                        <p class="noti-time"><span class="notification-time">8 mins ago</span>
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </li>

                        <li class="notification-message">
                            <a href="https://dreamspos.dreamguystech.com/laravel/template/public/activities">
                                <div class="media d-flex">
                                    <span class="avatar flex-shrink-0">
                                        <img alt
                                            src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/profiles/avatar-17.jpg">
                                    </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Rolland Webber</span>
                                            completed task <span class="noti-title">Patient and Doctor video
                                                conferencing</span></p>
                                        <p class="noti-time"><span class="notification-time">12 mins ago</span>
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </li>

                        <li class="notification-message">
                            <a href="https://dreamspos.dreamguystech.com/laravel/template/public/activities">
                                <div class="media d-flex">
                                    <span class="avatar flex-shrink-0">
                                        <img alt
                                            src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/profiles/avatar-13.jpg">
                                    </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Bernardo Galaviz</span>
                                            added new task <span class="noti-title">Private chat module</span>
                                        </p>
                                        <p class="noti-time"><span class="notification-time">2 days ago</span>
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="topnav-dropdown-footer">
                    <a href="https://dreamspos.dreamguystech.com/laravel/template/public/activities">View all
                        Notifications</a>
                </div>
            </div>
        </li>

        <li class="nav-item dropdown has-arrow main-drop">
            <a href="javascript:void(0);" class="dropdown-toggle nav-link userset" data-bs-toggle="dropdown">
                <span class="user-info">
                    <span class="user-letter">
                        <img src="<?php echo $_SESSION['img']; ?>" alt>
                    </span>
                    <span class="user-detail">
                        <span class="user-name">
                            <?php echo $_SESSION['name']; ?>
                        </span>
                        <span class="user-role">
                            <?php echo $_SESSION['account_type']; ?>
                        </span>
                    </span>
                </span>
            </a>

            <div class="dropdown-menu menu-drop-user">

                <div class="profilename">
                    <div class="profileset">
                        <span class="user-img"><img src="<?php echo $_SESSION['img']; ?>" alt>
                            <span class="status online"></span></span>
                        <div class="profilesets">

                            <h6>
                                <?php echo $_SESSION['name']; ?>
                            </h6>
                            <h5>
                                <?php echo $_SESSION['account_type']; ?>
                            </h5>
                        </div>
                    </div>
                    <hr class="m-0">

                    <a class="dropdown-item" href="https://dreamspos.dreamguystech.com/laravel/template/public/profile">
                        <i data-feather="user"></i> My Profile</a>
                    <a class="dropdown-item"
                        href="https://dreamspos.dreamguystech.com/laravel/template/public/generalsettings"><i
                            data-feather="settings"></i>Settings</a>
                    <hr class="m-0">
                    <a class="dropdown-item logout pb-0" href="../system/logout.php"><img
                            src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/icons/log-out.svg"
                            class="me-2" alt="img">Logout</a>
                </div>
            </div>
        </li>
    </ul>

</div>


<script>
    // FULL SCREEN 
    function toggleFullscreen(elem) {
        elem = elem || document.documentElement;
        if (!document.fullscreenElement && !document.mozFullScreenElement &&
            !document.webkitFullscreenElement && !document.msFullscreenElement) {
            if (elem.requestFullscreen) {
                elem.requestFullscreen();
            } else if (elem.msRequestFullscreen) {
                elem.msRequestFullscreen();
            } else if (elem.mozRequestFullScreen) {
                elem.mozRequestFullScreen();
            } else if (elem.webkitRequestFullscreen) {
                elem.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
            }
            // Change the icon to "minimize" when entering full screen
            document.getElementById('full-screen-icon').setAttribute('data-feather', 'minimize');
        } else {
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitExitFullscreen) {
                document.webkitExitFullscreen();
            }
            // Change the icon back to "maximize" when exiting full screen
            document.getElementById('full-screen-icon').setAttribute('data-feather', 'maximize');
        }
        // Refresh the Feather icons to apply the changes
        feather.replace();
    }

    document.getElementById('btnFullscreen').addEventListener('click', function () {
        toggleFullscreen();
    });

    function updateDateTime() {
        const currentDate = new Date();
        const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: true };
        const formattedDate = currentDate.toLocaleDateString('en-US', options);

        const dateTimeDisplay = document.getElementById('dateTime');
        dateTimeDisplay.textContent = formattedDate;
    }

    setInterval(updateDateTime, 1000);
    updateDateTime(); 
</script>