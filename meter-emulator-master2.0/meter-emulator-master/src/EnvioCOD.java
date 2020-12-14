import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EnvioCOD {
    public static void start(Socket aberturasocket) throws IOException, InterruptedException {
        final byte[] envio = new byte[]{0x21, 0x00, 0x01, 0x02, 0x03};
        while (true) {
            aberturasocket.getOutputStream().write(envio);
            Thread.sleep(1000);

            int len = aberturasocket.getInputStream().available();
            if (len > 1) {
                byte[] received = new byte[len];
                byte[] receivedCorrected = new byte[len];
                aberturasocket.getInputStream().read(received);
                int ReceivedENQS = 0;
                int i=0;
                for(byte b: received){
                    if(!(b == 5)){
                        receivedCorrected[i-ReceivedENQS] = received[i];
                    }
                    else{
                        ReceivedENQS = ReceivedENQS + 1;
                    }
                    i = i+1;
                }
                String s = new String(receivedCorrected, StandardCharsets.UTF_8);
                System.out.println(s);
                break;
            }
        }
    }
}
