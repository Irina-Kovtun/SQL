set DB_URL=jdbc:mysql://localhost:3306/app_db
set DB_USER=user
set DB_PASS=pass
docker-compose exec mysql mysql -u user --password=pass --database=app_db --execute="source /docker-entrypoint-initdb.d/schema_2.sql;exit;"
java -jar app-deadline.jar
