<?php include '../includes/head.php'; ?>

<body>
	<div class="main-wrapper">
		<?php include '../includes/nav.php'; ?>
		<?php include '../includes/sidebar.php'; ?>

		<div class="page-wrapper">
			<div class="content">
				<div class="col-lg-12">
					<div class="row chat-window">

						<div class="col-lg-5 col-xl-4 chat-cont-left">
							<div class="card mb-sm-3 mb-md-0 contacts_card flex-fill">
								<div class="card-header chat-search">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="search_btn"><i class="fas fa-search"></i></span>
										</div>
										<input type="text" placeholder="Search"
											class="form-control search-chat rounded-pill">
									</div>
								</div>
								<div class="card-body contacts_body chat-users-list chat-scroll">

									<?php
									require_once '../system/connect.php';

									if (isset($_GET['user'])) {
										$activeUser = $_GET['user'];
									} else {
										$activeUser = ''; // Default value if 'user' parameter is not set
									}

									$username = $_SESSION['username'];

									$query = $conn->query("SELECT contact FROM `account` WHERE `username` = '$username'") or die(mysqli_error($conn));
									$fetch = $query->fetch_array();
									$contactIds = explode(', ', $fetch['contact']);

									$contacts = array();

									foreach ($contactIds as $contactId) {
										$contactId = intval($contactId);

										$query = $conn->query("SELECT name, image FROM `account` WHERE `id` = $contactId") or die(mysqli_error($conn));
										$fetch = $query->fetch_array();

										if ($fetch) {
											$contacts[] = $fetch;
										}
									}

									foreach ($contacts as $contact) {
										$isActive = ($contact['name'] === $activeUser) ? 'active' : '';

										echo '<a class="media d-flex ' . $isActive . '">
										<div class="media-img-wrap flex-shrink-0">
											<div class="avatar avatar-lg">
												<img src="../res/assets/profile/' . $contact['image'] . '"
													alt="User Image" class="avatar-img rounded-circle">
											</div>
										</div>
										<div class="media-body flex-grow-1">
											<div>
												<div class="user-name">' . $contact['name'] . '</div>
												<div class="user-last-chat">' . $contact['name'] . '\'s last message</div>
											</div>
											<div>
												<div class="last-chat-time">Last message time</div>
												<div class="badge badge-success badge-pill">15</div>
											</div>
										</div>
									</a>';
									}
									?>


								</div>

							</div>
						</div>


						<div class="col-lg-7 col-xl-8 chat-cont-right">

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
							} else {
								$userName = $_SESSION['name'];
								$userImage = basename($_SESSION['img']);
							}

							?>

							<div class="card mb-0" style="height: 600px;">
								<div class="card-header msg_head">
									<div class="d-flex bd-highlight">
										<div class="img_cont">
											<img class="rounded-circle user_img"
												src="../res/assets/profile/<?php echo $userImage; ?>"
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
										<?php
										require_once '../system/connect.php';
										$username = $_SESSION['name'];
										$selectedUser = (isset($_GET['user'])) ? mysqli_real_escape_string($conn, $_GET['user']) : '';
										$query = $conn->query("SELECT * FROM `message` WHERE `receiver` = '$username' AND `sender` = '$selectedUser' ORDER BY timestamp ASC") or die(mysqli_error($conn));

										while ($message = $query->fetch_array()) {
											echo '<li class="media sent d-flex">';
											echo '<div class="avatar flex-shrink-0">';
											echo '<img src="../res/assets/profile/' . $userImage . '" class="avatar-img rounded-circle">';
											echo '</div>';
											echo '<div class="media-body flex-grow-1">';
											echo '<div class="msg-box">';
											echo '<div>';
											echo '<p>' . $message['message'] . '</p>';
											echo '<ul class="chat-msg-info">';
											echo '<li>';
											echo '<div class="chat-time">';
											$timestamp = strtotime($message['timestamp']);
											echo '<span>' . date('g:iA', $timestamp) . '</span>'; // Display time only
											echo '</div>';
											echo '</li>';
											echo '</ul>';
											echo '</div>';
											echo '</div>';
											echo '</div>';
											echo '</li>';
										}

										?>

									</ul>

								</div>

								<div class="card-footer">
									<div class="input-group">
										<textarea class="form-control type_msg mh-auto empty_check" placeholder="Aa"
											<?php if (empty($selectedUser) || $userName === "N/A")
												echo 'disabled'; ?>></textarea>

										<button class="btn btn-primary btn_send"><i class="fa fa-paper-plane"
												aria-hidden="true"></i></button>
									</div>

								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="searchpart">
			<div class="searchcontent">
				<div class="searchhead">
					<h3>Search </h3>
					<a id="closesearch"><i class="fa fa-times-circle" aria-hidden="true"></i></a>
				</div>
				<div class="searchcontents">
					<div class="searchparts">
						<input type="text" placeholder="search here">
						<a class="btn btn-searchs">Search</a>
					</div>
					<div class="recentsearch">
						<h2>Recent Search</h2>
						<ul>
							<li>
								<h6><i class="fa fa-search me-2"></i> Settings</h6>
							</li>
							<li>
								<h6><i class="fa fa-search me-2"></i> Report</h6>
							</li>
							<li>
								<h6><i class="fa fa-search me-2"></i> Invoice</h6>
							</li>
							<li>
								<h6><i class="fa fa-search me-2"></i> Sales</h6>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<script>
			const contacts = document.querySelectorAll('.media');
			const chatContainer = document.querySelector('.chat-cont-right');

			contacts.forEach(contact => {
				contact.addEventListener('click', function () {
					const contactName = this.querySelector('.user-name').textContent;

					contacts.forEach(contact => {
						contact.classList.remove('active');
					});

					this.classList.add('active');

					const xhr = new XMLHttpRequest();
					xhr.open('GET', '../system/chat-reload.php?user=' + contactName, true);
					xhr.onreadystatechange = function () {
						if (xhr.readyState === 4 && xhr.status === 200) {
							chatContainer.innerHTML = xhr.responseText;
						}
					};
					xhr.send();
				});
			});
		</script>

	</div>

</body>