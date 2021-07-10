package com.test.testBot.helper

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GetUsers {
    static def jsonSlurper = new JsonSlurper()
    private static Logger logger = LoggerFactory.getLogger(Requestor.class)


    static def getSpecificUser(String neededUserName){

        def response = Requestor.requestToApi("users")
        logger.info("The user List is :")
        logger.info(JsonOutput.toJson(response.data))
        def users = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        def neededUser
        for(user in users){
            if (user.username == neededUserName) {
                neededUser = user
                return jsonSlurper.parseText(JsonOutput.toJson(neededUser))
            }
        }
        return null
    }

}
