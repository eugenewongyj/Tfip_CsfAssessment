package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import ibf2022.batch1.csf.assessment.utils.Utils;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping(path="/search")
	@CrossOrigin(origins="*")
	public ResponseEntity<String> getMovies(@RequestParam String query) {
		List<Review> reviewList = movieService.searchReviews(query);
		JsonArray jsonArray = Utils.reviewListToJsonArray(reviewList);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(jsonArray.toString());
	}

	@PostMapping(path="/comment", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@CrossOrigin(origins="*")
	public ResponseEntity<String> postComment(Comment comment) {
		// JsonObject jsonObject = Utils.jsonStringToJsonObject(payload);
		// Comment comment = Utils.jsonObjectToComment(jsonObject);
		System.out.println("In controller");
		String title = movieService.saveComment(comment);
		JsonObject response = Json.createObjectBuilder()
                                .add("title", title)
                                .add("message", "Comment saved")
                                .build();         
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response.toString());
	
	}


}
