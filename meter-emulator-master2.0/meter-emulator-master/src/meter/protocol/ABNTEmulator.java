package meter.protocol;

import util.ByteArrayUtils;

import java.io.IOException;
import java.net.Socket;

public class ABNTEmulator {
    private final int ENQ = 0X05;
    private final byte[] EXPECTED_PACKET = {0x21, 0x00, 0x01, 0x02, 0x03};

    public void handle(final Socket socket) throws Exception{
        if(socket.getInputStream().available() > 0 ){
            Thread.sleep(10);
            final byte[] toRead = new byte[socket.getInputStream().available()];
            socket.getInputStream().read(toRead);
            System.out.println("ABNTEmulator - RX: [" + ByteArrayUtils.byteToHex(toRead) + "]");
            process(toRead, socket);
        }else{
            socket.getOutputStream().write(ENQ);
            Thread.sleep(500);
        }
    }

    protected void process(final byte[] packet, final Socket socket) throws IOException {
        if(ByteArrayUtils.equals(packet,EXPECTED_PACKET)){
            socket.getOutputStream().write("Parabéns, você chegou até aqui".getBytes());
        }
    }
}
