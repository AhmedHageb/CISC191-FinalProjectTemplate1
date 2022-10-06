package edu.sdccd.cisc191.Server;


public class Server {

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.start();
        CLI console = new CLI();
        console.start();
    }
} //end class Server
