<?php
session_start();
?>
<title>
	LLS | POS
</title>

<?php include '../includes/head.php'; ?>

<script src="../res//js/system-js/pos.js"></script>
<link rel="stylesheet" href="../res/css/pos.css">

<body>
	<div class="main-wrapper">


		<div id="global-loader">
			<img src="../res/assets/img/logo.png">
		</div>

		<div class="header header-one">

			<?php include '../includes/nav.php'; ?>

			<a id="mobile_btn" class="mobile_btn" href="#sidebar">
				<span class="bar-icon">
					<span></span>
					<span></span>
					<span></span>
				</span>
			</a>
			<div class="dropdown mobile-user-menu">
				<a href="javascript:void(0);" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
				<div class="dropdown-menu dropdown-menu-right">
					<a class="dropdown-item" href="">My Profile</a>
					<a class="dropdown-item" href="">Settings</a>
					<a class="dropdown-item" href="">Logout</a>
				</div>
			</div>

		</div>

		<div class="page-wrapper ms-0">
			<div class="content">
				<div class="row">

					<div class="col-lg-5 col-sm-12 tabs_wrapper">
						<div class="card">
							<div class="title-container">
								<div class="card-body text-align-center">
									<h5><strong>Items</strong></h5>
								</div>
							</div>

							<?php
							require_once '../system/connect.php';
							$categoryQuery = $conn->query("SELECT * FROM category") or die(mysqli_error($conn));
							$productQuery = $conn->query("SELECT * FROM product") or die(mysqli_error($conn));
							?>



							<div class="card-body pt-0">

								<ul class="tabs owl-carousel owl-theme owl-product border-0 owl-loaded">
									<div class="owl-stage-outer">
										<div class="owl-stage">
											<div class="owl-item active">
												<li class="" id="all">
													<div class="product-details">
														<img src="../res/assets/pos/category/default.png" alt="img">
														<h6>All</h6>
													</div>
												</li>
											</div>
											<?php
											while ($category = mysqli_fetch_assoc($categoryQuery)) {
												$categoryId = $category['id'];
												$categoryName = $category['name'];
												$categoryImage = $category['image'];
												?>
												<div class="owl-item active">
													<li id="<?php echo $categoryName; ?>">
														<div class="product-details">
															<img src="../res/assets/pos/category/<?php echo $categoryImage; ?>"
																alt="img">
															<h6>
																<?php echo $categoryName; ?>
															</h6>
														</div>
													</li>
												</div>
												<?php
											}
											?>
										</div>
									</div>
								</ul>




								<div class="tabs_container">


									<?php
									echo '<div class="tab_content active" data-tab="all">
									<div class="row">';

									$productQuery = $conn->query("SELECT * FROM product") or die(mysqli_error($conn));

									while ($row = $productQuery->fetch_assoc()) {
										$itemname = $row['name'];
										$price = $row['price'];
										$quantityperitem = $row['quantity'];
										$imageSrc = $row['image'];
										$barcode = $row['barcode'];

										echo '<div class="col-lg-3 col-sm-6 d-flex">
											<div class="productset flex-fill active">
												<div class="productsetimg">
													<img src="../res/assets/pos/item/' . $imageSrc . '" alt="img">
													<h6>' . $quantityperitem . ' PC</h6>
												</div>
												<div class="productsetcontent">
													<h4>' . $itemname . '</h4>
													<h5>' . $barcode . '</h5>
													<h6>₱' . $price . '</h6>
												</div>
											</div>
										</div>';
									}
									echo '</div></div>';
									?>

									<?php
									$categoryQuery = $conn->query("SELECT DISTINCT category FROM product") or die(mysqli_error($conn));

									while ($categoryRow = $categoryQuery->fetch_assoc()) {
										$category = $categoryRow['category'];

										echo '<div class="tab_content" data-tab="' . $category . '">
									<div class="row">';

										$productQuery = $conn->query("SELECT * FROM product WHERE category = '$category'") or die(mysqli_error($conn));

										while ($row = $productQuery->fetch_assoc()) {
											$itemname = $row['name'];
											$price = $row['price'];
											$quantityperitem = $row['quantity'];
											$imageSrc = $row['image'];
											$barcode = $row['barcode'];

											echo '<div class="col-lg-3 col-sm-6 d-flex">
											<div class="productset flex-fill active">
												<div class="productsetimg">
													<img src="../res/assets/pos/item/' . $imageSrc . '" alt="img">
													<h6>' . $quantityperitem . ' PC</h6>
												</div>
												<div class="productsetcontent">
													<h4>' . $itemname . '</h4>
													<h5>' . $barcode . '</h5>
													<h6>' . $price . '</h6>
												</div>
											</div>
										</div>';
										}

										echo '</div></div>';
									}

									?>

								</div>
							</div>
						</div>

					</div>

					<div class="col-lg-4 col-sm-12 ">

						<div class="card">
							<div class="title-container">
								<div class="card-body text-align-center">
									<h5><strong>Cart</strong></h5>
								</div>

								<div class="order-list">
									<div class="actionproducts">
										<ul>

											<li>
												<?php
												if ($_SESSION['account_type'] === 'Admin') {
													echo '
												<a href="javascript:void(0);" data-bs-toggle="dropdown"
													aria-expanded="false" class="dropset">
													<i class="pos-icon" data-feather="more-horizontal"></i>

												</a>												';
												}
												?>

												<a href="#" id="store-history">
													<i class="pos-icon" data-feather="cloud"></i>
												</a>

												<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton"
													data-popper-placement="bottom-end">
													<li>
														<a href="#" class="dropdown-item">Manage Transactions</a>
													</li>
													<li>
														<a href="#" class="dropdown-item">Manage Customers</a>
													</li>
													<li>
														<a href="#" class="dropdown-item">Manage Items</a>
													</li>
													<li>
														<a href="#" class="dropdown-item">Price Check</a>
													</li>
												</ul>
												<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton"
													data-popper-placement="bottom-end">
													<li>

													</li>
												</ul>
											</li>
										</ul>
									</div>
								</div>
							</div>


							<div class="card-body pt-0">
								<div class="totalitem">
									<h4>Total items : <span id="total-items">0</span></h4>
									<a href="#" id="clear-cart-link">Clear All</a>
								</div>

								<div class="product-table" id="cart">


								</div>
							</div>
						</div>

					</div>

					<div class="col-lg-3 col-sm-12 ">
						<div class="card">
							<div class="title-container">
								<div class="card-body text-align-center">
									<h5><strong>Payment</strong></h5>
								</div>
							</div>

							<div class="card-body pt-0">
								<div class="setvalue">
									<ul>
										<li>
											<h5>Subtotal</h5>
											<h6 id="subtotal-value">₱0.00</h6>
										</li>
										<li>
											<h5>Tax</h5>
											<h6 id="tax-value">₱0.00</h6>
										</li>
										<hr>
										<li class="total-value">
											<h5>Total</h5>
											<h6 id="total-value">₱0.00</h6>
										</li>
										<li class="total-value">
											<h5>Payment</h5>
											<h6 id="payment-value">₱0.00</h6>
										</li>
										<li class="total-value">
											<h5>Change</h5>
											<h6 id="change-value">₱0.00</h6>
										</li>
									</ul>
								</div>
								<div class="setvaluecash">
									<ul>

										<li>
											<a href="#" id="cash-payment">
												<img src="../res/assets/img/icons/cash.png" alt="img" class="pos-icon">
												Cash
											</a>
										</li>
										<li>
											<a href="javascript:void(0);">
												<img src="../res/assets/img/icons/credit.png" alt="img"
													class="pos-icon">
												Credit
											</a>
										</li>

									</ul>
								</div>


								<div class="text-end">
									<button type="button" class="btn btn-success btn-lg"
										id="proceed-transaction-button">Proceed Transaction</button>
								</div>


							</div>
						</div>
					</div>

				</div>
			</div>

		</div>

	</div>

</body>