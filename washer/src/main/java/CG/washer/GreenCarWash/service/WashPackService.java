package CG.washer.GreenCarWash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import CG.washer.GreenCarWash.exceptionHandlers.APIrequestException;
import CG.washer.GreenCarWash.model.OrderDetails;
import CG.washer.GreenCarWash.model.WashPacks;
import CG.washer.GreenCarWash.repo.WashPackRepo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WashPackService {

	@Autowired
    public RestTemplate restTemplate;
    
    //Url to access the Admin service
    String url1="http://Admin-Service/admins/";
    //Url to access the methods of Order Service
    String url="http://Order-Service/orders/";

    /** Only the methods that use rest template are below this comment **/
    //To see all the WashPacks
    public List<WashPacks> getAllWP(){
        WashPacks[] wp=restTemplate.getForObject(url1+"/findallWP",WashPacks[].class);
        return Arrays.asList(wp);
    }
    //To see the Unassigned orders
    public List<OrderDetails> getUnassignedOrders(){
        OrderDetails[] unassignedList = restTemplate.getForObject(url+"/findUnassigned",OrderDetails[].class);
        return Arrays.asList(unassignedList );

    }
    //To update the status of the order by Washer
    public OrderDetails updateStatus(OrderDetails orderDetails){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<OrderDetails> updatedOrder = new HttpEntity<>(orderDetails,headers);
        OrderDetails od = restTemplate.exchange(url+"/updateStatus/", HttpMethod.PUT,updatedOrder,OrderDetails.class).getBody();
        return od;
    }
    //To assign a washer to the order by washer
    public OrderDetails assignSelf(OrderDetails orderDetails){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<OrderDetails> assignedWasher = new HttpEntity<>(orderDetails,headers);
        OrderDetails od = restTemplate.exchange(url+"/assignWasher", HttpMethod.PUT,assignedWasher,OrderDetails.class).getBody();
        return od;
    }
}

