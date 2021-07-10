import com.test.testBot.helper.GetPosts
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
    static def neededUser
    static def neededposts


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


        and: "we search for the specific user from the list"

        neededUser = GetUsers.getSpecificUser(GetProperties.getSpecificProperty("username"))
        def neededuserName = GetProperties.getSpecificProperty("username")

        then: "if the user is found or not"

        logger.info("we check if the user is found or not")
        logger.info("Expected username is " +username)
        neededUser.username == neededuserName
        logger.info("This is the user from response list " + neededUser.username)
        logger.info("user is found")

        where: "a set of other parameters"
        username | responseStatus | scenarioSerial
        "Delphine" | 200 | scenarioSerial

    }

    @Unroll
    def " (#scenarioSerial) searching for non existing user"() {
        given: "requesting the API for the user list"
        logger.info ("----------------------------requesting the API for the users----------------------------")

        when: " We search for the user"
        logger.info ("We search for the user")

        def neededInvalidUser = GetUsers.getSpecificUser(GetProperties.getSpecificProperty("invalidname"))

        def neededuserName = GetProperties.getSpecificProperty("invalidname")

        then: "if the user is found or not"
        logger.info("we check if the user is found or not")
        logger.info("Expected username is " +neededuserName)
        neededInvalidUser == null
        logger.info("user is not found")

        where: "a set of other parameters"
        responseStatus | scenarioSerial
        200 | scenarioSerial

    }


    @Unroll
    def " (#scenarioSerial) searching for Delphine's posts"() {
        given: "we know the user ID from previous cases"

        when: " We search for the user"
        logger.info ("We take the userId from the previous case")
        def neededUserId = neededUser.id

        logger.info ("----------------------------requesting the API for the Posts----------------------------")
        and: "we check for the posts of the user"

        neededposts = GetPosts.getSpecificPosts(neededUserId)



        then: "we check if the response is correct or not"


        where: "a set of other parameters"
        responseStatus | scenarioSerial
        200 | scenarioSerial

    }




}
