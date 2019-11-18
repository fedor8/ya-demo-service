## DB - H2 
[http://localhost:8002/demo-service/h2](http://localhost:8002/demo-service/h2)

## login page
[http://localhost:8002/demo-service/login?login=USER&password=USER](http://localhost:8002/demo-service/login?login=USER&password=USER)

## create customer
 `curl -d "{ \"name\": \"Ivanov\", \"salary\": {   \"amount\": \"10000.90\"   } }" -H "Content-Type:application/json" -X POST http://localhost:8002/demo-service/api/customer/create`
 
## list all customers
`curl http://localhost:8002/demo-service/api/customer/`

## update salary for customer with id = 1 by anonymous user
`curl -X POST http://localhost:8002/demo-service/api/customer/1/update-salary?salary=100.5`

## update salary for customer with id = 1 by USER:USER
`curl -X POST http://localhost:8002/demo-service/api/customer/1/update-salary?salary=100.5 -u USER:USER`

## delete all customers by USER:USER
`curl -X DELETE http://localhost:8002/demo-service/api/customer/ -u USER:USER`