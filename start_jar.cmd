@REM set DB_URL=jdbc:mysql://localhost:3306/app_db
@REM set DB_USER=user
@REM set DB_PASS=pass
@REM -P:jdbc.url=..., -P:jdbc.user=..., -P:jdbc.password=...
@REM docker-compose exec mysql mysql -u user --password=pass --database=app_db --execute="source /docker-entrypoint-initdb.d/schema_2.sql;exit;"
java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:
jdbc.user=app -P:jdbc.password=pass

