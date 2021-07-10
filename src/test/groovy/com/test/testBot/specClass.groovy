import com.test.testBot.helper.Requestor
import com.test.testBot.helper.GetUsers
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.junit.Rule
import org.junit.rules.TestName
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification
import spock.lang.Unroll
import utils.GetProperties

class specClass extends Specification  {

    @Rule
    TestName testName = new TestName()

    private static Logger logger = LoggerFactory.getLogger(Requestor.class)
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

        when: " We search for the user"
        logger.info ("We search for the user")
        def response = Requestor.requestToApi("users")

        and: "we search for the specific user from the list"

        def neededUser = GetUsers.getSpecificUser(jsonSlurper.parseText(JsonOutput.toJson(response.data)),
                        GetProperties.getSpecificProperty("username"))

        def neededuserName = GetProperties.getSpecificProperty("username")

        then: "we check if the response is correct or not"
        logger.info("we check if the response is correct or not")
        response.responseBase.statusline.statusCode == responseStatus
        logger.info("Expected responseCode is 200")
        logger.info("This is the response " + response.responseBase.statusline.statusCode)
        logger.info("Response is expected")

        and: " if the user is found or not"
        logger.info("we check if the user is found or not")
        neededUser.username == neededuserName
        logger.info("Expected username is " +username)
        logger.info("This is the user from response list " + neededUser.username)
        logger.info("user is found")

        where: "a set of other parameters"
        username | responseStatus | scenarioSerial
        "Delphine" | 200 | scenarioSerial

    }


  



}
