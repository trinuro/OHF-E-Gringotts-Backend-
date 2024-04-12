1. Install MySQL if you haven't
2. Navigate to `MySQL 8.0 Command Line Client` (You can use the search button for this)
3. Log in using your password.
4. Create a new database called `egringotts`
```MYSQL
CREATE DATABASE egringotts
```
4. Navigate to `src/main/resources/Secrets`. 
5. Create a file called `env.properties`
6. Inside `env.properties`, add
```env.properties
DB_USER=XXX
DB_DATABASE_URL=jdbc:mysql://localhost:3306/egringotts
DB_PASSWORD=XXX
```