package com.wcmc.Software.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.matthewauld.controls.Button;
import net.matthewauld.controls.Control;
import net.matthewauld.controls.Textbox;
import net.matthewauld.racetrack.constants.CONST;

import com.wcmc.Software.Client;
import com.wcmc.network.Encrypt;

public class LoginScreen implements Screen {
	private int WIDTH = 825, HEIGHT = 300;
	private boolean isActive = true;
	private String errorMessage = "";
	private String name = "LOGINSCREEN";
	private ArrayList<Control> controls = new ArrayList<Control>();
	private Button btnExit = new Button(), btnLogin = new Button();
	private Textbox txtEmail = new Textbox(), txtPassword = new Textbox();

	public LoginScreen() {
		reloadControls();
	}

	@Override
	public void reloadControls() {
		btnExit.setLocation(720, 265);
		btnExit.setText("Exit");
		controls.add(btnExit);
		btnLogin.setText("Login");
		btnLogin.setLocation(615, 265);
		controls.add(btnLogin);
		txtEmail.setWidth(300);
		txtEmail.setLocation((Client.WIDTH / 2) - (txtEmail.getWidth() / 2), Client.HEIGHT / 2 - 50);
		controls.add(txtEmail);
		txtPassword.setWidth(300);
		txtPassword.setLocation((Client.WIDTH / 2) - (txtEmail.getWidth() / 2), Client.HEIGHT / 2);
		txtPassword.setPassword(true);
		controls.add(txtPassword);
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (Client.assets.logo != null) {
			g.drawImage(Client.assets.logo, 0, 0, null);
		}
		g2.setColor(Color.BLACK);
		String txt = "Username";
		g2.drawString(txt, (Client.WIDTH / 2) - (g.getFontMetrics().stringWidth(txt) / 2), (Client.HEIGHT / 2) - 56);
		txt = "Password";
		g2.drawString(txt, (Client.WIDTH / 2) - (g.getFontMetrics().stringWidth(txt) / 2), (Client.HEIGHT / 2) - 6);
		//Login Errors
		g2.setColor(CONST.offlineStatus);
		g2.drawString(errorMessage, (Client.WIDTH / 2) - (g.getFontMetrics().stringWidth(errorMessage) / 2), (Client.HEIGHT / 2) + 40);
		
		for (Control c : controls) {
			c.render(g);
		}
		
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void mouseClick(MouseEvent e) {
		if (btnExit.isClicked(e)) {
			System.exit(0);
		}

		if (btnLogin.isClicked(e)) {
			String loginString = CONST.LOGIN + " " + txtEmail.getText().toString() + CONST.seperator + Encrypt.md5(txtPassword.getText().toString());
			System.out.println(loginString);
			Client.sc.send(loginString);
		}
		for (Control c : controls) {
			c.mouseClicked(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (Control c : controls) {
			c.keyTyped(e);
		}
	}

	@Override
	public void mouseMove(MouseEvent e) {
		for (Control c : controls) {
			c.mouseMoved(e);
		}
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setError(String string) {
		this.errorMessage = string;
	}
}
