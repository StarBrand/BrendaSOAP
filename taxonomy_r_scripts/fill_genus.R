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
                   "SELECT species FROM genus;")
species <- dbFetch(res)
remove(res)

for (sci in species$species){
  if (!is.na(strsplit(sci, " ")[[1]][2]) & strsplit(sci, " ")[[1]][2] == "sp.")
    genus <- strsplit(sci, " ")[[1]][1]
  else
    genus <- tax_name(sci=sci, get="genus", db="ncbi", ask=FALSE)$genus
  if (!is.na(genus)){
    try({
      dbSendQuery(conn, sprintf(
        "INSERT INTO family(genus) VALUES ('%s');", genus
      ))
    })
    try({
      dbSendQuery(conn, sprintf(
        "UPDATE genus SET genus = '%s' WHERE species = '%s';", genus, sci
      ))
    })
  }
}

dbDisconnect(conn)
