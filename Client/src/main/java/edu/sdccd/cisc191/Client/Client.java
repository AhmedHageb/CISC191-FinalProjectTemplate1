package edu.sdccd.cisc191.Client;

import edu.sdccd.cisc191.common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Thread thread;
    private Scanner reader = new Scanner(System.in);


    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public GradeBookResponse sendRequest(String className, double grade) throws Exception {
        //out.println(StudentRequest.toJSON(new StudentRequest(className, grade)));
        return GradeBookResponse.fromJSON(in.readLine());
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }



    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.startConnection("127.0.0.1", 4444);
           //System.out.println(client.sendRequest().toString());
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Client

