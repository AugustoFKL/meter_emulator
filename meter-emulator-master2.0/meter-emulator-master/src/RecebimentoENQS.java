import java.io.IOException;
import java.net.Socket;

public class RecebimentoENQS {
    public static void main(String[] args) {
        try {
            Socket aberturasocket = new Socket("localhost", 3333);
            int flag = 0;
            while (true) {
                if (aberturasocket.getInputStream().available() > 0) {
                    byte[] bytea = new byte[aberturasocket.getInputStream().available()];
                    aberturasocket.getInputStream().read(bytea);
                    for (byte b :
                            bytea) {
                        if (b == 5) {
                            flag = flag + 1;
                        }
                    }
                    if (flag == 3) {
                        System.out.println("Recebi " + flag + " ENQs");
                        EnvioCOD.start(aberturasocket);
                        break;
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
