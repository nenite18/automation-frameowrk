import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.response.Response;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import src.client.HttpClient;
import src.config.Config;
import src.config.Endpoints;
import src.logger.LoggerUtil;
import src.pojo.Todos;
import src.pojo.User;

public class FancodeTest extends BaseTest {
    private static LoggerUtil logger = LoggerUtil.getLogger(FancodeTest.class);

    @Test
    public void testFanCodeCityUser() throws Exception {
        String queryParam = "userID";
        ExtentTest test = extentReport.createTest("FanCode City User Test", "Tests user data and todos in FanCode city");

        Response userResponse = HttpClient.get(Config.BASE_URL, Endpoints.USERS);

        Assert.assertEquals(userResponse.getStatusCode(), HttpStatusCode.SUCCESS.getCode(), "User response status code is not 200");
        test.log(Status.PASS, "User response status code is 200");

        User[] users = jsonUtil.getUsers(userResponse.asString());
        Assert.assertNotNull(users, "User response is null");

        Assert.assertTrue(users.length > 0, "No users found in response");
        test.log(Status.PASS, "Users found in response");

        for (User user : users) {
            double userLat = user.getAddress().getGeo().getLat();
            double userLng = user.getAddress().getGeo().getLng();

            if (helper.checkFanCodeCity(userLat, userLng)) {

                Response todosResponse = HttpClient.get(Config.BASE_URL, Endpoints.TODOS, queryParam, user.getId());

                Assert.assertEquals(todosResponse.getStatusCode(), HttpStatusCode.SUCCESS.getCode(), "Todo response status code is not 200");
                test.log(Status.PASS, "Todo response status code is 200");

                logger.logInfo("Todos response for user " + user.getName() + " is " + todosResponse.asString());


                Todos[] todos = jsonUtil.getTodos(todosResponse.asString());

                Assert.assertNotNull(todos, "Todos response is null");

                Assert.assertTrue(todos.length > 0, "No todos found in response");

                int totalTask = todos.length;
                int completedTask = 0;

                for (Todos todo : todos) {
                    logger.logInfo("Task " + todo.getId() + " for user :" + user.getName() + " is " + (todo.isCompleted() ? "completed" : "not completed"));

                    if (todo.isCompleted()) {
                        completedTask++;
                    }
                }

                logger.logInfo("Total completed task for user " + user.getName() + " is " + completedTask);

                int passPercentage = (completedTask * 100) / totalTask;

                if(passPercentage > 50){
                    test.log(Status.PASS, "User " + user.getName() + " has completion percentage greater than 50%");
                } else {
                    test.log(Status.FAIL, "User " + user.getName() + " has completion percentage less than 50%");
                }


            } else {
                logger.logInfo("User " + user.getName() + " is not in FanCode city");
            }
        }

    }
}
