use kobis_db;

create table wifi
(X_SWIFI_MGR_NO varchar(100),
 X_SWIFI_WRDOFC varchar(100) ,
 X_SWIFI_MAIN_NM varchar(100),
 X_SWIFI_ADRES1 varchar(100) ,
 X_SWIFI_ADRES2 varchar(100) ,
 X_SWIFI_INSTL_FLOOR varchar(100),
 X_SWIFI_INSTL_TY varchar(100) ,
 X_SWIFI_INSTL_MBY varchar(100),
 X_SWIFI_SVC_SE varchar(100) ,
 X_SWIFI_CMCWR varchar(100),
 X_SWIFI_CNSTC_YEAR varchar(100) ,
 X_SWIFI_INOUT_DOOR varchar(100),
 X_SWIFI_REMARS3 varchar(100) ,
 LAT varchar(100),
 LNT varchar(100) ,
 WORK_DTTM varchar(100)
);

select count(*) from wifi;
select * from wifi;

create table history
(id bigint primary key auto_increment,
 lat Double,
 lnt Double,
 date timestamp
);