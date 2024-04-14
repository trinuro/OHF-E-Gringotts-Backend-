# User endpoint
1. All user API endpoints begins `http://localhost:8080/api/v1/user`
## Get all users
1. Make a GET request to `../all`
Example output:
```http
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 12 Apr 2024 10:32:41 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "name": "GoblinSecretary",
    "email": "love@gmail.com",
    "password": "enable",
    "status": "goblin"
  },
  {
    "id": 2,
    "name": "test2",
    "email": "test2@gmail.com",
    "password": "12345",
    "status": "silverSnitch"
  }
]
```
## Get one user based on email
1. Make a GET request to `../getUser?property=email&value=<nameToFind>`
Example:
```http
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 12 Apr 2024 10:32:04 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 2,
  "name": "test2",
  "email": "test2@gmail.com",
  "password": "",
  "status": "silverSnitch"
}
```
## Create new user
1. Make a POST request with the following body:
```http
POST http://localhost:8080/api/v1/user/create?type=3  
Content-Type: application/json  
  
{  
  "name": "test3",  
  "email": "test3@gmail.com",  
  "password": "12345"
}
```
- Note that email address must be unique