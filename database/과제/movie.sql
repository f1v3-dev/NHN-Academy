# 1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.

SELECT ReleaseYear, Title, RunningTIme, Plot
FROM movie
WHERE KoreanTitle = '퍼스트 맨';

-- 

# 2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요

SELECT Title, KoreanTitle
FROM movie
WHERE ReleaseYear = 2003;

--

# 3. 영화 '글래디에이터'의 작곡가를 고르세요

SELECT p.Name, p.KoreanName
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN movie AS m ON a.MovieID = m.MovieID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE m.KoreanTitle = '글래디에이터'
		AND r.RoleKorName = '작곡';

--


# 4. 영화 '매트릭스'의 감독이 몇명인지 출력
                
SELECT count(*) AS 'Director Count'
FROM appear as a
INNER JOIN movie as m ON a.MovieID = m.MovieID
INNER JOIN role as r ON a.RoleID = r.RoleID
WHERE m.KoreanTitle = '매트릭스'
		AND r.RoleKorName = '감독';
        


--

# 5. 감독이 2명 이상인 영화를 출력하세요

SELECT m.*
FROM movie AS m
INNER JOIN appear AS a ON m.MovieID = a.MovieID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독'
GROUP BY m.MovieID
HAVING count(m.MovieID) >= 2;


--

# 6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요

SELECT m.Title, m.KoreanTitle, m.Plot
FROM movie AS m
INNER JOIN appear AS a ON m.MovieID = a.MovieID
INNER JOIN person AS p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve award ON award.AppearID = a.AppearID
INNER JOIN winning AS w ON award.WinningID = w.WinningID
WHERE w.WinOrNot = 'Winner'
		AND p.KoreanName = '한스 짐머';
        

-- 

# 7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요

SELECT m.Title, m.KoreanTItle, p1.KoreanName AS Director, p2.KoreanName AS Actor
FROM movie AS m
INNER JOIN appear AS a1 ON m.MovieID = a1.MovieID
INNER JOIN person AS p1 ON a1.PersonID = p1.PersonID
INNER JOIN role AS r1 ON a1.RoleID = r1.RoleID
INNER JOIN appear AS a2 ON m.MovieID = a2.MovieID
INNER JOIN person AS p2 ON a2.PersonID = p2.PersonID
INNER JOIN role AS r2 On a2.RoleID = r2.RoleID
WHERE (p1.KoreanName = '제임스 카메론' AND  r1.RoleName='Director')
	AND (p2.KoreanName = '아놀드 슈워제네거' AND r2.RoleName = 'Actor');




# 8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
SELECT m.Title, m.KoreanTitle, p.KoreanName, m.RunningTime
FROM movie AS m
INNER JOIN appear AS a ON m.MovieID = a.MovieID
INNER JOIN person AS p ON a.PersonID = p.PersonID
INNER JOIN role AS r ON r.RoleID = a.RoleID
WHERE p.KoreanName = '레오나르도 디카프리오'
	AND r.RoleKorName = '배우'
	AND m.RunningTime >= 100;



# 9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오

SELECT m.Title, m.KoreanTitle, m.BoxOfficeWWGross, g.GradeInKoreaName
FROM movie AS m
INNER JOIN gradeinkorea AS g ON m.GradeInKoreaID = g.GradeInKoreaID
WHERE g.GradeInKoreaName = '청소년 관람불가'
ORDER BY BoxOfficeWWGross DESC
LIMIT 1;


# 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오

SELECT AVG(BoxOfficeWWGross) AS '1999년 이전 영화 수익 평균'
FROM movie
WHERE ReleaseYear < 1999;


-- 
# 11. 가장 많은 제작비가 투입된 영화를 고르시오.

SELECT Title, KoreanTitle, Budget
FROM movie
ORDER BY Budget DESC
LIMIT 1;


# 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?

SELECT p.KoreanName, sum(m.Budget) AS Total
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN role AS r On a.RoleID = r.RoleID
INNER JOIN movie AS m ON a.MovieID = m.MovieID
WHERE r.RoleKorName = '감독'
GROUP BY p.PersonID
ORDER BY Total DESC
LIMIT 1;


# 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.

SELECT p.KoreanName, sum(m.BoxOfficeWWGross) AS Total
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN movie AS m ON a.MovieID = m.MovieID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE r.RoleKorName IN ('배우')
GROUP BY p.PersonID
ORDER BY Total DESC
LIMIT 1;


# 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)

SELECT Title, KoreanTitle, Budget, BoxOfficeWWGross
FROM movie
WHERE Budget > 0
ORDER BY Budget
LIMIT 1;



# 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요

SELECT AVG(BoxOfficeUSGross) AS US_AVG
FROM movie
WHERE Budget <= 50000000;

# 16. 액션 장르 영화의 평균 수익을 고르세요

SELECT AVG(BoxOfficeWWGross) AS 'AVG BoxOffice'
FROM movie AS m
INNER JOIN moviegenre AS mg ON m.MovieID = mg.MovieID
INNER JOIN genre AS g ON mg.GenreID = g.GenreID
WHERE g.GenreKorName = '액션';




# 17. 드라마, 전쟁 장르의 영화를 고르세요.

SELECT *
FROM movie AS m
INNER JOIN moviegenre AS mg ON m.MovieID = mg.MovieID
INNER JOIN genre AS g ON g.GenreID = mg.GenreID
WHERE g.GenreKorName IN ('드라마', '전쟁');



# 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.

SELECT m.Title, m.KoreanTitle, ReleaseYear
FROM movie AS m
INNER JOIN appear AS a ON m.MovieID = a.MovieID
INNER JOIN person AS p ON a.PersonID = p.PersonID
WHERE p.KoreanName = '톰 행크스'
ORDER BY RunningTime DESC
LIMIT 1;


# 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오

SELECT p.Name, p.KoreanName, count(p.PersonID) AS TotalAwards
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN awardinvolve AS ai ON a.AppearID = ai.AppearID
INNER JOIN sector AS s ON ai.SectorID = s.SectorID
INNER JOIN winning AS w ON ai.WinningID = w.WinningID
WHERE s.SectorKorName = '남우주연상' AND w.WinOrNot = 'Winner'
GROUP BY p.PersonID
ORDER BY TotalAwards DESC
LIMIT 1;

# 20. 아카데미상을 가장 많이 수상한 배우를 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)

SELECT p.Name, p.KoreanName, count(p.PersonID) AS TotalAwards
FROM person AS p
INNER JOIN appear AS a ON a.PersonID = p.PersonID
INNER JOIN role AS r ON a.RoleID = r.RoleID
INNER JOIN awardinvolve AS ai ON a.AppearID = ai.AppearID
INNER JOIN awardyear AS ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN winning AS w ON ai.WinningID = w.WinningID
WHERE w.WinOrNot = 'Winner' AND p.KoreanName != '수상자 없음' AND r.RoleKorName IN ('배우')
GROUP BY p.PersonID
ORDER BY TotalAwards DESC
LIMIT 1;


# 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오

SELECT p.*
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN awardinvolve AS ai ON a.AppearID = ai.AppearID
INNER JOIN sector AS s ON ai.SectorID = s.SectorID
INNER JOIN winning AS w ON ai.WinningID = w.WinningID
WHERE w.WinOrNot = 'Winner' AND s.SectorKorName = '남우주연상'
GROUP BY p.PersonID
HAVING count(p.PersonID) >= 2;

# 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.

SELECT p.Name, p.KoreanName, count(p.PersonID) AS Total
FROM person AS p
INNER JOIN appear AS a ON a.PersonID = p.PersonID
INNER JOIN awardinvolve AS ai ON a.AppearID = ai.AppearID
INNER JOIN awardyear AS ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN winning AS w ON ai.WinningID = w.WinningID
WHERE w.WinOrNot = 'Winner' AND p.KoreanName != '수상자 없음'
GROUP BY p.PersonID
ORDER BY Total DESC
LIMIT 1;



# 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.

SELECT m.Title, m.KoreanTitle, m.Plot, count(DISTINCT ai.SectorID) AS totalNominated
FROM movie AS m
	INNER JOIN appear AS a ON m.MovieID = a.MovieID
    INNER JOIN awardinvolve AS ai ON a.AppearID = ai.AppearID
GROUP BY m.MovieID
ORDER BY totalNominated DESC;


# 25. 가장 많은 영화에 출연한 여배우를 고르세요.

SELECT p.Name, p.KoreanName, count(p.PersonID) AS total
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN movie AS m ON a.MovieID = m.MovieID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE r.RoleName = 'Actress'
GROUP BY p.PersonID
ORDER BY total DESC
LIMIT 1;


# 26. 수익이 가장 높은 영화 TOP 10을 출력하세요. 

SELECT * 
FROM movie
ORDER BY BoxOfficeWWGross DESC
LIMIT 10;



# 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.

SELECT *
FROM movie
WHERE BoxOfficeWWGross >= 1000000000 
	AND Budget <= 100000000;



# 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.

SELECT p.Name, p.KoreanName, count(p.PersonID) AS total
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN movie AS m ON a.MovieID = m.MovieID
INNER JOIN moviegenre AS mg ON m.MovieID = mg.MovieID
INNER JOIN genre AS g ON mg.GenreID = g.GenreID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE g.GenreKorName = '전쟁' AND r.RoleKorName = '감독'
GROUP BY p.PersonID
ORDER BY total DESC
LIMIT 1;



# 29. 드라마에 가장 많이 출연한 사람을 고르세요.

SELECT p.Name, p.KoreanName, count(p.PersonID) AS total
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN moviegenre AS mg ON a.MovieID = mg.MovieID
INNER JOIN genre AS g ON mg.GenreID = g.GenreID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우' AND g.GenreKorName = '드라마'
GROUP BY p.PersonID
ORDER BY total DESC
LIMIT 1;

# 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.

SELECT p.Name, p.KoreanName
FROM person AS p
INNER JOIN appear AS a ON p.PersonID = a.PersonID
INNER JOIN movie AS m ON a.MovieID = m.MovieID
INNER JOIN moviegenre AS mg ON m.MovieID = mg.MovieID
INNER JOIN genre AS g ON mg.GenreID = g.GenreID
INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE g.GenreName = 'Drama' AND g.GenreName != 'Horror'
	AND r.RoleKorName = '배우'
GROUP BY p.PersonID;

SELECT *
FROM genre;


# 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?

SELECT Location, count(Location) AS total
FROM awardyear
GROUP BY Location
ORDER BY total DESC
LIMIT 1;


# 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?

SELECT (YEAR(CURDATE()) - YEAR) AS '회차'
FROM awardyear
ORDER BY Year ASC
LIMIT 1;