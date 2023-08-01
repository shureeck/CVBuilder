SELECT
  cv_id,
  description
FROM
  cvbuilder.cvs
WHERE
  user_id = ?;
