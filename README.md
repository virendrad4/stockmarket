# StockMarket Demo Project

## What is this?

This is a tiny scale model of Stock Market. It contains a Java backend with H2 memory database. The server handles requests from clients to create, update, and view Stocks.

## Start Application
##Minimum Requirement
- `java 11`
- `npm 7.7.6`

###To run Backend server
- `./gradlew bootrun` . It will run backend server at http://localhost:8080

###To run Client
- `cd client`
- `npm build`
- `npm start`. It will run client at http://localhost:3000

## Codebase Layout

###Backend Code

Backend code lives in `src/main/java/com/stockproject/stockmarket` and tests live in `src/test/java/com/stockproject/stockmarket`.

There are a few key bits of Backend code that might be worth looking at.

- `StockmarketApplication` is the entry-point to the app where java "main" function lies
- `controller/StocksController` is the controller of the app. It handles rest based api's. We support POST, PUT, DELETE api's to create, edit and delete the stocks from database.
- `service/StockService` is the layer between controller and database which handles inbetween data modification and validation.
- `repository/StockRepository` is the interface and talks to database based on defined queries and also extends CrudRepository.
- `model/StockModel` is the model which we store to the DB.

Backend runs at `http://localhost:8080` address.
### Data Storage

Data is stored in an in-memory H2 database. The Stock schema is in `src/main/resources/schema.sql`.

- Stock table have id, stock_name, current_price, last_update columns which 'id' as primary key.
- We are not persisting data, means whenever server restarts database will have predefined stocks values from `/src/main/resource/data.sql`. Persistent data can be done by specifying database filename in application.properties file.


### Client code
Client code resides in 'client' folder. UI is very basic with functionality of showing Stocks List. User can also add stocks and edit existing stocks.

Client is created using `React`. It also used `axiom` to call Backend Api's.
Client runs at `http://localhost:3000` address.


The following requests can be sent by clients to interact with the server.

**stocks**
- GET `/api/stocks`: gets all the stocks from the backend.
- GET `/api/stock/{id}`: retrieves information about stock based on their id.
- POST `/api/stock`: creates a new stock.
- PUT `/api/stock/{id}`: Update the existing stock.
- DELETE `/api/stock/{id}`: Deletes stock.

## Running Tests

You must have Java 11 installed to run the test suite as well as gradle.

You can run `./gradlew test` to invoke the test suite or use your IDE. It'll execute the tests in `src/test/java/com/stockproject/stockmarket/`.

## Schema Changes

To make a schema change, you can modify the file `src/main/resource/schema.sql`. Each table is emptied out between each test.
