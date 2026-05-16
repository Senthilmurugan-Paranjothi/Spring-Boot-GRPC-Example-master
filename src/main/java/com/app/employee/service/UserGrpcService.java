package com.app.employee.service;

import com.app.employee.UserRequest;
import com.app.employee.UserResponse;
import com.app.employee.AllUserResponse;
import com.app.employee.UserServiceGrpc;
import com.app.employee.model.User;
//import com.app.employee.service.UserDomainService;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

	@Autowired
	private UserDomainService userDomainService;

	@Override
	public void getUser(UserRequest request,
						io.grpc.stub.StreamObserver<UserResponse> responseObserver) {

		// Fetch domain object
		User user = userDomainService.getUserById(request.getUserId());
		//User user = getUserById(request.getUserId());

		// Map domain → proto
		UserResponse response = UserResponse.newBuilder()
				.setUserId(user.getUserId())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setEmail(user.getEmail())
				.setPhone(user.getPhone())
				.setAddress(user.getAddress())
				.setCity(user.getCity())
				.setState(user.getState())
				.setCountry(user.getCountry())
				.setZipCode(user.getZipCode())
				.setRole(user.getRole())
				.setStatus(user.getStatus())
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getAllUsers(UserRequest request,
						io.grpc.stub.StreamObserver<AllUserResponse> responseObserver) {

		// Fetch domain object
		User user = userDomainService.getUserById(request.getUserId());
		//User user = getUserById(request.getUserId());

		// Map domain → proto

		for (int i = 1; i <= 20; i++) {

			AllUserResponse response = AllUserResponse.newBuilder()
					.setUserId(String.valueOf(i))   // iteration count as userId
					.setFirstName(user.getFirstName())
					.setLastName(user.getLastName())
					.setEmail(user.getEmail())
					.setPhone(user.getPhone())
					.setAddress(user.getAddress())
					.setCity(user.getCity())
					.setState(user.getState())
					.setCountry(user.getCountry())
					.setZipCode(user.getZipCode())
					.setRole(user.getRole())
					.setStatus(user.getStatus())
					.build();

			responseObserver.onNext(response);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		responseObserver.onCompleted();
	}


	public User getUserById(String userId) {
		// Mock data — replace with DB call
		User user = new User();
		user.setUserId(userId);
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmail("john.doe@app.com");
		user.setPhone("555-1234");
		user.setAddress("123 Main St");
		user.setCity("Louisville");
		user.setState("KY");
		user.setCountry("USA");
		user.setZipCode("40299");
		user.setRole("ADMIN");
		user.setStatus("ACTIVE");
		return user;
	}
}

