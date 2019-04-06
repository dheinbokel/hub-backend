package com.hub.services;

import com.hub.daos.CommentsRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Comments;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class was created to handle the logic and thinking behind the comment requests taken in by the comment controller.
 */
@Service
public class CommentService {

    private CommentsRepository commentsRepository;

    CommentService(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }

    /**
     * Returns all comments in the database that come back from the commentsRepository method call. Sends as an iterable.
     * @return
     */
    public Iterable<Comments> findAllComments(){

        return commentsRepository.findAll();
    }

    /**
     * Returns all the comments in the database that are associated with the contentID given to this method's parameter.
     * @param contentID
     * @return
     */
    public Iterable<Comments> findCommentsByContentID(Integer contentID){

        return commentsRepository.findByContentID(contentID);
    }

    /**
     * Returns all the comments in the database that are associated with the userID given to this method's parameter.
     * @param userID
     * @return
     */
    public Iterable<Comments> findCommentsByUserID(Integer userID){

        return commentsRepository.findByUserID(userID);
    }

    /**
     * May return a null value or a comment object.  If there is no such id, the value returned is null. Otherwise, this
     * method will return a comment with the input commentID.
     * @param commentID
     * @return
     */
    public Optional<Comments> findCommentById(Integer commentID){

        Optional<Comments> comment = commentsRepository.findById(commentID);

        if(comment.isPresent()){

            return comment;
        }

        throw new HubNotFoundException("Could not find comment for commentID: " + commentID);
    }

    /**
     * Returns an iterable list of comments based on the username passed into the parameters.
     * @param username
     * @return
     */
    public Iterable<Comments> findByUserName(String username){

        return commentsRepository.findByUserName(username);
    }

    /**
     * Takes in a comment from the controller and saves it to the database as a comment record.
     * @param comments
     * @return
     */
    public Comments addComment(Comments comments){

        commentsRepository.save(comments);
        return comments;
    }

    public Comments changeComment(Comments comments, Integer id){

        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new HubNotFoundException("Could not find comment for commentID: " + id));

        comment.setComment(comments.getComment());
        Comments updatedComment = commentsRepository.save(comment);

        return updatedComment;
    }

    public Integer toggleComment(Integer id){

        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new HubNotFoundException("Could not find comment for commentID: " + id));

        if(comment.isActive()){
            comment.setActive(false);
        }
        else{
            comment.setActive(true);
        }

        commentsRepository.save(comment);
        return id;
    }

    public Integer deleteComment(Integer id){

        commentsRepository.deleteById(id);
        return id;
    }
}
