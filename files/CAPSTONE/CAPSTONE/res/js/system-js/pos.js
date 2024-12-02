$(document).ready(function () {
    // INITIALIZE DATA
    const paymentValueElement = document.getElementById('payment-value');
    paymentValueElement.textContent = '₱0.00';
    const cart = getCartFromLocalStorage();
    if (isLocalHistoryEnabled()) {
        renderCart(cart);
    }

    // TOTAL ITEMS
    function calculateTotalItems(cart) {
        const uniqueItems = new Set();
        cart.forEach(item => {
            uniqueItems.add(item.name);
        });
        return uniqueItems.size;
    }

    // PAYMENT UPDATE
    function updateCartSummary(cart) {
        const subtotalElement = document.getElementById('subtotal-value');
        const taxElement = document.getElementById('tax-value');
        const totalElement = document.getElementById('total-value');

        const subtotal = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);
        const taxRate = 0.10;
        const tax = subtotal * taxRate;
        const total = subtotal + tax;
        subtotalElement.textContent = '₱' + subtotal.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
        taxElement.textContent = '₱' + tax.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
        totalElement.textContent = '₱' + total.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
    }

    // DELETE TRANSACTION
    function clearCart() {
        if (cart.length === 0) {
            Swal.fire({
                title: 'No Items',
                text: 'Cart is empty.',
                icon: 'info'
            });
        } else {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-success',
                    cancelButton: 'btn btn-danger'
                },
                buttonsStyling: false
            });

            swalWithBootstrapButtons.fire({
                title: 'Delete Transaction',
                text: 'Are you sure you want to clear cart?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes!',
                cancelButtonText: 'No, cancel',
                reverseButtons: false
            }).then((result) => {
                if (result.isConfirmed) {
                    cart.length = 0;
                    renderCart(cart);
                    document.getElementById('total-items').textContent = 0;

                    const paymentAmount = 0; // Set payment amount to 0
                    paymentValueElement.textContent = '₱0.00';

                    swalWithBootstrapButtons.fire(
                        'Cleared!',
                        'Cart has been cleared.',
                        'success'
                    );

                    updateCartSummary(cart);
                    renderCart(cart);
                    updateChangeValue(cart);
                }
            });
        }
    }

    // UPDATE QUANTITY
    document.addEventListener('click', function (event) {
        if (event.target.classList.contains('update-quantity-button')) {
            const itemName = event.target.getAttribute('data-name');
            const currentQuantity = parseInt(event.target.getAttribute('data-quantity'));

            Swal.fire({
                title: 'Update Quantity',
                input: 'text',
                inputLabel: 'New Quantity',
                inputValue: currentQuantity,
                showCancelButton: true,
                inputValidator: (value) => {
                    if (!value || isNaN(value) || parseInt(value) < 0) {
                        return 'Please enter a valid quantity.';
                    } else if (parseInt(value) > 999) {
                        return 'Maximum quantity allowed is 999.';
                    }
                },
            }).then((result) => {
                if (result.isConfirmed) {
                    let newQuantity = parseInt(result.value);
                    newQuantity = Math.min(newQuantity, 999);

                    const cartItem = cart.find((item) => item.name === itemName);
                    if (cartItem) {
                        cartItem.quantity = newQuantity;
                        updateCartSummary(cart);
                        renderCart(cart);
                        updateChangeValue(cart);
                    }
                }
            });
        }
    });

    //  UPDATE CART
    const productItems = document.querySelectorAll('.productset');

    productItems.forEach(function (item) {
        item.addEventListener('click', function () {
            const itemName = item.querySelector('h4').textContent;
            const barcode = item.querySelector('h5').textContent;
            const priceText = item.querySelector('.productsetcontent h6').textContent;
            const price = parseFloat(priceText.replace('₱', ''));

            const imageSrc = item.querySelector('img').src;

            const cartItem = cart.find(item => item.name === itemName);

            if (cartItem) {
                if (cartItem.quantity < 999) {
                    cartItem.quantity++;
                } else {
                    Swal.fire('Quantity Limit Reached', 'You cannot add more of this item.', 'warning');
                }
            } else {
                cart.push({ name: itemName, barcode, price, quantity: 1, imageSrc });
            }

            updateChangeValue(cart);
            renderCart(cart);
        });
    });

    // CART RENDERING / ADD ITEMS
    function renderCart(cart) {
        const cartContainer = document.getElementById('cart');
        const totalItemsElement = document.getElementById('total-items');
        cartContainer.innerHTML = '';

        if (cart.length === 0) {
            const paymentAmount = 0;
            paymentValueElement.textContent = '₱0.00';
        }

        updateChangeValue(cart);
        totalItemsElement.textContent = calculateTotalItems(cart);
        updateCartSummary(cart);
        saveCartToLocalStorage(cart);

        cart.forEach(item => {
            const cartItemHTML = `
				<ul class="product-lists">
					<li>
						<div class="productimg">
							<div class="productimgs">
								<img src="${item.imageSrc}" alt="img">
							</div>
							<div class="productcontet">
								<h4>${item.name}</h4>
								<div class="productlinkset">
									<h5>${item.barcode}</h5>
								</div>
								<button class="update-quantity-button inc button btn btn-secondary" data-name="${item.name}" data-quantity="${item.quantity}">
									Update Quantity
								</button>
							</div>
						</div>
					</li>
					<div class="productquantity">
						Quantity: ${item.quantity}<br>
					</div>
					<li>₱${(item.price * item.quantity).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}</li>
					<li><a href="#" class="delete-item-button" data-name="${item.name}"><img src="https://dreamspos.dreamguystech.com/laravel/template/public/assets/img/icons/delete-2.svg" alt="img"></a></li>
				</ul>`;

            cartContainer.insertAdjacentHTML('beforeend', cartItemHTML);
        });
    }

    // INDIVIDUAL ITEM DELETE
    $("#cart").on("click", ".delete-item-button", function () {
        const itemName = $(this).data("name");
        const itemIndex = cart.findIndex(item => item.name === itemName);

        if (itemIndex !== -1) {
            Swal.fire({
                title: 'Delete Item',
                text: 'Are you sure you want to delete this item?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
                cancelButtonText: 'No',
                customClass: {
                    confirmButton: 'btn btn-success',
                    cancelButton: 'btn btn-danger'
                },
                buttonsStyling: false
            }).then((result) => {
                if (result.isConfirmed) {
                    cart.splice(itemIndex, 1);
                    renderCart(cart);
                }
            });
        }
    });

    // ADD PAYMENT
    function payment() {
        const subtotal = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);
        const taxRate = 0.10;
        const tax = subtotal * taxRate;
        const total = subtotal + tax;

        if (total === 0) {
            Swal.fire({
                title: 'No Item Selected',
                text: 'Add items to the cart before making a payment.',
                icon: 'info'
            });
        } else {
            Swal.fire({
                title: 'Enter Payment Amount',
                input: 'text',
                showCancelButton: true,
                inputValidator: (value) => {
                    if (!value || isNaN(value) || parseFloat(value) < total) {
                        return 'Payment amount must be at least equal to the total.';
                    }
                },
            }).then((result) => {
                if (result.isConfirmed) {
                    const paymentAmount = parseFloat(result.value);
                    paymentValueElement.textContent = '₱' + paymentAmount.toFixed(2);
                    updateChangeValue(cart);
                }
            });
        }
    }


    // UPDATE CHANGE 
    function updateChangeValue(cart) {
        const paymentAmountElement = document.getElementById('payment-value');
        const paymentAmount = parseFloat(paymentAmountElement.textContent.replace('₱', ''));

        const subtotal = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);
        const taxRate = 0.10;
        const tax = subtotal * taxRate;
        const total = subtotal + tax;
        const change = paymentAmount - total;

        const changeValueElement = document.getElementById('change-value');
        changeValueElement.textContent = '₱' + change.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
    }

    // LOCAL HISTORY
    function isLocalHistoryEnabled() {
        return localStorage.getItem('localHistoryEnabled') === 'true';
    }

    // PROMPT LOCAL HISTORY
    // PROMPT LOCAL HISTORY
    $("#store-history").on("click", function () {
        Swal.fire({
            title: 'Local History',
            text: isLocalHistoryEnabled() ? 'Local history is currently enabled. Do you want to turn it off?' : 'Do you want to enable local history?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: isLocalHistoryEnabled() ? 'Turn Off' : 'Enable',
            cancelButtonText: 'No',
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        }).then((result) => {
            if (result.isConfirmed) {
                if (isLocalHistoryEnabled()) {
                    disableLocalHistoryAndClearData();
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                } else {
                    localStorage.setItem('localHistoryEnabled', 'true');
                    Swal.fire('Local History Enabled', 'Your local history will be saved. <b>(Page will Reload)<b>', 'success');

                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                }
            }
        });
    });

    // DISABLE LOCAL HISTORY
    function disableLocalHistoryAndClearData() {
        localStorage.removeItem('localHistoryEnabled');
        localStorage.removeItem('cartItems');
        Swal.fire('Local History Disabled', 'You have turned off local history and cleared the data. <b>(Page will Reload)<b>', 'info');
    }

    // CONFIRM DISABLE HISTORY
    $("#disable-history").on("click", function () {
        if (isLocalHistoryEnabled()) {
            Swal.fire({
                title: 'Disable Local History?',
                text: 'This will turn off the local history feature',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Yes',
                cancelButtonText: 'No',
                customClass: {
                    confirmButton: 'btn btn-success',
                    cancelButton: 'btn btn-danger'
                },
                buttonsStyling: false
            }).then((result) => {
                if (result.isConfirmed) {
                    disableLocalHistory();
                }
            });
        } else {
            Swal.fire('Local History Already Disabled', 'You have already turned off local history.', 'info');
        }
    });


    // LISTENERS
    $("#clear-cart-link").on("click", clearCart);
    $("#cash-payment").on("click", payment);
    document.getElementById('proceed-transaction-button').addEventListener('click', saveTransaction);

    // LOCAL STORAGE
    function saveCartToLocalStorage(cart) {
        if (isLocalHistoryEnabled()) {
            localStorage.setItem('cartItems', JSON.stringify(cart));
        }
    }

    function getCartFromLocalStorage() {
        const cartData = localStorage.getItem('cartItems');
        return cartData ? JSON.parse(cartData) : [];
    }

    // SAVE  TRANSACTION
    function saveTransaction() {
        if (cart.length === 0) {
            Swal.fire({
                title: 'Empty Cart',
                text: 'You cannot proceed with an empty cart. Please add items to the cart first.',
                icon: 'info'
            });
            return;
        }

        const subtotal = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);
        const taxRate = 0.10;
        const tax = subtotal * taxRate;
        const total = subtotal + tax;

        const paymentAmountElement = document.getElementById('payment-value');
        const paymentAmount = parseFloat(paymentAmountElement.textContent.replace('₱', ''));
        const change = paymentAmount - total;

        if (change < 0) {
            Swal.fire({
                title: 'Insufficient Payment',
                text: 'The payment amount is less than the total. Please provide a sufficient payment.',
                icon: 'error'
            });
        } else {
            $.ajax({
                url: '../system/transaction.php',
                method: 'POST',
                data: {
                    subtotal: subtotal,
                    tax: tax,
                    total: total,
                    payment: paymentAmount,
                    change: change,
                    cart: JSON.stringify(cart)
                },
                success: function (response) {
                    cart.length = 0;
                    renderCart(cart);
                    document.getElementById('total-items').textContent = 0;
                    paymentValueElement.textContent = '₱0.00';
                    updateCartSummary(cart);
                    updateChangeValue(cart);

                    Swal.fire({
                        title: 'Transaction Saved',
                        text: 'The transaction has been saved successfully.',
                        icon: 'success',
                        timer: 1500, // Close the message after 3 seconds
                        timerProgressBar: true // Display a progress bar
                    }).then(() => {
                        // You can add additional actions here if needed
                    });
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        title: 'Error',
                        text: 'An error occurred while saving the transaction.',
                        icon: 'error'
                    });
                }
            });
        }
    }


});


