package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;

@RestController
@RequestMapping(path="/api")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping(path="/search")
	@CrossOrigin(origins="*")
	public ResponseEntity<String> getMovies(@RequestParam String query) {
		List<Review> reviewList = movieService.searchReviews(query);
		return null;
	}


}
