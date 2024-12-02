<?php
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}
?>

<?php
require_once '../system/connect.php';

$selectedUser = (isset($_GET['user'])) ? mysqli_real_escape_string($conn, $_GET['user']) : '';
$stmt = $conn->prepare("SELECT name, image FROM account WHERE name = ?");
$stmt->bind_param("s", $selectedUser);
$stmt->execute();
$result = $stmt->get_result();

if ($row = $result->fetch_assoc()) {
    $userName = $row['name'];
    $userImage = $row['image'];
}
?>

<div class="card mb-0" style="height: 600px;">
    <div class="card-header msg_head">
        <div class="d-flex bd-highlight">
            <div class="img_cont">
                <img class="rounded-circle user_img" src="../res/assets/profile/<?php echo $userImage; ?>"
                    alt="<?php echo $userName; ?>">
            </div>
            <div class="user_info">
                <span><strong>
                        <?php echo $userName; ?>
                    </strong></span>
                <p class="mb-0">Messages</p>
            </div>
        </div>
    </div>


    <div class="card-body msg_card_body chat-scroll">

        <ul class="list-unstyled">
            <!-- THIS IS FROM SENDER TO USER -->
            <?php
            require_once '../system/connect.php';

            $username = $_SESSION['name'];
            $selectedUser = (isset($_GET['user'])) ? mysqli_real_escape_string($conn, $_GET['user']) : '';

            $today = date('Y-m-d');
            $lastDate = '';

            $query = $conn->query("SELECT * FROM `message` 
                      WHERE (`receiver` = '$username' AND `sender` = '$selectedUser') 
                         OR (`sender` = '$username' AND `receiver` = '$selectedUser') 
                      ORDER BY timestamp ASC") or die(mysqli_error($conn));

            $lastDate = '';

            while ($message = $query->fetch_array()) {
                $messageTimestamp = strtotime($message['timestamp']);
                $messageDate = date('Y-m-d', $messageTimestamp);
                $formattedDate = date('l h:i A', $messageTimestamp);

                if ($messageDate != $lastDate) {
                    echo '<li class="chat-date">' . ($messageDate == $today ? 'Today' : ($messageDate == date('Y-m-d', strtotime('yesterday')) ? 'Yesterday' : $formattedDate)) . '</li>';
                    $lastDate = $messageDate;
                }

                $messageClass = ($message['sender'] == $username) ? 'sent' : 'received';

                $senderImage = basename($_SESSION['img']);

                echo '<li class="media ' . $messageClass . ' d-flex">';
                echo '    <div class="avatar flex-shrink-0">';

                if ($message['receiver'] == $username) {
                    echo '           <img class="rounded-circle user_img" src="../res/assets/profile/' . $senderImage . '" alt="' . $userName . '">';
                } else if ($message['sender'] == $username) {
                    echo '           <img class="rounded-circle user_img" src="../res/assets/profile/' . $userImage . '" alt="' . $selectedUser . '">';
                }
                echo '    </div>';
                echo '    <div class="media-body flex-grow-1">';
                echo '        <div class="msg-box">';
                echo '            <div>';
                echo '                <p>' . $message['message'] . '</p>';
                echo '                <ul class="chat-msg-info">';
                echo '                    <li>';
                echo '                        <div class="chat-time">';
                echo '                            <span>' . date('g:iA', $messageTimestamp) . '</span>';
                echo '                        </div>';
                echo '                    </li>';
                echo '                </ul>';
                echo '            </div>';
                echo '        </div>';
                echo '    </div>';
                echo '</li>';
            }

            ?>

        </ul>

    </div>

    <div class="card-footer">
        <div class="input-group">
            <textarea class="form-control type_msg mh-auto empty_check" placeholder="Aa" <?php if (empty($selectedUser) || $userName === "N/A")
                echo 'disabled'; ?>></textarea>

            <button class="btn btn-primary btn_send"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
        </div>

    </div>
</div>