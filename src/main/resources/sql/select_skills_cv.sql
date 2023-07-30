SELECT skills.skill
FROM   cvbuilder.skills
       JOIN cvbuilder.user_skills
         ON user_skills.skill_id = skills.id
WHERE  user_skills.user_id = ?
       AND user_skills.cv_id = ?