SELECT period,
       position,
       company,
       reponsibility
FROM   cvbuilder.work_expirience
WHERE  work_expirience.user_id = ?
       AND work_expirience.cv_id = ?