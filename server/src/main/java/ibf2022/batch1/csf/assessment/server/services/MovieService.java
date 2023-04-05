package ibf2022.batch1.csf.assessment.server.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.utils.Utils;
import jakarta.json.JsonObject;;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	private static final String NYTIMES_MOVIE_SEARCH_API = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	@Value("${nytimes.api.key}")
    private String apiKey;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {
		// https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=&api-key=
		String apiUrl = UriComponentsBuilder
							.fromUriString(NYTIMES_MOVIE_SEARCH_API)
							.queryParam("query", query)
							.queryParam("api-key", apiKey)
							.toUriString();

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = null;

		try {
			response = restTemplate.getForEntity(apiUrl, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return Collections.emptyList();
		} 

		String payload = response.getBody();
		JsonObject jsonObject = Utils.jsonStringToJsonObject(payload);
		List<Review> reviewList = Utils.jsonObjectToReviewList(jsonObject);
		reviewList.forEach(review -> {
			review.setCommentCount(movieRepository.countComments(review.getTitle()));
		});
		return reviewList;
	}

	public String saveComment(Comment comment) {
		movieRepository.saveComment(comment);
		return comment.getTitle();
	}

}
