package Bridge;

public class BridgePatternTest {
    public static void main(String[] args) {
        Device smartTV = new SmartTV();
        SmartRemote smartRemote = new SmartRemote(smartTV);

        smartRemote.power();
        smartRemote.volumeUp();
        smartRemote.volumeDown();
        smartRemote.channelUp();
        smartRemote.channelDown();

        ((SmartTV) smartTV).browseInternet();
        smartRemote.voiceControl("open YouTube");
        smartRemote.voiceControl("play music");
    }
}
