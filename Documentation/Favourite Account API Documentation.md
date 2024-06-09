# List of favourite accounts
Endpoint starts with `http://localhost:8080/api/v1/favourites`
## Get all favourites by User Id
1. GET request to `http://localhost:8080/api/v1/favourites/all?id=2`
```aidl
[
  {
    "id": {
      "userId": 2,
      "accountId": 1
    },
    "user": {
      "id": 2,
      "name": "Wen Yang",
      "email": "wenyang@test.com",
      "password": "8cb2237d0679ca88db6464eac60da96345513964",
      "status": "silverSnitch",
      "phoneNumber": "0123401648"
    },
    "account": {
      "id": 1,
      "total_balance": 222.5,
      "myUser": {
        "id": 1,
        "name": "GoblinSecretary",
        "email": "love@gmail.com",
        "password": "9905c33933602ae804e94f7731ecb5faaf4d6770",
        "status": "goblin",
        "phoneNumber": "0123456789"
      },
      "knut_balance": 3,
      "sickle_balance": 5.4,
      "galleon_balance": 23,
      "user_id_long": 1
    }
  },
  {
    "id": {
      "userId": 2,
      "accountId": 2
    },
    "user": {
      "id": 2,
      "name": "Wen Yang",
      "email": "wenyang@test.com",
      "password": "8cb2237d0679ca88db6464eac60da96345513964",
      "status": "silverSnitch",
      "phoneNumber": "0123401648"
    },
    "account": {
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
  },
  {
    "id": {
      "userId": 2,
      "accountId": 3
    },
    "user": {
      "id": 2,
      "name": "Wen Yang",
      "email": "wenyang@test.com",
      "password": "8cb2237d0679ca88db6464eac60da96345513964",
      "status": "silverSnitch",
      "phoneNumber": "0123401648"
    },
    "account": {
      "id": 3,
      "total_balance": 222.5,
      "myUser": {
        "id": 3,
        "name": "test3",
        "email": "test3@gmail.com",
        "password": "12345",
        "status": "silverSnitch",
        "phoneNumber": "0123599375"
      },
      "knut_balance": 2,
      "sickle_balance": 3.2,
      "galleon_balance": 22,
      "user_id_long": 3
    }
  },
]
```
## Add new favourites
1. POST request to `http://localhost:8080/api/v1/favourites/create` with the following JSON body
```aidl
POST http://localhost:8080/api/v1/favourites/create
Content-Type: application/json 
{
    "userId": 2,
    "accountId": 4
}
```