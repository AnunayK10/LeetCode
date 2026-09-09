SELECT 
    d.name AS Department, 
    e.name AS Employee, 
    e.salary AS Salary
FROM (
    SELECT 
        departmentId, 
        name, 
        salary, 
        DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) as rnk
    FROM 
        Employee
) e
JOIN 
    Department d ON e.departmentId = d.id
WHERE 
    e.rnk <= 3;