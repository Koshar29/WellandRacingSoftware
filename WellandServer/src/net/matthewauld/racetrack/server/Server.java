package net.matthewauld.racetrack.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import net.matthewauld.racetrack.constants.CONST;

public class Server {
	public static ServerSocket ss;
	public static ArrayList<ClientListener> clients = new ArrayList<ClientListener>();

	public static void main(String[] args) {
		try {
			ss = new ServerSocket(CONST.PORT);
			System.out.println("The server has been started.");
			System.out.println("The server is ready.");
			while (true) {
				Socket cs = ss.accept();
				ClientListener cl = new ClientListener(cs);
				cl.setName("Client: " + cs.getPort());
				cl.start();
				System.out.println("Client has connected from " + cs.getInetAddress().getHostAddress() + ":" + cs.getPort());
				sendToAll(CONST.CONNECTED + " " + cs.getPort());
				clients.add(cl);
				cl.send(CONST.CONNECTED);
				sendToAll(CONST.COUNT_UPDATE + " " + clients.size());
			}
		} catch (IOException e) {
			System.out.println("Failed To Start Server.");
			System.exit(1);
		}
	}

	public static void sendToAll(Object line) {
		for (ClientListener cl : clients) {
			cl.send(line);
		}
	}
}
