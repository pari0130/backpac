
-- 사용자 정보 비밀번호 Wkdl!@1600
INSERT INTO dev.user
(user_no, user_email, user_gender, user_name, user_nic, user_phone, user_pw)
VALUES(1, 'pari0130@gmail.com', 'm', '조동휘', 'pari', '0100000000', '{bcrypt}$2a$10$t4LHqN80UrKOESkFg1prieWLGDUvbTfoxsfYi2knufVgY1aJEHnum');
INSERT INTO dev.user
(user_no, user_email, user_gender, user_name, user_nic, user_phone, user_pw)
VALUES(2, 'pari01301@gmail.com', 'm', '조동휘', 'pari', '0100000000', '{bcrypt}$2a$10$hqO9rDJja5t63jAFWFabxeQquQyQU8wEQsGMv.TXSDRB.pYfIttR6');
INSERT INTO dev.user
(user_no, user_email, user_gender, user_name, user_nic, user_phone, user_pw)
VALUES(3, 'pari01302@gmail.com', 'm', '조동휘', 'pari', '0100000000', '{bcrypt}$2a$10$1bHM359swgvwc.9W4vlQteWSZ3fWyJWa3Alrki.bTzMqlQyZnNXxO');
INSERT INTO dev.user
(user_no, user_email, user_gender, user_name, user_nic, user_phone, user_pw)
VALUES(4, 'pari01303@gmail.com', 'm', '조동휘', 'pari', '0100000000', '{bcrypt}$2a$10$UKN9YShi3LSg3rksXXeyEe6nKP0uZGxkhUJ5hf9cHGBi4eCkpP6Vy');
INSERT INTO dev.user
(user_no, user_email, user_gender, user_name, user_nic, user_phone, user_pw)
VALUES(5, 'pari01304@gmail.com', 'm', '조동휘', 'pari', '0100000000', '{bcrypt}$2a$10$pUXJFumK6x7SHWXHo0t.eejwbWFA9Fj7j1lX2lPkqAYLSusVuDS3W');
INSERT INTO dev.user
(user_no, user_email, user_gender, user_name, user_nic, user_phone, user_pw)
VALUES(6, 'pari01305@gmail.com', 'm', '조동휘', 'pari', '0100000000', '{bcrypt}$2a$10$JU2TrQUFrsRSoWIx5j5wD.uCaQlQNhlYsDi8MHs4ZgatrFuOwxTh2');

-- 사용자 권한
INSERT INTO dev.user_roles(user_user_no, roles)VALUES(1, 'ROLE_USER');
INSERT INTO dev.user_roles(user_user_no, roles)VALUES(2, 'ROLE_USER');
INSERT INTO dev.user_roles(user_user_no, roles)VALUES(3, 'ROLE_USER');
INSERT INTO dev.user_roles(user_user_no, roles)VALUES(4, 'ROLE_USER');
INSERT INTO dev.user_roles(user_user_no, roles)VALUES(5, 'ROLE_USER');
INSERT INTO dev.user_roles(user_user_no, roles)VALUES(6, 'ROLE_USER');

-- 제품 정보
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(1, '생수', '석수', '2021-04-03 04:56:32.348000000', '2021-04-03 04:56:32.348000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(2, '음료', '코카콜라', '2021-04-03 05:01:23.642000000', '2021-04-03 05:01:23.642000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(3, '음료', '사이다', '2021-04-03 05:03:03.723000000', '2021-04-03 05:03:03.723000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(4, '음료', '환타', '2021-04-03 05:03:08.072000000', '2021-04-03 05:03:08.072000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(5, '음료', '매실', '2021-04-03 05:03:14.008000000', '2021-04-03 05:03:14.008000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(6, '음료', '미닛메이드 오렌지', '2021-04-03 05:03:20.524000000', '2021-04-03 05:03:20.524000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(7, '음료', '미닛메이드 포도', '2021-04-03 05:03:23.251000000', '2021-04-03 05:03:23.251000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(8, '과자', '포카칩', '2021-04-03 11:58:07.853000000', '2021-04-03 11:58:07.853000000');
INSERT INTO dev.product
(product_id, product_desc, product_name, create_dt, update_dt)
VALUES(9, '과자', '빼빼로😆🥵', '2021-04-03 12:02:02.987000000', '2021-04-03 12:02:02.987000000');



-- 제품 주문 정보
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(30, '2021-03-03 09:51:30.632000000', '2021-03-03 09:51:30.632000000', '351FM8HOXXS5', 1, 1);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(31, '2021-03-04 09:51:31.899000000', '2021-03-04 09:51:31.899000000', 'T19ZIIYFATCO', 2, 1);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(32, '2021-03-05 09:51:33.054000000', '2021-03-05 09:51:33.054000000', 'E1QEKGSHMXVN', 3, 1);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(33, '2021-04-03 09:51:34.085000000', '2021-04-03 09:51:34.085000000', 'E7JINN6M7O85', 4, 1);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(34, '2021-04-03 09:51:35.129000000', '2021-04-03 09:51:35.129000000', '1V4MC8DYKNHN', 5, 1);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(35, '2021-04-03 09:51:36.657000000', '2021-04-03 09:51:36.657000000', '0ZUOYOSN53JC', 6, 1);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(36, '2021-04-03 09:51:38.542000000', '2021-04-03 09:51:38.542000000', 'JC3VYU8LZ8IG', 1, 2);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(37, '2021-04-03 09:51:39.593000000', '2021-04-03 09:51:39.593000000', '7H4LTJYQY0XU', 2, 2);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(38, '2021-04-03 09:51:40.640000000', '2021-04-03 09:51:40.640000000', 'FPN5YCSXCWIP', 3, 2);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(39, '2021-04-03 09:51:41.772000000', '2021-04-03 09:51:41.772000000', 'NV5C4WZ0NW0Z', 4, 2);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(40, '2021-04-03 09:51:42.812000000', '2021-04-03 09:51:42.812000000', 'TN3943RVDEY6', 5, 2);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(41, '2021-04-03 09:51:43.811000000', '2021-04-03 09:51:43.811000000', 'AWEB3F2FF9JZ', 6, 2);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(42, '2021-04-03 09:51:46.379000000', '2021-04-03 09:51:46.379000000', 'LKTVTDUJK1X6', 1, 3);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(43, '2021-04-03 09:51:47.448000000', '2021-04-03 09:51:47.448000000', '29DOM990FG54', 2, 3);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(44, '2021-04-01 09:51:48.557000000', '2021-04-01 09:51:48.557000000', 'K26QPTBTZ11C', 3, 3);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(45, '2021-04-03 09:51:49.574000000', '2021-04-03 09:51:49.574000000', 'NXYUZ99LMCJ7', 4, 3);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(46, '2021-04-03 09:51:50.594000000', '2021-04-03 09:51:50.594000000', 'W72XVJSCD9X0', 5, 3);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(47, '2021-04-02 09:51:51.635000000', '2021-04-02 09:51:51.635000000', 'LVN9WOM3D1BE', 6, 3);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(48, '2021-04-03 09:51:53.643000000', '2021-04-03 09:51:53.643000000', 'LTCDTXTZE74R', 1, 4);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(49, '2021-04-03 09:52:40.181000000', '2021-04-03 09:52:40.181000000', 'TPJX7T2MD38N', 2, 4);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(50, '2021-01-01 09:52:42.665000000', '2021-01-01 09:52:42.665000000', '4Q0B6KF843PW', 3, 4);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(51, '2021-04-03 09:52:43.747000000', '2021-04-03 09:52:43.747000000', 'SXDKC7JA4WWS', 4, 4);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(52, '2021-04-03 09:52:45.175000000', '2021-04-03 09:52:45.175000000', '6F8POTGXATLK', 5, 4);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(53, '2021-01-05 09:52:49.481000000', '2021-01-05 09:52:49.481000000', 'ZEF6KJUZ7UK1', 1, 5);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(54, '2021-04-03 09:52:50.878000000', '2021-04-03 09:52:50.878000000', 'QG8JWIMJITHT', 2, 5);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(55, '2021-02-07 09:52:51.783000000', '2021-02-07 09:52:51.783000000', 'R5Z6BO5HP4MG', 3, 5);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(56, '2021-04-03 09:52:52.733000000', '2021-04-03 09:52:52.733000000', '82W6DRUJIF8K', 4, 5);
INSERT INTO dev.user_order
(order_no, create_dt, update_dt, order_id, product_id, user_no)
VALUES(57, '2021-04-03 09:52:53.876000000', '2021-04-03 09:52:53.876000000', '3Z3MNM1IFGMA', 5, 5);

