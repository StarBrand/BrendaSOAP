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
                   "SELECT genus FROM family WHERE family IS NULL;")
# Because some family are saved by hand
genuses <- dbFetch(res)
remove(res)

for (genus in genuses$genus){
  found <- FALSE
  while(!found){
    tryCatch({
      family <- tax_name(sci=genus, get="family", db="ncbi", ask=FALSE)$family
      found <- TRUE
    }, error = function(cond){
      found <- FALSE
    })
  }
  if (!is.na(family)){
    try({
      dbSendQuery(conn, sprintf(
        "INSERT INTO taxonomic_order(family) VALUES ('%s');", family
      ))
    })
    try({
      dbSendQuery(conn, sprintf(
        "UPDATE family SET family = '%s' WHERE genus = '%s';", family, genus
      ))
    })
  }
}

res <- dbSendQuery(conn,
                   "SELECT family FROM taxonomic_order;")
families <- dbFetch(res)
remove(res)

for (family in families$family){
  found <- FALSE
  while(!found){
    tryCatch({
      tax <- tax_name(sci=family, get=c("order", "class", "phylum", "superkingdom"),
                      db="ncbi", ask=FALSE)
      found <- TRUE
    }, error = function(cond){
      found <- FALSE
    })
  }
  superkingdom <- tax$superkingdom
  phylum <- tax$phylum
  if (!is.na(superkingdom)){
    if(!is.na(phylum)){
      try({dbSendQuery(conn, sprintf(
        "INSERT INTO superkingdom VALUES ('%s', '%s');", phylum, superkingdom))})
      class <- tax$class
      if(!is.na(class)){
        try({dbSendQuery(conn, sprintf(
          "INSERT INTO phylum VALUES ('%s', '%s');", class, phylum))})
        order <- tax$order
        if(!is.na(order)){
          try({dbSendQuery(conn, sprintf(
            "INSERT INTO class VALUES ('%s', '%s');", order, class))})
          try({dbSendQuery(conn, sprintf(
            "UPDATE taxonomic_order SET \"order\" = '%s' WHERE family = '%s';", order, family
            ))})
        }
      }
    }
  }
}

qdbDisconnect(conn)
