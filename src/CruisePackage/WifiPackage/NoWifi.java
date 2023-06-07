package CruisePackage.WifiPackage;

public class NoWifi implements WifiPackage{

    private String wifi;

    public NoWifi(){
        this.wifi = "No Wifi";
    }

    @Override
    public String wifiStrength() {
        return this.wifi;
    }

}
