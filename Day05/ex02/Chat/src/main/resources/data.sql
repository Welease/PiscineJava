insert into users (login, password) values ('first', '1111');
insert into users (login, password) values ('second', '2222');
insert into users (login, password) values ('third', '3333');
insert into users (login, password) values ('fourth', '4444');
insert into users (login, password) values ('fifth', '5555');

insert into chatrooms (name_, ownerID) values ('chatroom1', 1);
insert into chatrooms (name_, ownerID) values ('chatroom2', 1);
insert into chatrooms (name_, ownerID) values ('chatroom3', 2);
insert into chatrooms (name_, ownerID) values ('chatroom4', 3);
insert into chatrooms (name_, ownerID) values ('chatroom5', 4);

insert into messages (authorid, chatroomid, text, datetime) values (1, 2, 'hello', current_timestamp);
insert into messages (authorid, chatroomid, text, datetime) values (1, 2, 'hi', current_timestamp);
insert into messages (authorid, chatroomid, text, datetime) values (2, 3, 'aboba', current_timestamp);
insert into messages (authorid, chatroomid, text, datetime) values (4, 1, 'kek', current_timestamp);
insert into messages (authorid, chatroomid, text, datetime) values (2, 5, 'lol', current_timestamp);