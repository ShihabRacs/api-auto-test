package com.test.testBot.helper

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GetPosts {

    static def jsonSlurper = new JsonSlurper()
    private static Logger logger = LoggerFactory.getLogger(Requestor.class)

    static def getSpecificPosts(int neededId){

        def response = Requestor.requestToApi("posts")
        logger.info("The post List is :")
        logger.info(JsonOutput.toJson(response.data))
        def posts = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        ArrayList neededposts = new ArrayList()
        for(post in posts){
            if (post.userId == neededId) {
                neededposts.add(post)

            }
        }
        return jsonSlurper.parseText(JsonOutput.toJson(neededposts))
    }
}
