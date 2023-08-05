# Java

[Working with multiple Databases](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#r2dbc.multiple-databases)

```shell
docker run --name postgres-write -e POSTGRES_PASSWORD=pass -d -p 5433:5432 postgres
docker run --name postgres-read -e POSTGRES_PASSWORD=pass -d -p 5434:5432 postgres
```

```sql
CREATE TABLE account
(
    id                          SERIAL,
    name                        VARCHAR(200) NOT NULL,
    status                      VARCHAR(200) NOT NULL,
    CONSTRAINT account_pk PRIMARY KEY (id)
);

INSERT INTO public.account
    (id, name, status)
VALUES(4000, 'AC12345', 'ENABLED');
```

```shell
curl http://localhost:8080/api/usecase/account
curl http://localhost:8080/api/usecase/other
```