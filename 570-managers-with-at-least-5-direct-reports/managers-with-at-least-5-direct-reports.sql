SELECT e.name
FROM Employee e
JOIN (
    -- Step 1: Get the IDs of managers who have >= 5 reports
    SELECT managerId
    FROM Employee
    GROUP BY managerId
    HAVING COUNT(managerId) >= 5
) AS m 
-- Step 2: Join back to the main table to get the names
ON e.id = m.managerId;