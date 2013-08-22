package com.wcmc.Software;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import net.matthewauld.graphics.Colors;
import net.matthewauld.racetrack.constants.CONST;

import com.wcmc.Software.resources.Assets;
import com.wcmc.Software.screens.LoginScreen;
import com.wcmc.Software.screens.MenuScreen;
import com.wcmc.Software.screens.Screen;
import com.wcmc.network.ServerConnection;

public class Client extends Applet implements MouseInputListener, KeyListener {
	private static final long serialVersionUID = -3332883133718794696L;
	private static Image backBuffer;
	private Graphics bg;
	private static ArrayList<Screen> screens = new ArrayList<Screen>();
	public static LoginScreen ls;
	public static MenuScreen ms;
	public static ServerConnection sc;
	private static Point start_drag, start_loc;

	public static int clientCount = 0;
	public static final String IP = "127.0.0.1";
	// public static final String IP = "192.168.1.102";
	public static Assets assets = new Assets();
	public static JFrame window;
	public static int WIDTH = 826, HEIGHT = 300;
	public static boolean isConnected, isLoggedIn = false, canDrag = true;
	@SuppressWarnings("unused")
	private static String id;

	public static void main(String[] args) throws UnknownHostException {
		window = new JFrame("Welland County Motorcycle Club");
		window.setSize(WIDTH, HEIGHT);

		Applet app = new Client();
		window.add(app);
		app.init();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setUndecorated(true);

		window.setAlwaysOnTop(true);

		window.setVisible(true);

		retryConnection();
	}

	public void init() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		Thread gt = new Thread() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(33);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		gt.setName("Graphics Thread");
		gt.start();
		assets.start();
		ls = new LoginScreen();
		ls.setActive(true);
		screens.add(ls);
	}

	public void update(Graphics g) {
		if (backBuffer == null) {
			backBuffer = createImage(WIDTH, HEIGHT);
			bg = backBuffer.getGraphics();
		}

		bg.setColor(Colors.softwareColor);
		bg.fillRect(0, 0, WIDTH, HEIGHT);

		paint(bg);

		Graphics2D g2 = (Graphics2D) bg.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Colors.white50percent);
		g2.drawString("Auld Racing Software", 5, 17);
		g2.setColor(Color.BLACK);
		g2.drawString("Auld Racing Software", 5, 16);

		bg.setColor(Colors.borderColor);
		bg.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
		bg.setColor(Colors.white50percent);
		bg.drawRect(1, 1, WIDTH - 3, HEIGHT - 3);
		String txt = "";
		if (Client.isConnected) {
			// Set Color
			g2.setColor(Colors.white50percent);
			g2.fillArc(10, Client.HEIGHT - 17, 10, 10, 0, 360);
			txt = "Connected To Server";
			g2.drawString(txt, 22, Client.HEIGHT - 7);
			txt = " - Clients: " + clientCount;
			g2.drawString(txt, 138, Client.HEIGHT - 7);

			g2.setColor(CONST.onlineStatus);
			g2.fillArc(10, Client.HEIGHT - 18, 10, 10, 0, 360);
			txt = "Connected To Server";
			g2.drawString(txt, 22, Client.HEIGHT - 8);

			txt = " - Clients: " + clientCount;
			g2.drawString(txt, 138, Client.HEIGHT - 8);
		} else {
			// Set Color
			g2.setColor(Colors.white50percent);
			g2.fillArc(10, Client.HEIGHT - 17, 10, 10, 0, 360);
			txt = "Server Offline";
			g2.drawString(txt, 22, Client.HEIGHT - 7);

			g2.setColor(CONST.offlineStatus);
			g2.fillArc(10, Client.HEIGHT - 18, 10, 10, 0, 360);
			g2.drawString(txt, 22, Client.HEIGHT - 8);
		}
		g2.dispose();

		g.drawImage(backBuffer, 0, 0, this);
	}

	public void paint(Graphics g) {
		try {
			for (Screen s : screens) {
				if (s.isActive()) {
					s.render(g);
				}
			}
		} catch (Exception e) {
			// Nothing to see here
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (canDrag) {
			start_drag = getScreenLocation(e);
			start_loc = window.getLocation();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		for (Screen s : screens) {
			if (s.isActive()) {
				s.mouseClick(e);
			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getY() <= 25 && canDrag) {
			Point current = getScreenLocation(e);
			Point offset = new Point((int) current.getX() - (int) start_drag.getX(), (int) current.getY() - (int) start_drag.getY());

			Point new_location = new Point((int) (start_loc.getX() + offset.getX()), (int) (start_loc.getY() + offset.getY()));
			window.setLocation(new_location);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Screen s : screens) {
			if (s.isActive()) {
				s.mouseMove(e);
			}
		}
	}

	public static void retryConnection() {
		sc = new ServerConnection();
		sc.setName("Server Connection");
		sc.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (Screen s : screens) {
			if (s.isActive()) {
				s.keyTyped(e);
			}
		}
	}

	static Point getScreenLocation(MouseEvent e) {
		Point cursor = e.getPoint();
		Point target_location = window.getLocationOnScreen();
		return new Point((int) (target_location.getX() + cursor.getX()), (int) (target_location.getY() + cursor.getY()));
	}

	public static void login(String id) {
		Client.id = id;
		Client.isLoggedIn = true;
		ls.setActive(false);
		ms = new MenuScreen();

		screens.add(ms);
		window.setLocation(0, 0);
		Toolkit tk = Toolkit.getDefaultToolkit();
		WIDTH = tk.getScreenSize().width;
		HEIGHT = tk.getScreenSize().height - tk.getScreenInsets(window.getGraphicsConfiguration()).bottom;

		window.setLocation((WIDTH / 2) - 512, (HEIGHT / 2) - 374);
		WIDTH = 1024;
		HEIGHT = 748;

		window.setSize(WIDTH, HEIGHT);
		backBuffer = null;
		ms.setActive(true);
	}
}
