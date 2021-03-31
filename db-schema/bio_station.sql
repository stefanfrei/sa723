CREATE TABLE "bio_station" (
	"id" CHAR(36) NOT NULL,
	"id_employer" CHAR(36) NOT NULL,
	"station_type" VARCHAR(30) NOT NULL,
	"date_from" DATE NOT NULL,
	"date_until" DATE NOT NULL,
	PRIMARY KEY ("id")
)
;
