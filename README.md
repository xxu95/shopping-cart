Shopping Cart
========

### Technology Stack:
Languages: Java, JSP, HTML, CSS and JavaScript<br>
Database: MySQL<br>
Tools & Frameworks: jQuery, AJAX, Hibernate

Modules:
----------
* [Sign Up](#sign-up)
* [Sign In/Out](#sign-inout)
* [Home Page](#home-page)
  * [Add Items to Cart](#add-items-to-cart)
  * [Add Items to Wish List](#add-items-to-wish-list)
* [Cart](#cart)
  * [Manage Quantity of Items](#manage-quantity-of-items)
  * [Move Items to Wish List](#move-items-to-wish-list)
  * [Remove Items](#remove-items)
  * [Order Summary](#order-summary)
* [Wish List](#wish-list)
  * [Manage Quantity of Items](#manage-quantity-of-items-1)
  * [Move Items to Cart](#move-items-to-cart)
  * [Remove Items](#remove-items-1)

## Sign Up
Each page has the same navbar providing redirections to home page, cart, wish list, sign up and sign in/out. 
The sign up module is built using bootstrap modal.  

## Sign In/Out
Sign in module is built using bootstrap modal. It checks username and password using jQuery and AJAX. 
If your information is verified, it will redirect to the page and replace the sign up and sign in labels with sign out and welcome labels.
Meanwhile, all items in the temporary cart will be automatically added into the your cart.

## Home Page
List all the laptops and storages that are on sale including title, description, image and price.

#### Add Items to Cart
Click the cart icon to add laptop to the cart or check the desired storages and click the `Add to cart` button to add storage to the cart.

#### Add Items to Wish List
Click the star icon to add laptop to the wish list(This function needs login).

## Cart
List all products that are in the cart including title, category, image, quantity and price.

#### Manage Quantity of Items
Enter the target value and click the refresh icon to modify the quantity of the item.

#### Move Items to Wish List
Click the star icon to save the item to wish list.

#### Remove Items
Click the trash icon to remove the item from cart.

#### Order Summary
Make a summary and show the total price of the order.

## Wish List
List all products that are in the wish list including title, category, image, quantity and price. 
You need to login whenever you want to access the wish list page or move items to wish list.

#### Manage Quantity of Items
Enter the target value and click the refresh icon to modify the quantity of the item.

#### Move Items to Cart
Click the cart icon to move the item to cart.

#### Remove Items
Click the trash icon to remove the item from wish list.

