# 📘 Spring Boot gRPC User Service

A minimal, clean Spring Boot + gRPC example demonstrating how to expose a `getUser` RPC endpoint, map domain objects to protobuf messages, and run a lightweight gRPC server.  
Spring-Boot-GRPC-Example-masterThis project is intentionally small — only six core files — making it ideal for learning, demos, or onboarding new developers.

---

## 📂 Project Structure

```
src/
└── main/
    ├── java/
    │   └── com/app/employee/
    │       ├── SpringBootGrpcServerExampleApplication.java
    │       ├── model/
    │       │   └── User.java
    │       ├── service/
    │       │   ├── UserDomainService.java
    │       │   ├── UserGrpcService.java
    │       │   └── ReflectionPrinter.java
    └── proto/
        └── user-service.proto
```

---

## 🧩 1. `user-service.proto`

Defines the gRPC contract for the UserService.

- `UserRequest` contains `user_id`
- `UserResponse` contains 12 user attributes
- `UserService` exposes `rpc getUser(UserRequest) returns (UserResponse)`

This file is the **source of truth** for all generated Java classes.

---

## 🧩 2. `UserGrpcService.java`

Implements the gRPC endpoint defined in the proto.

Responsibilities:

- Receives `UserRequest`
- Calls `UserDomainService`
- Maps domain `User` → protobuf `UserResponse`
- Sends the response via `StreamObserver`

This is the actual gRPC server logic.

---

## 🧩 3. `UserDomainService.java`

A simple domain-layer service that returns a `User` object.

- Currently returns mock data
- In real applications, this is where DB calls or external service calls would live

This keeps your gRPC layer clean and business logic separate.

---

## 🧩 4. `User.java`

A plain domain model representing a user.

- Contains 12 fields (userId, firstName, lastName, email, etc.)
- Annotated with Lombok (`@Data`) for getters/setters

This object is mapped into the protobuf response.

---

## 🧩 5. `ReflectionPrinter.java`

A utility class that uses Java reflection to print object fields dynamically.

Useful for:

- Debugging
- Logging domain objects without manually writing `toString()`
- Inspecting objects during development

---

## 🧩 6. `SpringBootGrpcServerExampleApplication.java`

The Spring Boot entry point.

- Boots the application
- Starts the embedded gRPC server
- Makes the `UserService` available on the configured port (default: 8090)

---

## 🚀 Running the Project

### Build
```
mvn clean install
```

### Run
```
mvn spring-boot:run
```

The gRPC server will start and expose:

```
UserService → getUser()
```

---

## 🧪 Calling the gRPC Service (Client Example)

```java
ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 8090)
        .usePlaintext()
        .build();

UserServiceGrpc.UserServiceBlockingStub stub =
        UserServiceGrpc.newBlockingStub(channel);

UserResponse response = stub.getUser(
        UserRequest.newBuilder().setUserId("101").build()
);

System.out.println(response);

channel.shutdown();
```

---

## 📡 API Summary

### RPC: getUser

| Field | Type | Description |
|------|------|-------------|
| user_id | string | ID of the user to fetch |

### Response Fields

- user_id
- first_name
- last_name
- email
- phone
- address
- city
- state
- country
- zip_code
- role
- status

---

## 🔮 Future Enhancements

- Add database integration (JPA, MongoDB, etc.)
- Add validation and error handling
- Add unit tests for gRPC service
- Add client module with CLI or REST wrapper
- Add logging using ReflectionPrinter

---