package com.pluralsight.controller;

public class RestControllerTest {

  /*
   * @Test(timeout=3000) public void testCreateRide() { RestTemplate restTemplate = new
   * RestTemplate();
   * 
   * Order ride = new Order(); ride.setName("Bobsled Trail"); ride.setDuration(33);
   * 
   * ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride,
   * Order.class);
   * 
   * System.out.println("Ride: " + ride); }
   * 
   * @Test(timeout=3000) public void testGetRides() { RestTemplate restTemplate = new
   * RestTemplate();
   * 
   * ResponseEntity<List<Order>> ridesResponse = restTemplate.exchange(
   * "http://localhost:8080/ride_tracker/rides", HttpMethod.GET, null, new
   * ParameterizedTypeReference<List<Order>>() { }); List<Order> rides = ridesResponse.getBody();
   * 
   * for (Order ride : rides) { System.out.println("Ride name: " + ride.getName()); } }
   * 
   * @Test(timeout=3000) public void testGetRide() { RestTemplate restTemplate = new RestTemplate();
   * 
   * Order ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1",
   * Order.class);
   * 
   * System.out.println("Ride name: " + ride.getName());
   * 
   * }
   * 
   * @Test(timeout=3000) public void testUpdateRide() { RestTemplate restTemplate = new
   * RestTemplate();
   * 
   * Order ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1",
   * Order.class);
   * 
   * ride.setDuration(ride.getDuration() + 1);
   * 
   * restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
   * 
   * System.out.println("Ride name: " + ride.getName());
   * 
   * }
   * 
   * @Test(timeout=3000) public void testBatchUpdate() { RestTemplate restTemplate = new
   * RestTemplate();
   * 
   * restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
   * 
   * }
   * 
   * @Test(timeout=3000) public void testDelete() { RestTemplate restTemplate = new RestTemplate();
   * 
   * restTemplate.delete("http://localhost:8080/ride_tracker/delete/15");
   * 
   * }
   * 
   * @Test(timeout=3000) public void testException() { RestTemplate restTemplate = new
   * RestTemplate();
   * 
   * restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Order.class);
   * 
   * }
   */

}
