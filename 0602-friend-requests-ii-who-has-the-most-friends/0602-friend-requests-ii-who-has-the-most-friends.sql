# Write your MySQL query statement below
WITH TotalFriends AS (
    SELECT id, COUNT(*) AS num
    FROM (
        SELECT requester_id AS id FROM RequestAccepted
        UNION ALL
        SELECT accepter_id AS id FROM RequestAccepted
    ) AS all_friends
    GROUP BY id
)
SELECT id, num
FROM TotalFriends
WHERE num = (SELECT MAX(num) FROM TotalFriends);