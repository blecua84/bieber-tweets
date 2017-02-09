Bieber Tweets application

Functionality: 

+ Connect to the [Twitter Streaming API](https://dev.twitter.com/streaming/overview)
    * Use the following values(See in project):
    * The app name will be `bieber-tweets`
    * You will need to login with Twitter
+ Filter messages that track on "bieber"
+ Retrieve the incoming messages for 30 seconds or up to 100 messages, whichever comes first
+ Your application should return the messages grouped by user (users sorted chronologically, ascending)
+ The messages per user should also be sorted chronologically, ascending
+ For each message, we will need the following:
    * The message ID
    * The creation date of the message as epoch value
    * The text of the message
    * The author of the message
+ For each author, we will need the following:
    * The user ID
    * The creation date of the user as epoch value
    * The name of the user
    * The screen name of the user
+ All the above infomation is provided in either SDTOUT or a log file
+ You are free to choose the output format, provided that it makes it easy to parse and process by a machine

This application can be executed in docker. 


Docker Configuration:

+ It's mandatory to have a Docker Server in local(127.0.0.1).
+ Define in the host-system a log folder(In this, the application will save the log file generated).
	* Example: /home/bieber-tweets/logs/	
+ In the proyect root, in the same level of the Dockerfile, build the image with the following instruction:
	* mvn clean install -Plocal
+ Execute container with the following instruction:
	* docker run -v /home/bieber-tweets/logs/:/tmp -d --name bieber-tweets com.blecua84/bieber-tweets
	* It'll execute a container with a volume, mapping the host-system folder with the container folder log defined in log4j.properties, in the project.
+ The log file will generated in the host-system folder defined.