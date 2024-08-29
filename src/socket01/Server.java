package socket01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        // 소켓 통신용으로 무조건 켜져있어야 사용 가능

        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("소켓 서버 실행 완료.");

            while(true){
                // 소켓 서버 접속 허용 처리.
                Socket socket = serverSocket.accept();

                /*      출력부분 처리(상세)           */
//                InputStream is = socket.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is);
//                BufferedReader br = new BufferedReader(isr);

                /*      출력부분 처리(한줄요약)           */
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                /*      출력부분 처리(상세)           */
//                OutputStream os = socket.getOutputStream();
//                PrintWriter pw =  new PrintWriter(os, true);

                /*      출력부분 처리(한줄요약)           */
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("클라이언트가 접속했당.");

//                String msg = in.readLine();
                String msg ;
                while ((msg = in.readLine()) != null) {
                    System.out.println("클라이언트 대화글 : " + msg );
                    out.println("서버에서 전송 받은 글 : " + msg);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
