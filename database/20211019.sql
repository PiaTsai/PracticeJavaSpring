-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021 年 10 月 19 日 04:36
-- 伺服器版本： 8.0.18
-- PHP 版本： 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `test`
--

DELIMITER $$
--
-- 程序
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ROWPERROW` ()  BEGIN
    DECLARE n INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    SET i=0;
    SET n = 235364;
    WHILE i<n DO
UPDATE `login_log` SET country = (SELECT country_name FROM `ip2location_db1`,(SELECT REPLACE(ip,'.','')AS ip FROM `login_log` WHERE id = i)AS T WHERE ip_from <= T.ip  AND ip_to >= T.ip) WHERE id = i;
SET i = i + 1;
END WHILE;
End$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 資料表結構 `member_account`
--

CREATE TABLE `member_account` (
                                  `ID` int(11) NOT NULL COMMENT '會員流水號',
                                  `ACCOUNT` varchar(100) NOT NULL COMMENT '帳號',
                                  `PASSWORD` varchar(60) NOT NULL COMMENT '密碼',
                                  `SALT` varchar(32) NOT NULL COMMENT '鹽值',
                                  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
                                  `PHONE` char(10) NOT NULL COMMENT '手機號碼',
                                  `CREATE_TIME` datetime NOT NULL COMMENT '建立時間',
                                  `UPDATE_TIME` datetime NOT NULL COMMENT '修改時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `member_account`
--
ALTER TABLE `member_account`
    ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ACCOUNT` (`ACCOUNT`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member_account`
--
ALTER TABLE `member_account`
    MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '會員流水號';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
