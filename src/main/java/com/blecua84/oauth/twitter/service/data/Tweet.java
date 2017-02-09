package com.blecua84.oauth.twitter.service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Serialize class for readed tweets.
 *
 * @author bleck84
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet implements Comparable{

    @JsonProperty(TweetConstantsKey.MESSAGE_ID)
    private String messageId;

    @JsonProperty(TweetConstantsKey.CREATED_AT)
    private Date creationDate;

    @JsonProperty(TweetConstantsKey.TEXT)
    private String messageText;

    @JsonProperty(TweetConstantsKey.AUTHOR)
    private Author author;

    public String getMessageId() {
        return messageId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public Author getAuthor() {
        return author;
    }

    public int compareTo(Object o) {
        final int EQUAL = 0;
        int result;

        Tweet oTweet = (Tweet) o;

        if(this == oTweet)
            result = EQUAL;
        else
            result = this.getCreationDate().compareTo(oTweet.getCreationDate());

        return result;
    }

    @Override
    public String toString(){
        return "Tweet: [" +
                TweetConstantsKey.MESSAGE_ID + ": " + messageId + "," +
                TweetConstantsKey.CREATED_AT + ": " + creationDate + "," +
                TweetConstantsKey.TEXT + ": " + messageText + "," +
                TweetConstantsKey.AUTHOR + ": " + author.toString() +
                "]";
    }
}
