# Synpulse

## start service
```
mvn spring-boot:run
```

## API
create user 
```
curl --location --request POST 'http://localhost:8080/synpulse/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "wade",
    "lastName": "chen"
}'
```

create user 
```
curl --location --request POST 'http://localhost:8080/synpulse/userAccount' \
--header 'Content-Type: application/json' \
--data-raw '{
    "countryCode": "TW",
    "userId": "d021c03d-3ce6-45fb-9be9-d33370f1796c"
}'
```

create user 
```
curl --location --request POST 'http://localhost:8080/synpulse/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 10,
    "payeeIBAN": "RO63 SNGU GNUQ LTII USJU 5GYT",
    "payerIBAN": "LTII 5GYT USJU RO63 SNGU GNUQ"
}'
```

## health check
```
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/health/liveness
curl http://localhost:8080/actuator/health/readiness
```

## swagger
```
http://localhost:8080/swagger-ui/index.html
```
