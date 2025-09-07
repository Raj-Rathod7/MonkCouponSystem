Implemented Cases :

Create Coupon: Allows creation of coupons with type, threshold, and discount details.

Get All Coupons: Fetches the list of all available coupons from the database.

Get Coupon by ID: Retrieves a specific coupon by its unique id.

Applicable Coupons API: Returns coupons applicable based on cart total or product-specific conditions.

Apply Coupon by Id: Apply coupon by id to cart and return final price after discounts.

Unimplemented Cases :

Coupon Expiry Handling: Expiry date validation.

No special coupons tied to user referrals

User-Specific Coupons: Personalized coupon assignment.

No restriction on how many times a coupon can be used.

Flat discount coupons can be implemented

Free shipping coupons.

First User coupons.

If more than one coupon in applied only one should be applied

Product specific coupon can be applied only if item is present in cart



Coupon Usage Tracking: No implementation for tracking how many times a coupon has been used.

Error responses are basic

No interface to manage coupons

Only one coupon can be applied at a time, multiple coupon stacking is not implemented.


Limitations : 

No support for percentage vs flat discounts combination in the current design. 

Users can reuse the same coupon multiple times. 

Error handling is basic.

Database schema is minimal . 
 

Assumptions : 
 
 A coupon is valid until deleted. 
 
 Coupon details entered are correct and valid. 
 
 No distinction between registered and guest users. 
 
 Coupons are considered active immediately after creation