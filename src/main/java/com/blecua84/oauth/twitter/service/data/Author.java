package com.blecua84.oauth.twitter.service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Serialize class for the Author's data.
 *
 * @author bleck84
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    @JsonProperty(TweetConstantsKey.USER_ID)
    private String userId;

    @JsonProperty(TweetConstantsKey.CREATED_AT)
    private Date creationDate;

    @JsonProperty(TweetConstantsKey.NAME)
    private String name;

    @JsonProperty(TweetConstantsKey.SCREEN_NAME)
    private String screenName;

    public String getUserId() {
        return userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    @Override
    public String toString(){
        return "Author: [" +
                TweetConstantsKey.USER_ID + ": " + userId + "," +
                TweetConstantsKey.CREATED_AT + ": " + creationDate + "," +
                TweetConstantsKey.NAME + ": " + name + "," +
                TweetConstantsKey.SCREEN_NAME + ": " + screenName +
                "]";
    }
}
