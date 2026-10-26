package sep3.drivia.tier2.grpc;


import sep3.drivia.grpc.CarList;
import sep3.drivia.grpc.CarServiceGrpc;
import sep3.drivia.grpc.GetAllCarsRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CarDataClient {

  private final ManagedChannel channel;
  private final CarServiceGrpc.CarServiceBlockingStub stub;

  public CarDataClient(
      @Value("${grpc.client.host:localhost}") String host,
      @Value("${grpc.client.port:9090}") int port) {

    // Create a connection to Tier 3
    channel = ManagedChannelBuilder
        .forAddress(host, port)
        .usePlaintext()
        .build();

    // Create the gRPC client stub
    stub = CarServiceGrpc.newBlockingStub(channel);
  }

  public CarList getAllCars() {

    // Create an empty request
    GetAllCarsRequest request = GetAllCarsRequest.newBuilder().build();

    // Call the GetAllCars method on Tier 3
    return stub.getAllCars(request);
  }

  @PreDestroy
  public void shutdown() {
    channel.shutdown();
  }
}
