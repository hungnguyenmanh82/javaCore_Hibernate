drop table if exists mkyongdb.stock;
drop table if exists mkyongdb.stock_daily_record;
create table mkyongdb.stock (STOCK_ID integer not null auto_increment, STOCK_CODE varchar(10) not null, STOCK_NAME varchar(20) not null, primary key (STOCK_ID)) engine=MyISAM;
create table mkyongdb.stock_daily_record (RECORD_ID integer not null auto_increment, DATE date not null, PRICE_CHANGE float, PRICE_CLOSE float, PRICE_OPEN float, VOLUME bigint, STOCK_ID integer not null, primary key (RECORD_ID)) engine=MyISAM;
alter table mkyongdb.stock add constraint UK_j1y49jqhck84j782fojvrk55w unique (STOCK_CODE);
alter table mkyongdb.stock add constraint UK_id2u76pv39wuq1txaomuw9ita unique (STOCK_NAME);
alter table mkyongdb.stock_daily_record add constraint UK_k8p5x4txjkimsi98ssumd7y8a unique (DATE);
alter table mkyongdb.stock_daily_record add constraint FKha4n7aajs37dplx0c4dyyjk52 foreign key (STOCK_ID) references mkyongdb.stock (STOCK_ID);
