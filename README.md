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
