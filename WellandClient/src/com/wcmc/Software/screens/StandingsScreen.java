package com.wcmc.Software.screens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.matthewauld.controls.Button;
import net.matthewauld.controls.Control;

public class StandingsScreen implements Screen {
	private ArrayList<Control> controls = new ArrayList<Control>();
	private boolean isActive = false;
	private Button btnHeats = null, btnFinals = null, btnResults = null;

	public StandingsScreen() {
		reloadControls();
	}

	@Override
	public void reloadControls() {
		controls.clear();

		btnHeats = new Button();
		btnHeats.setText("Heats");
		btnHeats.setLocation(8, 90);
		controls.add(btnHeats);

		btnFinals = new Button();
		btnFinals.setText("Finals");
		btnFinals.setLocation(115, 90);
		controls.add(btnFinals);

		btnResults = new Button();
		btnResults.setText("Results");
		btnResults.setLocation(222, 90);
		controls.add(btnResults);

	}

	@Override
	public void render(Graphics g) {
		for (Control c : controls) {
			c.render(g);
		}
		// reloadControls();
	}

	@Override
	public void mouseMove(MouseEvent e) {
		for (Control c : controls) {
			c.mouseMoved(e);
		}
	}

	@Override
	public void mouseClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
