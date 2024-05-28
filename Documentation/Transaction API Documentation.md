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
  "dateTime": "2024-05-15 14:30:00",
  "source_account_id_long": 1,  
  "destination_account_id_long": 7,  
  "category" : "Entertainment",  
  "description": "I love to watch movies!",
  "sourceCurrency": "knut",
  "destinationCurrency": "galleon"
}
```
- dateTime format is in yyyy-MM-dd HH:mm:ss
- Source currency can only be "knut", "galleon" or "sickle"

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

# To get transactions by category, source_account or destination_account
1. Make a GET request to `../getTransaction?property=category&value=food` to get all transactions that fits a category
```json
[
  {
    "id": 1,
    "amount": 200.0,
    "dateTime": "2024-04-01 14:30:00",
    "sourceAccount": null,
    "source_account_id_long": 6,
    "destinationAccount": null,
    "destination_account_id_long": 7,
    "category": "Food",
    "description": "I love to eat. Yum"
  }
]
```
2. Make a GET request to `../getTransaction?property=source_account&value=6` to get all transactions that fits a source_account id (in this case, 6)
```json
[
  {
    "id": 1,
    "amount": 200.0,
    "dateTime": "2024-04-01 14:30:00",
    "sourceAccount": null,
    "source_account_id_long": 6,
    "destinationAccount": null,
    "destination_account_id_long": 7,
    "category": "Food",
    "description": "I love to eat. Yum"
  }
]
```
3.  Make a GET request to `../getTransaction?property=destination_account&value=7` to get all transactions that fits a destination_account id (in this case, 7)
```json
[
  {
    "id": 1,
    "amount": 200.0,
    "dateTime": "2024-04-01 14:30:00",
    "sourceAccount": null,
    "source_account_id_long": 6,
    "destinationAccount": null,
    "destination_account_id_long": 7,
    "category": "Food",
    "description": "I love to eat. Yum"
  },
	...
  {
    "id": 6,
    "amount": 200.0,
    "dateTime": "2024-04-01 14:30:00",
    "sourceAccount": null,
    "source_account_id_long": 1,
    "destinationAccount": null,
    "destination_account_id_long": 7,
    "category": "Entertainment",
    "description": "I love to watch movies!"
  }
]
```

# To get transactions within a certain period
1. Make a GET request to `../getTransactionByDateTime?start=2024-03-1 14:30:00&end=2024-04-30 14:30:00`
```json
[
  {
    "id": 6,
    "amount": 200.0,
    "dateTime": "2024-04-01 14:30:00",
    "sourceAccount": null,
    "source_account_id_long": 1,
    "destinationAccount": null,
    "destination_account_id_long": 7,
    "category": "Entertainment",
    "description": "I love to watch movies!"
  }
]
```

# To get transactions before a certain time
Make a GET request to `../getTransactionByDayBeforeDate?endDateTime=2024-04-30 14:30:00&days=31`
```json
[
  {
    "id": 6,
    "amount": 200.0,
    "dateTime": "2024-04-01 14:30:00",
    "sourceAccount": null,
    "source_account_id_long": 1,
    "destinationAccount": null,
    "destination_account_id_long": 7,
    "category": "Entertainment",
    "description": "I love to watch movies!"
  }
]
```