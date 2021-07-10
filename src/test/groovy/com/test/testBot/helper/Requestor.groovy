package com.test.testBot.helper

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import utils.GetProperties
import utils.HeaderBuilder

class Requestor {

    private static Logger logger = LoggerFactory.getLogger(Requestor.class)

    static def restClient = new RESTClient(GetProperties.getSpecificProperty("baseUrl"))
    static def jsonSlurper = new JsonSlurper()

    static def contentType = "application/json"
    static def request

    static def requestToApi(String reqPath) {
        def response
        try {


            def correlationHeader = new HeaderBuilder().build()
            def path = GetProperties.getSpecificProperty(reqPath)

            logger.info("\nRequest URL: " + restClient.getUri().toString() + path + "\nRequest data : " + JsonOutput.toJson(request))

            response = restClient.get(
                    headers: correlationHeader,
                    path: path,
                    requestContentType: contentType
            )
            logger.info("\nRequest header "+correlationHeader.toString())

            logger.info("Response data  : " + response)
        return response
//        return JsonOutput.toJson(response.data)
        }
        catch (Exception ex) {
            return ExceptionHelper.handleException(ex)

        }
    }


}
