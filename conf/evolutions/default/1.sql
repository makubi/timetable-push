# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "timetableevents" ("_id" UUID NOT NULL PRIMARY KEY,"userId" UUID NOT NULL,"configId" UUID NOT NULL,"datetime" TIMESTAMP NOT NULL,"timetableEvent" VARCHAR(200000) NOT NULL);
create table "untisconfig" ("_id" UUID NOT NULL PRIMARY KEY,"uid" UUID NOT NULL,"url" VARCHAR(254) NOT NULL,"school" VARCHAR(254) NOT NULL,"elementType" INTEGER NOT NULL,"elementId" INTEGER NOT NULL,"untisuser" VARCHAR(254) NOT NULL,"untispassword" VARCHAR(254) NOT NULL);
create table "user" ("email" VARCHAR(254) NOT NULL,"password" VARCHAR(254) NOT NULL,"_id" UUID NOT NULL PRIMARY KEY,"dateTime" TIMESTAMP NOT NULL,"activatedByUser" BOOLEAN NOT NULL,"activatedByAdmin" BOOLEAN NOT NULL);
create unique index "email_index" on "user" ("email");
alter table "timetableevents" add constraint "fk_config" foreign key("userId") references "user"("_id") on update NO ACTION on delete NO ACTION;
alter table "timetableevents" add constraint "fk_user" foreign key("configId") references "untisconfig"("_id") on update NO ACTION on delete NO ACTION;
alter table "untisconfig" add constraint "userIdFk" foreign key("uid") references "user"("_id") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "untisconfig" drop constraint "userIdFk";
alter table "timetableevents" drop constraint "fk_config";
alter table "timetableevents" drop constraint "fk_user";
drop table "user";
drop table "untisconfig";
drop table "timetableevents";

