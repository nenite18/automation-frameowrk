package src.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import src.logger.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

public class HttpClient {
    private static final LoggerUtil logger = LoggerUtil.getLogger(HttpClient.class);

    public static class HttpRequestBuilder {
        private String baseurl;
        private String endpoint;
        private Map<String, String> cookies;
        private Map<String, Object> queryPara;

        public HttpRequestBuilder withBaseurl(String baseurl) {
            this.baseurl = baseurl;
            return this;
        }

        public HttpRequestBuilder withEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public HttpRequestBuilder withCookies(Map<String, String> cookies) {
            this.cookies = cookies;
            return this;
        }

        public HttpRequestBuilder withQueryPara(String key, Object value) {
            if (queryPara == null) {
                queryPara = new HashMap<>();
            }
            queryPara.put(key, value);
            return this;
        }

        public Response build() {
            Response response = given()
                    .baseUri(baseurl)
                    .basePath(endpoint)
                    .when()
                    .get();

            logger.logInfo("GET request to " + baseurl + endpoint + " returned status code " + response.getStatusCode());
            return response;
        }
    }


    /**
     * Sends a GET request to the specified URL.
     *
     * @param baseurl  the base URL
     * @param endpoint the endpoint
     * @return the server's response
     */
    public static Response get(String baseurl, String endpoint) {
        return new HttpRequestBuilder()
                .withBaseurl(baseurl)
                .withEndpoint(endpoint)
                .build();
    }

    /**
     * Sends a GET request to the specified URL with the specified cookies.
     *
     * @param baseurl  the base URL
     * @param endpoint the endpoint
     * @param cookies  the cookies to include in the request
     * @return the server's response
     */
    public static Response get(String baseurl, String endpoint, Map<String, String> cookies) {
        return new HttpRequestBuilder()
                .withBaseurl(baseurl)
                .withEndpoint(endpoint)
                .withCookies(cookies)
                .build();
    }

    /**
     * Sends a GET request to the specified URL with the specified query parameter.
     *
     * @param baseurl  the base URL
     * @param endpoint the endpoint
     * @param key      the key of the query parameter
     * @param value    the value of the query parameter
     * @return the server's response
     */
    public static Response get(String baseurl, String endpoint, String key, int value) {
        return new HttpRequestBuilder()
                .withBaseurl(baseurl)
                .withEndpoint(endpoint)
                .withQueryPara(key, value)
                .build();
    }
}