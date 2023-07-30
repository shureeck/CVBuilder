SELECT period,
       degree,
       university
FROM   cvbuilder.education
WHERE  education.user_id = ?
       AND education.cv_id = ?