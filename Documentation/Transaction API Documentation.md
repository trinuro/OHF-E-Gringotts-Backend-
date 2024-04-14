1. All Transaction API endpoints starts with `http://localhost:8080/api/v1/transaction`
# To get all transactions that were recorded
1. Make a GET request to `../all`
Output:
```http
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 14 Apr 2024 10:23:58 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "amount": 200.0,
    "dateTime": null,
    "sourceAccount": {
      "id": 6,
      "total_balance": 1000.5,
      "myUser": {
        "id": 1,
        "name": "GoblinSecretary",
        "email": "love@gmail.com",
        "password": "enable",
        "status": "goblin"
      },
      "knut_balance": 500.0,
      "sickle_balance": 0.7,
      "galleon_balance": 100.0,
      "user_id_long": 1
    },
    "source_account_id_long": 6,
    "destinationAccount": {
      "id": 7,
      "total_balance": 1000.5,
      "myUser": {
        "id": 1,
        "name": "GoblinSecretary",
        "email": "love@gmail.com",
        "password": "enable",
        "status": "goblin"
      },
      "knut_balance": 500.0,
      "sickle_balance": 0.7,
      "galleon_balance": 100.0,
      "user_id_long": 1
    },
    "destination_account_id_long": 7,
    "category": "Food",
    "description": "I love to eat. Yum"
  },
  {
    "id": 2,
    "amount": 200.0,
    "dateTime": null,
    "sourceAccount": {
      "id": 1,
      "total_balance": 1000.5,
      "myUser": null,
      "knut_balance": 500.0,
      "sickle_balance": 0.7,
      "galleon_balance": 100.0,
      "user_id_long": null
    },
    "source_account_id_long": 1,
    "destinationAccount": {
      "id": 7,
      "total_balance": 1000.5,
      "myUser": {
        "id": 1,
        "name": "GoblinSecretary",
        "email": "love@gmail.com",
        "password": "enable",
        "status": "goblin"
      },
      "knut_balance": 500.0,
      "sickle_balance": 0.7,
      "galleon_balance": 100.0,
      "user_id_long": 1
    },
    "destination_account_id_long": 7,
    "category": "Entertainment",
    "description": "I love to watch movies!"
  }
]
```
# To create a new transaction
1. Make a POST request to `../create`
```http
POST http://localhost:8080/api/v1/transaction/create  
Content-Type: application/json  
  
{  
  "amount": 200,  
  "date_time_string": "2024-04-14 14:30:00",  
  "source_account_id_long": 1,  
  "destination_account_id_long": 7,  
  "category" : "Entertainment",  
  "description": "I love to watch movies!"
}
```
Output:
```http
HTTP/1.1 200 
Content-Length: 0
Date: Sun, 14 Apr 2024 10:21:51 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 285ms (285 ms); Content length: 0 bytes (0 B)
```
