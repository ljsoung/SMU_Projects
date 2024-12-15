package basicProjectII.secondProject;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    Socket mySocket= null;
    public static void main(String[] args) {
        Client client = new Client();
        MessageListener msgListener = null;
        OutputStream outStream = null;
        DataOutputStream dataOutStream = null;
        Scanner scn = new Scanner(System.in);

        try {

            client.mySocket = new Socket("localhost", 1702); //만약 서버가 원격에 위치하면 해당 서버컴퓨터의 IP주소 사용 필요
            System.out.println("Client> 서버로 연결되었습니다.");
            msgListener = new MessageListener(client.mySocket);
            msgListener.start();

            outStream = client.mySocket.getOutputStream();
            dataOutStream = new DataOutputStream(outStream);
            Thread.sleep(100);
            while(true) {
                String msg = null;
                System.out.print("Client(Enter Message)> ");
                msg = scn.nextLine();
                dataOutStream.writeUTF(msg);

            }

        }catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MessageListener extends Thread{
    Socket socket;
    InputStream inStream;
    DataInputStream dataInStream;

    MessageListener(Socket _s){
        this.socket = _s;

    }

    public void run() {
        try {
            inStream = this.socket.getInputStream();
            dataInStream = new DataInputStream(inStream);
            String msg = dataInStream.readUTF();
            System.out.println("Client> Server Sent: " + msg);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}