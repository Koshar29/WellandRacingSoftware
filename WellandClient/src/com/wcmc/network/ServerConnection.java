package com.wcmc.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import net.matthewauld.racetrack.constants.CONST;

import com.wcmc.Software.Client;

public class ServerConnection extends Thread {
	private Socket cs;
	private PrintWriter out;
	private BufferedReader in;
	private String line = "", info = null;

	public void connect() throws UnknownHostException, IOException {
		cs = new Socket(Client.IP, CONST.PORT);
		in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
		out = new PrintWriter(cs.getOutputStream());
		Client.isConnected = true;
	}

	@Override
	public void run() {
		if (cs == null) {
			try {
				connect();
			} catch (IOException e1) {
				// System.out.println("Failed to connect to the server.");
				try {
					Thread.sleep(3000);
					Client.retryConnection();
				} catch (InterruptedException e) {
				}
				return;
			}
		}

		while (Client.isConnected) {
			try {
				while ((line = in.readLine()) != null) {
					parseCommand(line);
				}
			} catch (IOException e) {
				System.out.println("Lost Connection");
				Client.isConnected = false;
				break;
			}
		}
		Client.retryConnection();
	}

	private void parseCommand(String command) {
		int code = -1;
		if (command.contains(" ")) {
			code = Integer.parseInt(command.substring(0, command.indexOf(" ")));
		} else {
			code = Integer.parseInt(command);
		}

		String data = "";
		switch (code) {
		case CONST.CONNECTED:
			Client.isConnected = true;
			break;
		case CONST.COUNT_UPDATE:
			int clientCount = Integer.parseInt((String) getArgs(command));
			Client.clientCount = clientCount;
			break;
		case CONST.LOGIN:
			if (command.contains(CONST.seperator)) {
				String[] args = (String[]) getArgs(command);
				Client.ls.setError(args[1]);
			} else {
				Client.login(getArgs(command).toString());
			}
			break;
		case CONST.RIDER_DATA_SEARCH:
			data = command.substring(command.indexOf(" ") + 1);
			Client.ms.rS.setAutocompleteData(data);
			break;
		case CONST.CLASSES_DATA_SEARCH:
			data = command.substring(command.indexOf(" ") + 1);
			Client.ms.rS.setClassData(data);
			break;
		case CONST.GET_CLASS_RIDERS:
			String[] args = (String[]) getArgs(command);
			if (Client.ms.rS.getActiveClass() == Integer.parseInt(args[0])) {
				Client.ms.rS.setRidersData(args[1]);
			}
			break;
		case CONST.GET_RIDER_INFO:
			args = (String[]) getArgs(command);
			if (Client.ms.rS.currentRider == Integer.parseInt(args[0])) {
				Client.ms.rS.setRiderInfo(args[1]);
			}
			break;
		case CONST.GET_CLASS_STANDING:
			// args = (String[]) getArgs(command);
			data = command.substring(command.indexOf(" ") + 1);
			info = data;
			break;
		case CONST.GET_RACE_DATES:
			data = command.substring(command.indexOf(" ") + 1);
			System.out.println(data);
			info = data;
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

	public void send(Object classesDataSearch) {
		if (Client.isConnected) {
			// System.out.println("OUT: " + line);
			out.println(classesDataSearch.toString());
			out.flush();
		}
	}

	public String getInfo() {
		String tempInfo = info;
		info = null;
		return tempInfo;
	}
}
