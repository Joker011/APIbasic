package Pojo;

import java.util.ArrayList;
import java.util.List;

public class SetData {

    public Gmaps getGmaps()
    {
        Gmaps gmaps = new Gmaps();
        gmaps.setAccuracy(50);
        gmaps.setName("Frontline house");
        gmaps.setPhone_number("(+91) 983 893 3937");
        gmaps.setAddress("Maharashtra");
        gmaps.setWebsite("http://google.com");
        gmaps.setLanguage("French-IN");
        List<String> listOFTypes = new ArrayList<>();
        listOFTypes.add("shoe park");
        listOFTypes.add("shop");
        gmaps.setTypes(listOFTypes);
        location location = new location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        gmaps.setlocation(location);
        return gmaps;
    }

    public Gmaps getGmaps(String name,String phone,String address)
    {
        Gmaps gmaps = new Gmaps();
        gmaps.setAccuracy(50);
        gmaps.setName(name);
        gmaps.setPhone_number(phone);
        gmaps.setAddress(address);
        gmaps.setWebsite("http://google.com");
        gmaps.setLanguage("French-IN");
        List<String> listOFTypes = new ArrayList<>();
        listOFTypes.add("shoe park");
        listOFTypes.add("shop");
        gmaps.setTypes(listOFTypes);
        location location = new location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        gmaps.setlocation(location);
        return gmaps;
    }

    public updateAddress updateAddress(String key, String place_id)
    {
        updateAddress updateAddress = new updateAddress();
        updateAddress.setAddress("A new Address, MH");
        updateAddress.setKey(key);
        updateAddress.setPlace_id(place_id);

        return updateAddress;
    }
}
