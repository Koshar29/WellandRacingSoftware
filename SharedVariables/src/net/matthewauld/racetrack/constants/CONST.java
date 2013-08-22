package net.matthewauld.racetrack.constants;

import java.awt.Color;
import java.awt.Font;

public class CONST {
	public static final Font default_font = new Font(Font.SANS_SERIF, Font.PLAIN, 12), header = new Font("Arial", Font.BOLD, 18);
	public static final String seperator = "~";
	public static final int PORT = 59678, CONNECTED = 0, DISCONNECT = 1, CHAT = 2, LOGIN = 3, LOGOUT = 4, COUNT_UPDATE = 5, RIDER_DATA_SEARCH = 6, CLASSES_DATA_SEARCH = 7, GET_CLASS_RIDERS = 8;
	public static final int GET_RIDER_INFO = 9, SIGN_IN_RIDER = 10, SIGN_OUT_RIDER = 11, CLEAR_ATTENDANCE = 12, GET_CLASS_STANDING = 13,GET_RACE_DATES = 14;
	public static final Color onlineStatus = new Color(0, 102, 0), offlineStatus = new Color(204, 0, 0);
}
