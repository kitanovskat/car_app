# car_app

Setup database locally Create and run a docker image with the following command:
docker run --name dev -e POSTGRES_PASSWORD=secretpassword -p 5432:5432 -d postgres This command will
create docker contained named "postgres" based on postgres image and run a PostgreSQL accessible on
the 5432 port with the specified password.

After completing it, the application should be successfully connected with the database.
