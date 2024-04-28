### Database Configuration

Ensure you have a MySQL database set up with a user having appropriate privileges.

### Environment Variables

Set the following environment variables in your IntelliJ configurations for `Hogwarts5Application`:

- `JDBC_DATABASE_URL`: URL of your MySQL database
- `JDBC_USERNAME`: Username for your MySQL database
- `JDBC_PASSWORD`: Password for your MySQL database

### Test Prefects

- POST: Set prefects at `prefects/set` with studentId as body
- GET: House prefects at `prefects/house/{houseName}`
- DELETE: Remove a prefect at `prefects/remove/{studentId}`

