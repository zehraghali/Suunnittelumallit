package Bridge;

public class SmartRemote extends BasicRemote {
    public SmartRemote(Device device) {
        super(device);
    }

    public void voiceControl(String command) {
        if (device.isEnabled()) {
            System.out.println("Executing voice command: " + command);
            if (command.equalsIgnoreCase("open youtube")) {
                System.out.println("Opening YouTube on SmartTV.");
            } else {
                System.out.println("Command not recognized.");
            }
        } else {
            System.out.println("Cannot execute command. Device is OFF.");
        }
    }
}
