#####################################
#### RETAIL STORE APP ASSIGNMENT ####
#####################################
###########################
#### PROBLEM STATEMENT ####
###########################

On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

Write a program in a programming language of your choice with test cases such that given a bill,
it finds the net payable amount. Please note the stress is on object oriented approach and test coverage.
User interface is not required.

What we care about:

Required Activities
• Object oriented programming approach, provide a high level UML class diagram of all the key classes in your solution.
  This can either be on paper or using a CASE tool
• Unit tests to achieve good code coverage, good use of Mocking
• Code to be generic and simple
• Leverage today's best coding practices
• Clear README.md that explains how the code and the test can be run and how the coverage reports can be generated

##################
#### SOLUTION ####
##################

My Assumptions:
1.  Total discount is a combination of discount on product and discount offered to USER.
2.  Depending on the their type (AFFILIATE, EMPLOYEE, OLD/NEW CUSTOMER), the user gets (10, 30, 5) percent discount.
3.  Depending on the product purchased the user gets either 5% discount if the product is non-grocery product or
    no discount on the product.
4.  A generic formula for computation of product price can be given as:
    DISCOUNTED_TOTAL_BILL =
    QUANTITY * (  (1-USER_DISCOUNT)*( (1-PRODUCT_DISCOUNT)*(SUM of per unit non-grocery product prices)
    + (SUM of per unit grocery product prices) )  )


Testcase:

We're assuming the shopper purchases 2 item with unit prices as below:

19.99
4.99
14.99
0.99 -- GROCERY
1.99

Then we have 4 test cases:

CASE 1 >> AFFILIATE (0.9)
2*( 0.9*(0.95*(1.99+14.99+4.99+19.99) + 0.99) ) = 73.5336
discount = 12.3664

CASE 2 >> EMPLOYEE (0.7)
2*( 0.7*(0.95*(1.99+14.99+4.99+19.99) + 0.99) ) = 57.1928
discount = 28.7072

CASE 3 >> CUSTOMER (0.95) (Since more than two years)
2*( 0.95*(0.95*(1.99+14.99+4.99+19.99) + 0.99) ) = 77.6188
discount = 8.2812

CASE 4 >> CUSTOMER (0.95) (Since less than two years)
2*( 0.95*(1.99+14.99+4.99+19.99) + 0.99 ) = 81.704
discount = 4.196

**** You can find these test cases in ShoppingApplicationTest class ****