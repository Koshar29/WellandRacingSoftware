package net.matthewauld.racetrack.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

import net.matthewauld.racetrack.constants.CONST;

public class ClientListener extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int localport, id;
	private String displayName = "";

	public ClientListener(Socket cs) {
		this.clientSocket = cs;
		try {
			this.clientSocket.setKeepAlive(true);
		} catch (SocketException e1) {

		}
		this.localport = cs.getPort();
		try {
			out = new PrintWriter(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {

		}

	}

	@Override
	public void run() {
		try {
			String line;
			while ((line = in.readLine()) != null) {
				parseCommand(line);
			}
		} catch (IOException e) {
			Server.sendToAll(CONST.DISCONNECT + " " + this.localport);
			System.out.println("Client " + this.localport + " has disconnected");
			Server.clients.remove(this);
			Server.sendToAll(CONST.COUNT_UPDATE + " " + Server.clients.size());

		}
	}

	public void parseCommand(String command) {
		String sql = "";
		String riderJSONString = "NULL";
		String classRidersData = "NULL";
		String jsonString = "NULL";
		String[] args = null;
		WrSQL wSQL = null;
		int code = -1;
		if (command.contains(" ")) {
			code = Integer.parseInt(command.substring(0, command.indexOf(" ")));
		} else {
			code = Integer.parseInt(command);
		}
		if (code != 6)
			System.out.println("IN: " + command);

		switch (code) {
		case CONST.LOGIN:
			String[] strings = (String[]) getArgs(command.substring(command.indexOf(" ") + 1));
			if (strings.length == 2) {
				String username = strings[0];
				String password = strings[1];
				checkLogin(username, password);
			}
			break;
		case CONST.RIDER_DATA_SEARCH:
			if (command.contains(CONST.seperator)) {
				@SuppressWarnings("unused")
				String[] argss = (String[]) getArgs(command);
			} else {
				String arg = (String) getArgs(command);
				sql = "SELECT * FROM riders WHERE first_name LIKE ? OR last_name LIKE ?";
				wSQL = new WrSQL();
				String[] sArgs = { "%" + arg + "%", "%" + arg + "%" };
				String riderData = "NULL";
				try {
					riderData = wSQL.getJSONString(sql, sArgs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				send(CONST.RIDER_DATA_SEARCH + " " + riderData);
			}
			break;
		case CONST.CLASSES_DATA_SEARCH:
			sql = "SELECT * FROM classes WHERE `active` = '1' ORDER BY `order` ASC";
			wSQL = new WrSQL();
			String classData = "NULL";
			try {
				classData = wSQL.getJSONClasses(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			send(CONST.CLASSES_DATA_SEARCH + " " + classData);
			break;
		case CONST.GET_CLASS_RIDERS:
			String arg = (String) getArgs(command);
			sql = "SELECT * FROM riders WHERE EXISTS(SELECT * FROM ridersclasses WHERE ridersclasses.rid = riders.id AND cid = '" + arg + "') ORDER BY first_name ASC";
			wSQL = new WrSQL();
			try {
				classRidersData = wSQL.getJSONClassSpecificRiders(sql, arg);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			send(CONST.GET_CLASS_RIDERS + " " + arg + CONST.seperator + classRidersData);
			break;
		case CONST.GET_RIDER_INFO:
			String rid = (String) getArgs(command);
			wSQL = new WrSQL();
			riderJSONString = wSQL.getRiderInfo(Integer.parseInt(rid));
			send(CONST.GET_RIDER_INFO + " " + rid + CONST.seperator + riderJSONString);
			break;
		case CONST.SIGN_IN_RIDER:
			args = (String[]) getArgs(command);
			wSQL = new WrSQL();
			wSQL.signInRider(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			riderJSONString = wSQL.getRiderInfo(Integer.parseInt(args[0]));
			Server.sendToAll(CONST.GET_RIDER_INFO + " " + Integer.parseInt(args[0]) + CONST.seperator + riderJSONString);
			break;
		case CONST.SIGN_OUT_RIDER:
			args = (String[]) getArgs(command);
			wSQL = new WrSQL();
			wSQL.signOutRider(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			riderJSONString = wSQL.getRiderInfo(Integer.parseInt(args[0]));
			Server.sendToAll(CONST.GET_RIDER_INFO + " " + Integer.parseInt(args[0]) + CONST.seperator + riderJSONString);
			break;
		case CONST.GET_CLASS_STANDING:
			args = (String[]) getArgs(command);
			wSQL = new WrSQL();
			jsonString = wSQL.getClassStandings(Integer.parseInt(args[0]), args[1]);
			send(CONST.GET_CLASS_STANDING + " " + jsonString);
			break;
		case CONST.GET_RACE_DATES:
			args = (String[]) getArgs(command);
			wSQL = new WrSQL();
			jsonString = wSQL.getRaceDates(args[0], Integer.parseInt(args[1]));
			send(CONST.GET_RACE_DATES + " " + jsonString);
			break;
		}
	}

	private Object getArgs(String command) {
		String arg = command.substring(command.indexOf(" ") + 1);
		if (arg.contains(CONST.seperator)) {
			String[] args = arg.split(CONST.seperator);
			return args;
		} else {
			return arg;
		}
	}

	public void send(Object line) {
		if (clientSocket.isConnected() && out != null && line != null) {
			out.println(line.toString());
			out.flush();
		}
	}

	public String getDisplayName() {
		return this.displayName;
	}

	private void checkLogin(String username, String password) {
		System.out.println("Verifying Login: " + username + " - " + password);
		WrSQL wSQL = new WrSQL();
		String results = wSQL.fetchQuery("SELECT `id` FROM `accounts` WHERE `username` = '" + username + "' AND `password` = '" + password + "'");
		// System.out.println(results);
		if (results == null) {
			send(CONST.LOGIN + " 0" + CONST.seperator + "Invalid Login");
		} else {
			this.id = Integer.parseInt(results);
			send(CONST.LOGIN + " " + this.id);
		}
		wSQL.disconnect();
	}
}
