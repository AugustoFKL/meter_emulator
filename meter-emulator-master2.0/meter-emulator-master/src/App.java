import meter.server.MeterServer;

import java.io.IOException;
import java.net.ServerSocket;

public class App {

    public static void main(String[] args) {
        try {
            final MeterServer meterServer = new MeterServer(3333);
            meterServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
