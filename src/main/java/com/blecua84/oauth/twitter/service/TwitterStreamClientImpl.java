package com.blecua84.oauth.twitter.service;

import com.blecua84.oauth.twitter.service.data.Tweet;
import com.blecua84.oauth.twitter.service.exception.TwitterStreamClientExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.blecua84.oauth.twitter.service.exception.TwitterStreamClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Implementation class of the operation of the exercise.
 *
 * @author bleck84
 */
public class TwitterStreamClientImpl implements TwitterStreamClient {

    /**
     * System Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TwitterStreamClientImpl.class);

    /**
     * Endpoint
     */
    private static final String ENDPOINT_STREAM_TWITTER = "https://stream.twitter.com/1.1/statuses/filter.json";

    /**
     * Max number of tweets
     */
    private static final int MAX_TWEET_SIZE = 100;

    /**
     * Max interval of time
     */
    private static final long MAX_TIME_INTERVAL = 30000;

    /**
     * Map of tweets group by User
     */
    private Map<String, List<Tweet>> tweetByUser;

    private static ObjectMapper mapper;

        /**
         * Get the tweets filter by the word Bieber and make a list with they.
         *
         * @param httpRequestFactory HttpRequestFactoy with the authentication.
         * @return List<Tweet>
         * @throws IOException
         */
    private List<Tweet> parseInputStreamToTweetList(HttpRequestFactory httpRequestFactory) throws IOException {
        logger.info("Init parseInputStreamToTweetList...");
        List<Tweet> tweetList = new ArrayList<>();

        HttpRequest request = httpRequestFactory.buildGetRequest(
                new GenericUrl(ENDPOINT_STREAM_TWITTER.concat("?track=Bieber")));

        HttpResponse response = request.execute();

        InputStream in = response.getContent();

        ObjectMapper mapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        mapper.setDateFormat(dateFormat);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();

        int countTweets = 0;
        long startTime = System.currentTimeMillis();

        while (line != null && countTweets < MAX_TWEET_SIZE &&
                (System.currentTimeMillis() - startTime < MAX_TIME_INTERVAL)) {
            // Parse tweet and add to the list
            tweetList.add(mapper.readValue(line, Tweet.class));

            line = reader.readLine();
            countTweets++;
            logger.info("Number of Input Tweets: " + countTweets);
        }

        logger.info("End parseInputStreamToTweetList!!");
        return tweetList;
    }

    /**
     * Gets the map of tweets group by user.
     *
     * @param tweetList Generated Tweet List.
     */
    private void setMapOfTweetsSortedByUserChronologycally(List<Tweet> tweetList) {
        logger.info("Init setMapOfTweetsSortedByUserChronologycally...");

        tweetByUser = tweetList.stream()
                .sorted((p1,p2) -> p1.getAuthor().getCreationDate().compareTo(p2.getAuthor().getCreationDate()))
                .collect(Collectors.groupingBy(
                        p -> p.getAuthor().getUserId()));


        for (List<Tweet> userTweetList : tweetByUser.values()) {
            userTweetList.sort((p1, p2) -> p1.compareTo(p2));
        }

        logger.info("End setMapOfTweetsSortedByUserChronologycally!!");
    }

    private void printSortedTweets() {
        logger.info("Init printSortedTweets...");


        logger.info("Final tweet list: ");
        for (List<Tweet> userTweetList : tweetByUser.values()) {
            for(Tweet tweet: userTweetList) {
                logger.info(tweet.toString());
            }
        }

        logger.info("End printSortedTweets!!");
    }

    @Override
    public void processingTweets(HttpRequestFactory httpRequestFactory) throws TwitterStreamClientException {
        logger.info("Init processingTweets...");
        try {
            setMapOfTweetsSortedByUserChronologycally(parseInputStreamToTweetList(httpRequestFactory));

            printSortedTweets();
        } catch (IOException e) {
            String msg = TwitterStreamClientExceptionCode.KO_IO_EXCEPTION.getMessage().concat(e.getMessage());

            logger.error(msg, e);

            throw new TwitterStreamClientException(e, TwitterStreamClientExceptionCode.KO_IO_EXCEPTION.getCode(), msg);
        }
        logger.info("End processingTweets!!");
    }
}
