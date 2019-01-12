-- This view provides each booking details, use it in current bookings component!
CREATE VIEW booking_details AS
  SELECT b.id,
    concat_ws(' '::text, u.firstname, u.lastname) AS patient,
    b.date,
    t.description,
    b.attachmentpath
   FROM bookings b,
    users u,
    types t
  WHERE ((b.userid = u.id) AND (b.typeid = t.id))
  ORDER BY b.id;

