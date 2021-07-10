package com.test.testBot.helper

import groovy.json.JsonOutput
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ExceptionHelper {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHelper.class)

    static def handleException(Exception ex) {

        logger.error("Exception occurred : " + ex.getMessage() + "\nStack trace for the exception: " + ex.getStackTrace())
        if(ex.response.hasProperty('data')) {
            logger.error("\nResponse data : " + JsonOutput.toJson(ex.response.data))
            return ex.response.data
        }
        else {
            logger.error("\nResponse data : " + JsonOutput.toJson(ex.response))
            return ex.response
        }
    }
}