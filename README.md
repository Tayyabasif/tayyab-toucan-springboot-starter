# Toucan Payments — Transaction Service

## Understanding of the problem
This service manages customer transactions and implements the four required operations: create a transaction, retrieve one transaction, update its status, and retrieve all transactions for a customer.

The challenge requires Java, Spring Boot, H2, Maven and JUnit, plus at least four meaningful tests.

## Assumptions and validation rules
These rules are deliberately explicit because the challenge asks the candidate to define them:

- `transactionId` and `customerId`: required.
- `amount`: required, greater than 0, maximum 100,000.
- `currency`: one of INR, USD.
- `transactionType`: UPI or NEFT.
- New transactions start as `PENDING`.
- `transactionId` is unique.
- Status transitions allowed: `PENDING -> COMPLETED` and `PENDING -> FAILED`.
- `COMPLETED` and `FAILED` are terminal states. Repeating the current status is treated as idempotent.

## API

### Create transaction
`POST /api/v1/transactions`

```json
{
  "transactionId": "tx-1001",
  "customerId": "cust-42",
  "amount": 1250.50,
  "currency": "INR",
  "transactionType": "UPI"
}
```

Returns `201 Created` with the transaction or `400 Bad Request` if transaction fails validation rules.

### Get transaction
`GET /api/v1/transactions/{customerID}`

Returns the customer's transactions with `200 OK`, or `404 Not Found` if it does not exist.

### Update transaction status
`PATCH /api/v1/transactions/{transactionId}/{transactionStatus}`

Returns `200 OK`. Invalid transitions return `400 Bad Request` or `404 Not Found` if the transaction does not exist.

### Get customer transactions
`GET /api/v1/transactions/customer`

Returns all transactions with `200 OK`. If no transactions exist returns an empty array.

## Error handling
- `400 Bad Request`: validation failure or invalid status transition.
- `404 Not Found`: transaction does not exist.
- `409 Conflict`: duplicate transaction ID.

Errors use a small JSON body containing, HTTP status and message.

## Structure
- `controller`: HTTP API
- `service`: business rules and status transition logic
- `repository`: Spring Data JPA persistence
- `entity`: transaction/domain enums
- `dto`: request objects and response object
- `exception`: domain exceptions and global HTTP error mapping
- `helper` : helper class for request to entity and entity to response conversion

## Testing
The test suite covers successful creation, validation rejection, duplicate ID rejection, and missing transaction handling.

Run:

```
./mvnw clean test
```

## Known limitations / improvements
- H2 is in-memory, so data disappears when the application stops.
- No authentication/authorization is included.
- Currency-specific amount limits are not modeled.

## AI Usage Disclosure
I used an Chatgpt AI coding assistant during the implementation and development process. The AI was used to help scaffold the Spring Boot implementation, suggest a package structure, generate the initial service-layer implementation, and create an initial test suite. The AI generated an initial version of the service class and suggested using it directly within the controller. After reviewing the generated design, I identified that directly using the concrete service class in the controller created tight coupling between the controller and service layer. To improve the design and achieve loose coupling, I introduced a service interface and made the generated service class implement that interface. The controller was then updated to depend on the interface rather than the concrete service implementation.
