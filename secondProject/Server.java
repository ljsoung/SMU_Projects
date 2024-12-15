package basicProjectII.secondProject;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;



public class Server {
    ServerSocket ss = null;
    ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>();
    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.ss = new ServerSocket(1702);                    //서버 소켓 생성
            System.out.println("Server> Server Socket is created....");
            while(true) {                                                         //서버 메인쓰레드 소켓생성 무한루프
                Socket socket = server.ss.accept();	                          //소켓생성
                ConnectedClient c = new ConnectedClient(socket);
                server.clients.add(c);
                c.start();
            }
        }catch(SocketException e){
            System.out.println("Server> 서버 종료");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //
        // 특정 조건에 의해 더이상 서버를 실행 할 필요가 없을 경우
        // 서버 소켓을 닫아야 함.
        // 예) server.ss.close();

    }
}

class ConnectedClient extends Thread{
    Socket socket;
    OutputStream outStream;
    DataOutputStream dataOutStream;
    InputStream inStream;
    DataInputStream dataInStream;

    ConnectedClient(Socket _s){
        this.socket = _s;

    }

    public void run() {
        try {
            System.out.println("Server> "+ this.socket.toString()+"에서의 접속이 연결되었습니다.");

            outStream = this.socket.getOutputStream();
            dataOutStream = new DataOutputStream(outStream);
            inStream = this.socket.getInputStream();
            dataInStream = new DataInputStream(inStream);

            dataOutStream.writeUTF("Welcome to This Server!!!!");
            while(true) {
                String msg = dataInStream.readUTF();
                System.out.println("Server> "+ this.socket.toString()+": " + msg);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        //더 이상 이 클라언트에 대한 연결을 유지할 필요가 없을 경우
        //소켓의 연결 해제 필요
        //예) this.socket.close();
    }
}
