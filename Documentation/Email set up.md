1. Create Secrets class in `utilities` directory
```java
public class Secrets {
    public static String getSenderEmail(){
        return "<email-address>";
    }

    public static String getJSON(){
        return "Secrets\\nothing-to-see.json";
    }

    public static String getClientID(){
        return "client-id";
    }

    public static String getClientSecret(){
        return "client-secret";
    }

}
```
2. Create a `nothing-to-see.json` in the `Secrets` directory in `src/resources`
```json
{
  "type": "authorized_user",
  "client_id":  "client-id",
  "client_secret":  "client-secret",
  "refresh_token":  ""
}
```
3. Open a new account in google
4. Make a GET request to `http://localhost:8080/api/v1/user/refreshToken`. If a chrome window does not automatically open, copy and paste the link in the terminal into a chrome window
5. Allow the app to send email on behalf of your account
6. Paste the refresh token in `nothing-to-see.json`
7. Test whether the Gmail API works by sending a POST request to `http://localhost:8080/api/v1/user/create?type=2`
```http
POST http://localhost:8080/api/v1/user/create?type=2
Content-Type: application/json

{
  "name": "superman",
  "email": "your_email@gmail.com",
  "password": "12345"
}
```
