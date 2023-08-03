<?php
// Function to connect to the database
function connectDatabase()
{
    $host = "your_mysql_host";
    $username = "your_mysql_username";
    $password = "your_mysql_password";
    $database = "ecommerce";

    $conn = new mysqli($host, $username, $password, $database);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    return $conn;
}

// Function to get all products
function getAllProducts()
{
    $conn = connectDatabase();
    $sql = "SELECT * FROM products";
    $result = $conn->query($sql);
    $products = array();

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $products[] = $row;
        }
    }

    $conn->close();
    return $products;
}

// You can create similar functions to handle user registration, login, adding products to the cart, placing orders, etc.
?>
