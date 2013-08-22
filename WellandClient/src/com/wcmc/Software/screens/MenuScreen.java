package com.wcmc.Software.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.wcmc.Software.Client;

import net.matthewauld.controls.Control;
import net.matthewauld.controls.Tab;
import net.matthewauld.controls.TabContainer;
import net.matthewauld.graphics.Colors;

public class MenuScreen implements Screen {
	private boolean isActive = false;
	private ArrayList<Control> controls = new ArrayList<Control>();
	private TabContainer tC = new TabContainer();
	private ArrayList<Screen> tabScreens = new ArrayList<Screen>();
	public RegistrationScreen rS = null;
	public TowerScreen tS = null;
	public TreasurerScreen trS = null;
	public StandingsScreen sS = null;

	public MenuScreen() {

	}

	private void clearActiveScreen() {
		for (Screen s : tabScreens) {
			s.setActive(false);
		}
	}

	@Override
	public void reloadControls() {
		tC.setY(50);
		tC.setWidth(Client.WIDTH - 1);
		tC.setHeight(Client.HEIGHT - 51);
		tC.clear();

		Tab homeTab = new Tab();
		homeTab.setText("Home");
		homeTab.setActive(true);
		tC.add(homeTab);

		HomeScreen hS = new HomeScreen();
		hS.setActive(true);
		tabScreens.add(hS);

		Tab registrationTab = new Tab();
		registrationTab.setText("Registration");
		tC.add(registrationTab);

		rS = new RegistrationScreen();
		tabScreens.add(rS);

		Tab standingsTab = new Tab();
		standingsTab.setText("Heats/Finals/Results");
		tC.add(standingsTab);
		
		sS = new StandingsScreen();
		tabScreens.add(sS);
		
		Tab towerTab = new Tab();
		towerTab.setText("Tower");
		tC.add(towerTab);

		tS = new TowerScreen();
		tabScreens.add(tS);

		Tab treasureTab = new Tab();
		treasureTab.setText("Treasurer");
		tC.add(treasureTab);

		trS = new TreasurerScreen();
		tabScreens.add(trS);
		
		

		controls.add(tC);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Colors.softwareShadowColor);
		g.fillRect(0, 0, Client.WIDTH, 80);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Colors.closeButtonFill);
		g2.fillRect(Client.WIDTH - 23, 1, 21, 20);
		g2.setColor(Colors.closeButtonBorder);
		g2.drawRect(Client.WIDTH - 23, 1, 21, 20);

		if (Client.assets.closeButton != null) {
			g2.drawImage(Client.assets.closeButton, Client.WIDTH - 20, 4, null);
		}

		for (Control c : controls) {
			c.render(g);
		}

		for (Screen s : tabScreens) {
			if (s.isActive()) {
				s.render(g);
			}
		}
	}

	@Override
	public void mouseMove(MouseEvent e) {
		tC.mouseMoved(e);
		for (Screen s : tabScreens) {
			if (s.isActive()) {
				s.mouseMove(e);
			}
		}
	}

	@Override
	public void mouseClick(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		if (mx >= Client.WIDTH - 20 && mx <= Client.WIDTH && my >= 0 && my <= 20) {
			System.exit(0);
		}

		tC.mouseClicked(e);

		if (tC.getTabs().get(0).isClicked(mx - tC.getX(), my - tC.getY())) {
			clearActiveScreen();
			tabScreens.get(0).setActive(true);
		}
		if (tC.getTabs().get(1).isClicked(mx - tC.getX(), my - tC.getY())) {
			clearActiveScreen();
			tabScreens.get(1).setActive(true);
		}
		if (tC.getTabs().get(2).isClicked(mx - tC.getX(), my - tC.getY())) {
			clearActiveScreen();
			tabScreens.get(2).setActive(true);
		}
		if (tC.getTabs().get(3).isClicked(mx - tC.getX(), my - tC.getY())) {
			clearActiveScreen();
			tabScreens.get(3).setActive(true);
		}
		if (tC.getTabs().get(4).isClicked(mx - tC.getX(), my - tC.getY())) {
			clearActiveScreen();
			tabScreens.get(4).setActive(true);
		}

		for (Screen s : tabScreens) {
			if (s.isActive()) {
				s.mouseClick(e);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (Screen s : tabScreens) {
			if (s.isActive()) {
				s.keyTyped(e);
			}
		}
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
		if (this.isActive) {
			reloadControls();
		}
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public String getName() {
		return null;
	}

}
