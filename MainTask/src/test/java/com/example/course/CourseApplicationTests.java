package com.example.course;

import com.example.course.Config.ReviewPK;
import com.example.course.Entities.Restaurant;
import com.example.course.Entities.Review;
import com.example.course.Entities.Visitor;
import com.example.course.Repositories.RestaurantRepository;
import com.example.course.Repositories.ReviewRepository;
import com.example.course.Repositories.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class CourseApplicationTests {
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Test
	void contextLoads() {
		Visitor visitor = new Visitor();
		visitor.setName("Anton");
		visitor.setGender("M");
		visitor.setAge(23);
		visitorRepository.save(visitor);

		Restaurant restaurant = new Restaurant();
		restaurant.setName("AbobaRest2");
		restaurant.setDescription("Cool place");
		restaurant.setAverageBill(BigDecimal.valueOf(1000));
		restaurant.setKitchenType(Restaurant.KitchenType.JAPANESE);
		restaurantRepository.save(restaurant);

		Review review = new Review();
		ReviewPK pk = new ReviewPK();
		pk.setVisitorId(1L);
		pk.setRestaurantId(1L);
		review.setId(pk);
		review.setVisitor(visitorRepository.findById(1L).orElse(null));
		review.setRestaurant(restaurantRepository.findById(1L).orElse(null));
		review.setRating(5);
		reviewRepository.save(review);
	}

}
