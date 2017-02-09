package com.blecua84.oauth.twitter;

import com.google.api.client.http.HttpRequestFactory;
import com.blecua84.oauth.twitter.service.TwitterStreamClientImpl;
import com.blecua84.oauth.twitter.service.exception.TwitterStreamClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class.
 *
 * @author bleck84
 */
public class TwitterClientMain {

    /** System logger */
    private static final Logger logger = LoggerFactory.getLogger(TwitterClientMain.class);

    public static void main(String[] args){
        logger.info("Init main....");
        try {
            TwitterAuthenticator authenticator = new TwitterAuthenticator(
                    "RLSrphihyR4G2UxvA0XBkLAdl", "FTz2KcP1y3pcLw0XXMX5Jy3GTobqUweITIFy4QefullmpPnKm4");

            // Gets the authentication
            HttpRequestFactory httpRequestFactory = authenticator.getAuthorizedHttpRequestFactory();

            // Processing tweets
            TwitterStreamClientImpl twwiterStreamClient = new TwitterStreamClientImpl();
            twwiterStreamClient.processingTweets(httpRequestFactory);
        } catch (TwitterAuthenticationException | TwitterStreamClientException e) {
            logger.error("Error: " + e.getMessage(), e);
        }
        logger.info("End main!!");
    }

}
