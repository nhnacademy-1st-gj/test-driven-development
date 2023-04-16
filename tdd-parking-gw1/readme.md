## Specification 1
A car enters the parking lot.
When a car enters, the license plate is scanned.

The car parks in space A-1.
The car is parked in a specific parking space (ParkingSpaces).

The car leaves the parking lot. Payment must be made for the duration of the parking time.
If there is no money, the car cannot leave.

Fee schedule
| Time | Fee | Note |
| --- | --- | --- |
| First 30 minutes | 1,000 won |  |
| Additional 10 minutes | 500 won | Charged even for an extra second. |
| Daily parking | 10,000 won | Maximum daily fee. Additional fees will be charged after 24:00. For parking two consecutive days, the fee is 20,000 won. |

- If a car parks for 30 minutes and 1 second, the fee is 1,500 won.
- If a car parks for 50 minutes, the fee is 2,000 won.
- If a car parks for 61 minutes, the fee is 3,000 won.
- If a car parks for 6 hours, the fee is 10,000 won.

## Specification 2
There are n entrance gates to the parking lot.
There are also n exit gates.
Use polymorphism.

## Specification 3
The fee schedule has changed.

Fee schedule
| Time | Fee | Note |
| --- | --- | --- |
| First 30 minutes | 1,000 won |  |
| Additional 10 minutes | 500 won | Charged even for an extra second. |
| Daily parking | 10,000 won | Maximum daily fee. Additional fees will be charged after 24:00. For parking two consecutive days, the fee is 20,000 won. |

- For small cars, the fee is reduced by 50%.
- Large vehicles (trucks, etc.) cannot park in the parking lot.

## Specification 4
If the user is a member of Payco, the parking fee is discounted by 10%.
Discount only if the user is a member.

There is a hourly parking ticket.
- If a car parks for 3 hours and then presents a 2-hour parking ticket, only 1 hour fee will be charged.
- If a car parks for 59 minutes and presents a 1-hour parking ticket, the fee is free.
