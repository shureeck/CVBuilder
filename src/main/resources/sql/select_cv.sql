SELECT personal_data.first_name,
       personal_data.last_name,
       personal_data.position,
       skills.skill
FROM   cvbuilder.personal_data
       JOIN cvbuilder.user_skills
         ON personal_data.user_id = user_skills.user_id
            AND personal_data.cv_id = user_skills.cv_id
       JOIN cvbuilder.skills
         ON user_skills.skill_id = skills.id
WHERE  personal_data.user_id = ?
       AND personal_data.cv_id = ?