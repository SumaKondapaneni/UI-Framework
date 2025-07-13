🐞 Defects Found

🔴 Functional Defects
# 1.Room Name Not Unique
Defect: API allows creation of multiple rooms with the same roomName.
Expected: Should return 409 Conflict or a validation error if duplicates are not allowed.
# 2.Not all the rooms are displayed in the CheckIn Availability
Defect: All created rooms are not displayed in the CheckIn Availability.
Expected: All the available rooms should be displayed in the availability
# 3.Invalid Date Range Accepted
Defect: Booking accepted where checkout < checkin.
Expected: Should return a validation error indicating invalid date logic.

# 4.POST /api/booking Returns Empty Despite Existing Bookings
Defect: Bookings are created and confirmed, but not returned in response .
Cause: Could be incorrect filtering on backend or bad linkage of booking to room.
# 5.Able to create bookings with past dates
Defect: Booking accepted with past dates.
Expected: Should return a validation error