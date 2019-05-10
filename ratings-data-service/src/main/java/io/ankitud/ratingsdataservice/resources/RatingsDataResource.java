package io.ankitud.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ankitud.ratingsdataservice.models.Rating;
import io.ankitud.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 5);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getMovies(@PathVariable String userId) {
		
		UserRating userRating = new UserRating();
		
		userRating.setRatings(
				Arrays.asList(
				new Rating("1001", 5),
				new Rating("3312", 4),
				new Rating("9907", 4),
				new Rating("1111", 4)
			)
		);
		
		return userRating;
	}
}
