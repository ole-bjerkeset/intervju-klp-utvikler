# intervju-klp-utvikler

Teknisk oppgave for andregangsintervju KLP utvikler

## How to run the application

Clone the project, build it and run "TekniskOppgaveApplication"

## api spesification (can be tested through postman etc.)
POST:
http://localhost:8085/api/user: used to create a new user based on request

requestBody example:
{
"email": "test@test.test",
"type": "ADMIN" (Only "USER" or "ADMIN" are valid)
}

GET:
http://localhost:8085/api/county/{countyNumber}: insert valid county number to fetch the name of the county

http://localhost:8085/api/user/{id}: used to fetch a user by id

http://localhost:8085/api/user?email={email}&type={userType}: used to fetch a list of users matching the given criterias (both optinal, if left empty returns all users)