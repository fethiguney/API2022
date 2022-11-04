package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String, String> bookingDatesMethod(String checkin, String checkout){
            Map<String, String> bookingDatesMap=new HashMap<>();
            bookingDatesMap.put("checkin", checkin);
            bookingDatesMap.put("checkout", checkout);

        return bookingDatesMap;
    }

    public Map<String, Object> bookingMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates){
        Map<String, Object> bookingMap=new HashMap<>();
        bookingMap.put("firstname", firstname);
        bookingMap.put("lastname", lastname);
        bookingMap.put("totalprice", totalprice);
        bookingMap.put("depositpaid", depositpaid);
        bookingMap.put("bookingdates", bookingdates);

        return bookingMap;
    }
}
