package CruisePackage.WifiPackage;

public class NoWifi implements WifiPackage{

    @Override
    public String wifiStrength() {
        return "No Wifi";
    }
}
