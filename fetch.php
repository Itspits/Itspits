<!-- Add PHP code to fetch products -->
<?php
include('ecommerce.php');
$products = getAllProducts();
?>

<!-- ... -->

<div class="section2">
  <div class="container">
    <?php foreach ($products as $product) : ?>
      <div class="items">
        <div class="img img1"><img src="<?php echo $product['image_url']; ?>" alt=""></div>
        <div class="name"><?php echo $product['name']; ?></div>
        <div class="price">$<?php echo $product['price']; ?></div>
        <div class="info"><?php echo $product['description']; ?></div>
      </div>
    <?php endforeach; ?>
  </div>
</div>
