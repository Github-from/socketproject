import java.io.*;
import java.net.*;

public class calculatorserver {
    public static void main(String[] args) {
        try(ServerSocket serverSocket=new ServerSocket(30699)){
            System.out.println("Calculator server is running on port 30699..");
            while(true){
                Socket clientSocket=serverSocket.accept();
                System.out.println("Client connected: "+clientSocket);
                BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);

                String operation = in.readLine();
                System.out.println("Recieved operation: "+operation);

                String result=calculate(operation);
                out.println(result);
                clientSocket.close();
            }
        }catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
    private static String calculate(String operation){
        try{
            String[] parts=operation.split(" ");
            if (parts.length != 3){
                return "Invalid format, use: number 1 operator number2 (e.g., 5+3)";
            }
            double num1=Double.parseDouble(parts[0]);
            String operator=parts[1];
            double num2=Double.parseDouble(parts[2]);
            switch (operator){
                case "+":
                    return "Result: "+(num1+num2);
                case "-":
                    return "Result: "+(num1-num2);
                case "*":
                    return "Result: "+(num1*num2);
                case "/":
                    if (num2==0) return "Error: Division by zero";
                    return "Result: "+(num1/num2);
                default:
                    return "Invalid opeartor. use +,-,* or /";
            }
        }catch (NumberFormatException e){
            return "Invalid numbers.use: number1 opertor number2";
        }
    }
}
