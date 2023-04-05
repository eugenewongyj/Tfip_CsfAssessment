package ibf2022.batch1.csf.assessment.utils;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

    public static JsonObject jsonStringToJsonObject(String jsonString) {
        Reader reader = new StringReader(jsonString);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    public static List<Review> jsonObjectToReviewList(JsonObject jsonObject) {
        return jsonObject.getJsonArray("results")
                    .stream()  
                    .map(JsonObject.class::cast)
                    .map(Utils::jsonObjectToReview)
                    .toList();
    }

    // {
    //     "display_title": "The Godfather, Part II",
    //     "mpaa_rating": "R",
    //     "critics_pick": 1,
    //     "byline": "VINCENT CANBY",
    //     "headline": "Godfather: Part II, The (Movie)",
    //     "summary_short": "",
    //     "publication_date": "1974-12-13",
    //     "opening_date": "1974-12-20",
    //     "date_updated": "2017-11-02 04:17:27",
    //     "link": {
    //     "type": "article",
    //     "url": "https://www.nytimes.com/1974/12/13/archives/godfather-part-ii-is-hard-to-define-the-cast.html",
    //     "suggested_link_text": "Read the New York Times Review of The Godfather, Part II"
    //     },
    //     "multimedia": null
    //     }
    public static Review jsonObjectToReview(JsonObject jsonObject) {
        Review review = new Review();
        review.setTitle(jsonObject.getString("display_title"));
        review.setRating(jsonObject.getString("mpaa_rating"));
        review.setByline(jsonObject.getString("byline"));
        review.setHeadline(jsonObject.getString("headline"));
        review.setSummary(jsonObject.getString("summary_short"));
        review.setReviewURL(jsonObject.getJsonObject("link").getString("url"));
        if (!jsonObject.isNull("multimedia")) {
            review.setImage(jsonObject.getJsonObject("multimedia").getString("src"));
        } else {
            review.setImage("no image found");
        }
        
        return review;
    }

    public static JsonArray reviewListToJsonArray(List<Review> reviewList) {
        // JsonArrayBuilder
        return null;
    }

    public static JsonObject reviewToJsonObject(Review review) {
        return Json.createObjectBuilder()
                .add("title", review.getTitle())
                .add("rating", review.getRating())
                .add("byline", review.getByline())
                .add("headline", review.getHeadline())
                .add("summary", review.getSummary())
                .add("reviewURL", review.getReviewURL())
                .add("image", review.getImage())
                .build();

    }

   
    
}
