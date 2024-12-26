import java.io.*;
import java.net.*;
import java.util.Scanner;

public class calculatorclient {
    public static void main(String[] args) {
        try(Socket socket=new Socket("localhost",30699);
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner=new Scanner(System.in)){
            System.out.println("Connected to the calculator server!");
            System.out.println("Enter a mathematical operation: ");
            while(true){
                System.out.println("Your operation: ");
                String input=scanner.nextLine();
                if(input.equalsIgnoreCase("exit")){
                    System.out.println("Exiting...");
                    break;
                }
                out.println(input);
                String result=in.readLine();
                System.out.println(result);
            }
        }catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
