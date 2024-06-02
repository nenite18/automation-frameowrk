package src.config;

import lombok.Getter;

@Getter
public enum HttpStatusCode {
    SUCCESS(200),
    CREATED(201),
    ACCEPTED(202),
    MOVED_PERMANENTLY(301),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    TOO_MANY_REQUESTS(429),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }

}
