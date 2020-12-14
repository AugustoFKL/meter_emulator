package meter.server;

import meter.session.MeterSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MeterServer extends Thread {
    private final ServerSocket serverSocket;
    private boolean active = true;

    public MeterServer(final int port) throws IOException {
        System.out.println("MeterServer - Creating a ServerSocket...");
        this.serverSocket = new ServerSocket(port);
        System.out.println("MeterServer - ServerSocket created!");
    }

    protected ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void run() {
        while(isActive()){
            try {
                final Socket clientSocket = getServerSocket().accept();
                System.out.println("MeterServer - Someone opened a socket...");
                final MeterSession meterSession = new MeterSession(clientSocket);
                meterSession.start();
                System.out.println("MeterServer - MeterSession started...");
            } catch (final Exception e){
                e.printStackTrace();
                setActive(false);
            }
        }
    }
}
