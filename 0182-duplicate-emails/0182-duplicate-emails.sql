# Write your MySQL query statement below
SELECT email AS Email
FROM (
    SELECT email, COUNT(email) AS email_count
    FROM Person
    GROUP BY email
) AS temp
WHERE email_count > 1;