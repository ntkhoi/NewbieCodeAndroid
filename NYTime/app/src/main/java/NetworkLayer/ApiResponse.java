package NetworkLayer;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dustin on 12/3/17.
 */



public class ApiResponse {
    @SerializedName("response")
    private JsonObject response;

    @SerializedName("status")
    private String status;

    public JsonObject getResponse() {
        if(null == response){
            return new JsonObject();
        }
        return response;
    }

    public String getStatus() {
        return status;
    }
}
