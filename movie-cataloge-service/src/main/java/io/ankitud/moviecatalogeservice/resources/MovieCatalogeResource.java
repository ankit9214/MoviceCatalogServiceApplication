package io.ankitud.moviecatalogeservice.resources;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.ankitud.moviecatalogeservice.models.CatalogeItem;
import io.ankitud.moviecatalogeservice.models.Movie;
import io.ankitud.moviecatalogeservice.models.Rating;
import io.ankitud.moviecatalogeservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogeResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogeItem> getMovieCataloge(@PathVariable String userId) {
		
		
		//restTemplate.getForObject("localhost:8081/movies/foo", Movie.class);
		
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		
		//for each movie ID, call movie info Service and get details
		return userRating.getRatings().stream().map(rating -> {
			
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogeItem(movie.getName(), "Desc", rating.getRating());
			
		})
		.collect(Collectors.toList());
		//Put them all together
		
		/*return Collections.singletonList(
					new CatalogeItem("Shawshanks Redemption", "Test", 5)
				);*/
	}
}
