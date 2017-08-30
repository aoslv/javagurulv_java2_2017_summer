package lv.javaguru.java2.console.businesslogic.api;

import java.util.List;

public class Response {

    private boolean success;
    private List<Error> errors;

    public static Response createSuccessResponse() {
        return new Response(true, null);
    }

    public static Response createFailResponse(List<Error> errors) {
        return new Response(false, errors);
    }

    public Response(boolean success, List<Error> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFail() {
        return !success;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
