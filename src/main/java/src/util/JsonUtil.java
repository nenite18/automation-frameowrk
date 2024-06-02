package src.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import src.logger.LoggerUtil;
import src.pojo.Todos;
import src.pojo.User;

/**
 * Utility class for handling JSON data.
 * Provides methods for converting JSON strings to User and Todos objects.
 */
public class JsonUtil {
    private static final LoggerUtil logger = LoggerUtil.getLogger(JsonUtil.class);

    /**
     * Converts a JSON string to an array of User objects.
     *
     * @param userResponse the JSON string to convert
     * @return an array of User objects, or null if an error occurs
     */
    public User[] getUsers(String userResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(userResponse, User[].class);
        } catch (Exception e) {
            logger.logError("Error parsing user response: " + e.getMessage());
            return null;
        }
    }

    /**
     * Converts a JSON string to an array of Todos objects.
     *
     * @param todosResponse the JSON string to convert
     * @return an array of Todos objects, or null if an error occurs
     */
    public Todos[] getTodos(String todosResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(todosResponse, Todos[].class);
        } catch (Exception e) {
            logger.logError("Error parsing todos response: " + e.getMessage());
            return null;
        }
    }
}