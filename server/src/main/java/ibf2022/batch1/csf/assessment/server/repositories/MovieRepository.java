package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.utils.Utils;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// db.comments.find({ title: "The Black Godfather" }).count()


	public int countComments(String param) {
		Criteria criteria = Criteria.where("title").is(param);
		Query query = Query.query(criteria);

		return (int) mongoTemplate.count(query, "comments");



	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	/* 
	db.comments.insert({
		title: "The Black Godfather",
		userName: "Eugene"
		rating: 5,
		commentText: "Very good"
	})
	*/
	public void saveComment(Comment comment) {
		Document documentComment = Utils.commentToDocument(comment);
		documentComment = mongoTemplate.insert(documentComment, "comments");
	}
}
