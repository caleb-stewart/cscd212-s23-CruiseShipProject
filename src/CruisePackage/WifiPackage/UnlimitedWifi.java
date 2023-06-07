package CruisePackage.WifiPackage;

public class UnlimitedWifi implements WifiPackage{

    private String wifi;

    public UnlimitedWifi(){
        this.wifi = "Strong Wifi";
    }

    @Override
    public String wifiStrength() {
        return this.wifi;
    }
}
