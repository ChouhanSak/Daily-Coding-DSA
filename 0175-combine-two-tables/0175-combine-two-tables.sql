# Write your MySQL query statement below
SELECT 
firstName, 
lastName, 
city, 
state
FROM Person AS per
LEFT JOIN Address AS addr
    ON per.personId = addr.personId
    ;