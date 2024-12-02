<?php
require_once '../system/connect.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $conn->begin_transaction();

    $systemQuery = "SELECT trans_id FROM system FOR UPDATE";
    $systemResult = $conn->query($systemQuery);

    if ($systemResult) {
        $systemData = $systemResult->fetch_assoc();
        $trans_id = $systemData['trans_id'];
        $trans_id = $trans_id + 1;

        $updateSystemQuery = "UPDATE system SET trans_id = $trans_id";
        if ($conn->query($updateSystemQuery)) {
            $total = isset($_POST['total']) ? str_replace(['₱', ','], '', $_POST['total']) : '0.00';
            $payment = isset($_POST['payment']) ? str_replace(['₱', ','], '', $_POST['payment']) : '0.00';
            $change = isset($_POST['change']) ? str_replace(['₱', ','], '', $_POST['change']) : '0.00';

            $cart = isset($_POST['cart']) ? $_POST['cart'] : '';

            $highestIdQuery = "SELECT MAX(id) AS max_id FROM trans_product";
            $highestIdResult = $conn->query($highestIdQuery);

            if ($highestIdResult) {
                $row = $highestIdResult->fetch_assoc();
                $maxId = $row['max_id'];

                if ($maxId === null) {
                    $maxId = 0;
                }

                if (!empty($cart)) {
                    $cartData = json_decode($cart, true);

                    if (is_array($cartData)) {
                        foreach ($cartData as $item) {
                            $name = $item['name'];
                            $price = $item['price'];
                            $quantity = $item['quantity'];

                            $amount = $quantity * $price;

                            $maxId++;

                            $insertTransProductQuery = "INSERT INTO trans_product (id, trans_id, item, quantity, price, amount) VALUES (?, ?, ?, ?, ?, ?)";
                            $stmt = $conn->prepare($insertTransProductQuery);
                            $stmt->bind_param("ddssdd", $maxId, $trans_id, $name, $quantity, $price, $amount);

                            if (!$stmt->execute()) {
                                $conn->rollback();
                                echo "Error inserting item into trans_product: " . $conn->error . "<br>";
                                exit;
                            }
                        }
                    } else {
                        $conn->rollback();
                        echo "Invalid cart data format.<br>";
                        exit;
                    }
                } else {
                    $conn->rollback();
                    echo "Cart is empty.<br>";
                    exit;
                }
            } else {
                $conn->rollback();
                echo "Error fetching the highest id: " . $conn->error . "<br>";
                exit;
            }

            $insertQuery = "INSERT INTO transaction (trans_id, payment, total, `change`) VALUES (?, ?, ?, ?)";
            $stmt = $conn->prepare($insertQuery);
            $stmt->bind_param("dsss", $trans_id, $payment, $total, $change);
            $total = (float) str_replace('₱', '', $total);
            $payment = (float) str_replace('₱', '', $payment);
            $change = (float) str_replace('₱', '', $change);

            if ($stmt->execute()) {
                $conn->commit();
                echo "Data inserted successfully.";
            } else {
                $conn->rollback();
                echo "Error inserting data: " . $stmt->error;
            }
        } else {
            $conn->rollback();
            echo "Error updating system trans_id: " . $conn->error . "<br>";
        }
    } else {
        $conn->rollback();
        echo "Error fetching current trans_id: " . $conn->error . "<br>";
    }
} else {
    echo "Invalid request method.";
}
?>