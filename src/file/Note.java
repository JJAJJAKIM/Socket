package file;

import jdk.jshell.SourceCodeAnalysis;

import java.io.*;
import java.util.Scanner;

public class Note {

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//
//        String txt = scanner.nextLine();
//        out(txt);

        in();
    }

    public static StringBuffer in() { // 대상 파일의 내용을 읽어 오는 메소드
            StringBuffer msg = new StringBuffer();
        try {
            /*         파일 로드 부분 처리(상세)         */
//            FileInputStream fis = new FileInputStream("src/text.txt");
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);

            /*         파일 로드 부분 처리(한줄 요약)         */
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src/text.txt")));
            int value = 0;
            while ((value = in.read()) != -1) {
//                System.out.print((char)value);
                msg.append((char) value);
            }
            in.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return msg;
    }

    public static void out(String txt) { // 대상 파일에 내용을 입력 하는 메소드
        System.out.println("전달 받은 내용 : " + txt);
        try {
            /*         파일 저장 부분 처리(상세)         */
//           FileOutputStream fos = new FileOutputStream("src/text.txt", false);
//           OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
//           BufferedWriter bw = new BufferedWriter(osw);

            /*         파일 저장 부분 처리(한줄 요약)         */
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/text.txt", true), "utf-8"));
            out.write(txt);
            out.write("\r\n");
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
