CREATE TABLE "article" (
    "id" INTEGER NOT NULL DEFAULT 'nextval(''article_id_seq''::regclass)',
    "id_author" INTEGER NOT NULL,
    "lang" CHAR(5) NOT NULL,
    "created" TIMESTAMPTZ NOT NULL,
    "published" TIMESTAMPTZ NOT NULL,
    "title-short" CHAR(30) NOT NULL,
    "title" CHAR(50) NOT NULL,
    "teaser" CHAR(300) NOT NULL,
    "text" TEXT NOT NULL
)
;
