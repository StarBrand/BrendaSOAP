require(taxize)
require(DBI)
source("password.R")

conn <- dbConnect(RPostgres::Postgres(),
                  dbname = 'brenda_local',
                  host = 'pesb2.cl',
                  port = 5432,
                  user = 'pesb2',
                  password = dbPassword)
res <- dbSendQuery(conn,
                   "SELECT species FROM genus WHERE genus IS NULL;")

species <- dbFetch(res)
remove(res)

# Decide what to do
dbDisconnect(conn)
