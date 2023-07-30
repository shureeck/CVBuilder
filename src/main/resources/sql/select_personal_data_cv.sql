SELECT first_name,
       middle_name,
       last_name,
       position
FROM   cvbuilder.personal_data
WHERE  personal_data.user_id = ?
       AND personal_data.cv_id = ?