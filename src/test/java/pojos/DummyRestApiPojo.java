package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApiPojo {

   private String status;
   private String message;
   private DummyRestApiDataPojo data;

    public DummyRestApiPojo() {
    }

    public DummyRestApiPojo(String status, String message, DummyRestApiDataPojo data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DummyRestApiDataPojo getData() {
        return data;
    }

    public void setData(DummyRestApiDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DummyRestApiPojo{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
