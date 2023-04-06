Creating a Payment Service with Coupon, Point, and Payment Notification
===

Concept
---
Account, Payment, Point policy
- Behaviors: Payment processing, Point accumulation, Notification

Payment1
---
- The customer's account already exists. If not, an exception is thrown.
- Payment is dependent on the account.
- Payment request information and customer information (customer ID, etc.) are passed.
- The payment amount must be valid (cannot be negative).
- Accumulation based on the applicable point rate with respect to the actual payment amount.
- Points are accumulated in the customer's account.
- After the payment is completed, an SMS notification occurs (dummy). Even if notification sending fails, the payment is completed successfully.
- Receipt is issued after the payment is completed.

Payment2
---
The customer can use accumulated points when making a payment.

Payment3
---
After the payment is completed, not only SMS but also push notifications (dummy) and emails are sent.

Payment4
---
Applicable to fixed amount and % discount coupons - 1000 won coupon, 10% discount coupon.

