package meter.session;

import meter.protocol.ABNTEmulator;
import util.ByteArrayUtils;

import java.net.Socket;
import java.net.SocketException;

public class MeterSession extends Thread{
    private final Socket socket;
    private final ABNTEmulator protocolEmulator;

    public MeterSession(final Socket socket){
        this.socket = socket;
        this.protocolEmulator = new ABNTEmulator();
    }

    protected Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        try {
            while(socket.isConnected()){
                protocolEmulator.handle(socket);
                sleep(50);
            }
        } catch (final SocketException e){
        } catch (final Exception e){
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (final Exception e){}
            System.out.println("MeterSession - Socket closed");
        }
    }

    protected void handleReceivedPacket(final byte[] packet){

    }

}
