# Account API Documentation
1. All Account endpoints starts with `http://localhost:8080/api/v1/account`
## Get all accounts in the database
1. Make a GET request to `../all`
Output:
```http
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 13 Apr 2024 08:21:25 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "total_balance": 1000.5,
    "myUser": null,
    "knut_balance": 500.0,
    "sickle_balance": 0.7,
    "galleon_balance": 100.0,
    "user_id_long": null
  },
	.... <cut output for simplicity>
  {
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
  {
    "id": 8,
    "total_balance": 222.5,
    "myUser": {
      "id": 1,
      "name": "GoblinSecretary",
      "email": "love@gmail.com",
      "password": "enable",
      "status": "goblin"
    },
    "knut_balance": 2.0,
    "sickle_balance": 2.2,
    "galleon_balance": 22.0,
    "user_id_long": 1
  }
]
```

2. Get Account By User Id
```
http://localhost:8080/api/v1/account/getByUserId?id=2
{
  "id": 2,
  "total_balance": 222.5,
  "myUser": {
    "id": 2,
    "name": "Wen Yang",
    "email": "wenyang@test.com",
    "password": "8cb2237d0679ca88db6464eac60da96345513964",
    "status": "silverSnitch",
    "phoneNumber": "0123401648"
  },
  "knut_balance": 58,
  "sickle_balance": 71,
  "galleon_balance": 16,
  "user_id_long": 2
}
```

## Create new account
1. Create a POST request to this endpoint
```http
POST http://localhost:8080/api/v1/account/create  
Content-Type: application/json  
  
{  
  "total_balance": 222.50,  
  "user_id_long": 1,  
  "knut_balance": 2,  
  "sickle_balance": 2.2,  
  "galleon_balance": 22
}
```
Output:
```http
HTTP/1.1 200 
Content-Length: 0
Date: Sat, 13 Apr 2024 08:24:21 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 70ms (70 ms); Content length: 0 bytes (0 B)
```
- `Response code: 200` means all OK.