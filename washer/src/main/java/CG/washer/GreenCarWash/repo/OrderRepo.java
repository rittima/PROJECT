package CG.washer.GreenCarWash.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CG.washer.GreenCarWash.model.OrderDetails;

@Repository
public interface OrderRepo extends MongoRepository<OrderDetails, Long> {

}
