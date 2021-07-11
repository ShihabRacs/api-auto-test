package com.test.testBot.helper

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GetterUtils {

    static def jsonSlurper = new JsonSlurper()
    private static Logger logger = LoggerFactory.getLogger(Requestor.class)

    static def getSpecificPosts(int neededId){

        def response = Requestor.requestToApi("posts")
        logger.info("The post ids of the post List of the user is :")
        def posts = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        ArrayList neededposts = new ArrayList()
        for(post in posts){
            if (post.userId == neededId) {
                logger.info(post.id.toString())
                neededposts.add(post)

            }
        }
        return jsonSlurper.parseText(JsonOutput.toJson(neededposts))
    }

    static def getSpecificUser(String neededUserName){

        def response = Requestor.requestToApi("users")
        logger.info("The user List is :")


        def users = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        def neededUser
        for(user in users) {
            logger.info(user.username)
        }
        for(user in users){
            if (user.username == neededUserName) {
                neededUser = user
                return jsonSlurper.parseText(JsonOutput.toJson(neededUser))
            }

        }
        return null
    }

    static def getCommentsforPosts(int postId){

        def response = Requestor.requestToApi("comments",postId)
        def comments = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        return comments
    }

    static def getEmailsofComments(int postId){

        def response = Requestor.requestToApi("comments",postId)
        def comments = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        ArrayList neededEmail = new ArrayList()
        for(comment in comments){
            logger.info("email of the comment "+comment.id.toString() + " of the post "+postId.toString()+" is "+comment.email)
            neededEmail.add(comment.email)
        }
        return neededEmail
    }
}
