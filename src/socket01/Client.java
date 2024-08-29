package socket01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("192.168.0.9",9000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread t1 = new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e){
                    System.out.println("서버 통신 오류");
                }
            });
            t1.start();
            System.out.println("서버 접속 성공 !!");
            Scanner sc = new Scanner(System.in);

            while (true){
                String msg = sc.nextLine();

                System.out.println("서버에 보낼 대화글 : " + msg);
                out.println(msg);

                String result = in.readLine();
                System.out.println("서버에서 응답 받은 대화글 : " + result);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
