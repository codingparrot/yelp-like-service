# Yelp-Like-Service

## Required
1. Git
2. JDK 11 +
3. Gradle 4.10.3



## How to run and make changes
1. Clone the repository `git clone `
2. cd yelp-like-service
3. Modify the userName and password in application.properties.
4. gradle bootrun
5. Running tests


## How to verify the setup is working
1. Once gradle boot run is successful
   You should see the message: `Started YelpLikeServiceMain ...`
2. Visit the url: http://localhost:18090/ and you should yelp-like-service home page.
3. Visit the url: http://localhost:18090/restaurants should show a json with two restaurants.

## How to access database
1. This service uses in memory database H2. You can visit http://localhost:18090/h2 to access the database. No password is required
