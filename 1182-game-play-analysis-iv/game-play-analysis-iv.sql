SELECT 
    ROUND(COUNT(A2.player_id) / COUNT(A1.player_id), 2) AS fraction
FROM (
    -- Step 1: Find the first login date for every player
    SELECT player_id, MIN(event_date) AS first_login
    FROM Activity
    GROUP BY player_id
) A1
-- Step 2: Left join to see if they logged in exactly one day later
LEFT JOIN Activity A2
    ON A1.player_id = A2.player_id 
    AND A2.event_date = DATE_ADD(A1.first_login, INTERVAL 1 DAY);