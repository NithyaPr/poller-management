CREATE TABLE IF NOT EXISTS SERVICES
                              (	"ID" NUMBER NOT NULL AUTO_INCREMENT,
                           	"URL" VARCHAR2(500 BYTE) ,
                           	"CREATED_DATE" DATE ,
                           	"CREATED_BY" VARCHAR2(50 BYTE) ,
                           	"STATUS" VARCHAR2(10 BYTE) ,
                           	"MODIFIED_DATE" DATE,
                           	"LAST_ACTIVE_DATE" DATE,
                           	"STATUS_DATE" DATE
                              );