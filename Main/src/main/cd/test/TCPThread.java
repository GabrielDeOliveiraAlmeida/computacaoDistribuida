package main.cd.test;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPThread extends Thread{

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Socket clientConnecting;
    boolean valid = false;

    public TCPThread(Socket clientConnecting) {
        try {
            this.clientConnecting = clientConnecting;
            this.objectInputStream = new ObjectInputStream(clientConnecting.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(clientConnecting.getOutputStream());
            valid = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isValid() {
        return valid;
    }

    public void info(Object message){
        System.out.println("[Thread - " + clientConnecting.getInetAddress().getHostAddress() +":" + clientConnecting.getPort() + "]: " + message.toString());
    }

    @Override
    public void run() {
        try {
            while(true) {
                Object object = objectInputStream.readObject();
                info("Objeto lido: ");
                if (object == null){
                    info("VALO NULO\n");
                    continue;
                }
                if ( !(object instanceof String)){
                    info("Objeto recebido não é válido!");
                    continue;
                }
                info(object);
                String message = (String) object;

                ServerSideTCP.lastMessage = message;
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
