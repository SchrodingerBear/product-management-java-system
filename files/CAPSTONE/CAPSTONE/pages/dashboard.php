<?php
session_start();
?>

<?php include '../includes/head.php'; ?>

<body>
	<div class="main-wrapper">
		<?php include '../includes/nav.php'; ?>
		<?php include '../includes/sidebar.php'; ?>
		<div class="page-wrapper">

			<div class="content">
				<div class="row">
					<div class="col-lg-3 col-sm-6 col-12 d-flex">
						<div class="dash-count">
							<div class="dash-counts">
								<h4></h4>
								<h5>Total Revenues</h5>
							</div>
							<div class="dash-imgs">
								<i data-feather="dollar-sign"></i>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6 col-12 d-flex">
						<div class="dash-count das1">
							<div class="dash-counts">
								<h4></h4>
								<h5>Registered Items</h5>
							</div>
							<div class="dash-imgs">
								<i data-feather="package"></i>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6 col-12 d-flex">
						<div class="dash-count das2">
							<div class="dash-counts">
								<h4></h4>
								<h5>Registered User</h5>
							</div>
							<div class="dash-imgs">
								<i data-feather="users"></i>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6 col-12 d-flex">
						<div class="dash-count das3">
							<div class="dash-counts">
								<h4></h4>
								<h5>Transactions</h5>
							</div>
							<div class="dash-imgs">
								<i data-feather="check-square"></i>
							</div>
						</div>
					</div>
				</div>

				<!-- <div class="row">
					<div class="col-lg-7 col-sm-12 col-12 d-flex">
						<div class="card flex-fill">
							<div class="card-header pb-0 d-flex justify-content-between align-items-center">
								<h5 class="card-title mb-0">Purchase &amp; Sales</h5>
								<div class="graph-sets">
									<ul>
										<li>
											<span>Sales</span>
										</li>
										<li>
											<span>Purchase</span>
										</li>
									</ul>
									<div class="dropdown">
										<button aria-expanded="false" class="btn btn-white btn-sm dropdown-toggle"
											data-bs-toggle="dropdown" id="dropdownMenuButton" type="button">
											2022 <img alt="img" class="ms-2" src="img/icons/dropdown.svg" />
										</button>
										<ul aria-labelledby="dropdownMenuButton" class="dropdown-menu">
											<li>
												<a class="dropdown-item" href="javascript:void(0);">2022</a>
											</li>
											<li>
												<a class="dropdown-item" href="javascript:void(0);">2021</a>
											</li>
											<li>
												<a class="dropdown-item" href="javascript:void(0);">2020</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div id="sales_charts"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-5 col-sm-12 col-12 d-flex">
						<div class="card flex-fill">
							<div class="card-header pb-0 d-flex justify-content-between align-items-center">
								<h4 class="card-title mb-0">Recently Added Products</h4>
								<div class="dropdown">
									<a aria-expanded="false" class="dropset" data-bs-toggle="dropdown"
										href="javascript:void(0);">
										<i class="fa fa-ellipsis-v"></i>
									</a>
									<ul aria-labelledby="dropdownMenuButton" class="dropdown-menu">
										<li>
											<a class="dropdown-item" href="productlist.html">Product List</a>
										</li>
										<li>
											<a class="dropdown-item" href="addproduct.html">Product Add</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive dataview">
									<table class="table datatable">
										<thead>
											<tr>
												<th>Sno</th>
												<th>Products</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td class="productimgname">
													<a class="product-img" href="productlist.html">
														<img alt="product" src="img/product/product22.jpg" />
													</a>
													<a href="productlist.html">Apple
														Earpods</a>
												</td>
												<td>$891.2</td>
											</tr>
											<tr>
												<td>2</td>
												<td class="productimgname">
													<a class="product-img" href="productlist.html">
														<img alt="product" src="img/product/product23.jpg" />
													</a>
													<a href="productlist.html">iPhone
														11</a>
												</td>
												<td>$668.51</td>
											</tr>
											<tr>
												<td>3</td>
												<td class="productimgname">
													<a class="product-img" href="productlist.html">
														<img alt="product" src="img/product/product24.jpg" />
													</a>
													<a href="productlist.html">samsung</a>
												</td>
												<td>$522.29</td>
											</tr>
											<tr>
												<td>4</td>
												<td class="productimgname">
													<a class="product-img" href="productlist.html">
														<img alt="product" src="img/product/product6.jpg" />
													</a>
													<a href="productlist.html">Macbook
														Pro</a>
												</td>
												<td>$291.01</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="card mb-0">
					<div class="card-body">
						<h4 class="card-title">Expired Products</h4>
						<div class="table-responsive dataview">
							<table class="table datatable">
								<thead>
									<tr>
										<th>SNo</th>
										<th>Product Code</th>
										<th>Product Name</th>
										<th>Brand Name</th>
										<th>Category Name</th>
										<th>Expiry Date</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td><a href="javascript:void(0);">IT0001</a></td>
										<td class="productimgname">
											<a class="product-img" href="productlist.html">
												<img alt="product" src="img/product/product2.jpg" />
											</a>
											<a href="productlist.html">Orange</a>
										</td>
										<td>N/D</td>
										<td>Fruits</td>
										<td>12-12-2022</td>
									</tr>
									<tr>
										<td>2</td>
										<td><a href="javascript:void(0);">IT0002</a></td>
										<td class="productimgname">
											<a class="product-img" href="productlist.html">
												<img alt="product" src="img/product/product3.jpg" />
											</a>
											<a href="productlist.html">Pineapple</a>
										</td>
										<td>N/D</td>
										<td>Fruits</td>
										<td>25-11-2022</td>
									</tr>
									<tr>
										<td>3</td>
										<td><a href="javascript:void(0);">IT0003</a></td>
										<td class="productimgname">
											<a class="product-img" href="productlist.html">
												<img alt="product" src="img/product/product4.jpg" />
											</a>
											<a href="productlist.html">Stawberry</a>
										</td>
										<td>N/D</td>
										<td>Fruits</td>
										<td>19-11-2022</td>
									</tr>
									<tr>
										<td>4</td>
										<td><a href="javascript:void(0);">IT0004</a></td>
										<td class="productimgname">
											<a class="product-img" href="productlist.html">
												<img alt="product" src="img/product/product5.jpg" />
											</a>
											<a href="productlist.html">Avocat</a>
										</td>
										<td>N/D</td>
										<td>Fruits</td>
										<td>20-11-2022</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div> -->

			</div>
		</div>

</body>