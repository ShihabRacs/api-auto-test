package com.test.testBot.helper

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GetUsers {
    static def jsonSlurper = new JsonSlurper()
    private static Logger logger = LoggerFactory.getLogger(Requestor.class)

    static def getSpecificUser(ArrayList users,String neededUserName){

//        def userList = jsonSlurper.parseText(JsonOutput.toJson(users))
//        logger.info("**********************************"+userList)

        def neededUser
        for(user in users){
            if (user.username == neededUserName)
                neededUser = user
        }
        return jsonSlurper.parseText(JsonOutput.toJson(neededUser))
    }

    static def getSpecificUserId(ArrayList users,String neededUserName){

//        def userList = jsonSlurper.parseText(JsonOutput.toJson(users))
//        logger.info("**********************************"+userList)

        def neededUser
        for(user in users){
            if (user.username == neededUserName)
                neededUser = user
        }
        return neededUser.id
    }
}
