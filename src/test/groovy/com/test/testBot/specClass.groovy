import com.test.testBot.helper.requestor
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.junit.Rule
import org.junit.rules.TestName
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification
import spock.lang.Unroll

class specClass extends Specification  {

    @Rule
    TestName testName = new TestName()

    private static Logger logger = LoggerFactory.getLogger(requestor.class)
    def jsonSlurper = new JsonSlurper()

    static def scenarioCount = 1
    static def scenarioSerial = scenarioCount


    def setup() {
        logger.info("\n\n================================== Scenario (Sync) - " + testName.methodName + " =======================================\n")
        ++scenarioCount
        scenarioSerial = scenarioCount
    }

    @Unroll
    def " (#scenarioSerial) searching for the user Delphine"() {
        given: "requesting the API for the user list"
        logger.info ("----------------------------requesting the API for the users----------------------------")

        when: " We check for a bank account validation"
        logger.info ("We check for a bank account validation")
        def response = requestor.requestToApi("users")

        logger.info("This is the response " + JsonOutput.toJson(response.data))

        logger.info("This is the response Code " + response.responseBase.statusline.statusCode)
        def userList = jsonSlurper.parseText(JsonOutput.toJson(response.data))
        def neededUser
        for(user in userList){
            if (user.username == "Delphine")
                neededUser = user
        }

        logger.info("delphine is here "+ JsonOutput.toJson(neededUser))

        then: "we check if the response is correct or not"
        logger.info("we check if the response is correct or not")
        response.responseBase.statusline.statusCode == responseStatus
        logger.info("Expected responseCode is 200")
        logger.info("This is the response " + response.responseBase.statusline.statusCode)
        logger.info("Response is expected")

        where: "a set of other parameters"
        responseStatus | scenarioSerial
        200 | scenarioSerial

    }



}
