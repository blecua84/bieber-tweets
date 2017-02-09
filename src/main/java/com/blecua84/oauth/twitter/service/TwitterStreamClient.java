package com.blecua84.oauth.twitter.service;

import com.google.api.client.http.HttpRequestFactory;
import com.blecua84.oauth.twitter.service.exception.TwitterStreamClientException;

/**
 * Interface of definition of the necessary operation to implement to do the exercise.
 *
 * @author bleck84
 */
public interface TwitterStreamClient {

    /**
     * Processing the tweets of the exercise from a HttpRequestFactory object.
     *
     * @param httpRequestFactory
     * @throws TwitterStreamClientException
     */
    void processingTweets(HttpRequestFactory httpRequestFactory) throws TwitterStreamClientException;
}
