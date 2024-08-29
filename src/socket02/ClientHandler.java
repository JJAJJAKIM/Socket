package socket02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import file.Note;

public class ClientHandler implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private StringBuffer history;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        history =  Note.in();
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            synchronized (socket) {
                MultiClientServer.clientWriters.add(out);
            }
            out.println(history.toString());
            String msg;
            while((msg = in.readLine()) != null) {
                System.out.println("클라이언트 대화글 : " + msg);
//				out.println("서버에 전송 받은 글 :" + msg);
                broadcastMessage("서버에 전송 받은 글 :" + msg);
                Note.out("서버에 전송 받은 글 :" + msg);
            }
        } catch (IOException e) {
//			e.printStackTrace();
        } finally {
            try {
                synchronized (MultiClientServer.clientWriters) {
                    MultiClientServer.clientWriters.remove(out);
                }
                socket.close();
                System.out.println("클라이언트가 접속을 종료 하였습니다.");
            } catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("클라이언트가 접속을 종료 하였습니다.");
            }
        }
    }

    private void broadcastMessage(String message) {
        synchronized (MultiClientServer.clientWriters) {
            for (PrintWriter writer : MultiClientServer.clientWriters) {
                writer.println(message);
            }
        }
    }

}