SELECT contact_type,
       contact_data
FROM   cvbuilder.contacts
WHERE  contacts.user_id = ?
       AND contacts.cv_id = ?