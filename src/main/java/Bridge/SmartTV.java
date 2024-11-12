package Bridge;

// SmartTV Class
public class SmartTV implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("SmartTV is now ON.");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("SmartTV is now OFF.");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
            System.out.println("SmartTV volume set to " + volume);
        }
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("SmartTV channel set to " + channel);
    }

    public void browseInternet() {
        if (on) {
            System.out.println("Browsing the internet on SmartTV.");
        } else {
            System.out.println("Cannot browse internet. SmartTV is OFF.");
        }
    }
}
