WITH FilteredStadium AS (
    SELECT 
        id, 
        visit_date, 
        people,
        -- Subtracting row number from ID creates a unique grouping value for consecutive IDs
        id - ROW_NUMBER() OVER(ORDER BY id) AS grp
    FROM Stadium
    WHERE people >= 100
),
GroupCounts AS (
    SELECT 
        id, 
        visit_date, 
        people,
        -- Count how many records share the same group ID
        COUNT(*) OVER(PARTITION BY grp) AS grp_count
    FROM FilteredStadium
)
SELECT id, visit_date, people
FROM GroupCounts
WHERE grp_count >= 3
ORDER BY visit_date ASC;