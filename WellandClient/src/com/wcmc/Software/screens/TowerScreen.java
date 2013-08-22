package com.wcmc.Software.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.matthewauld.controls.ComboBox;
import net.matthewauld.controls.Control;
import net.matthewauld.controls.Textbox;

public class TowerScreen implements Screen {
	private ArrayList<Control> controls = new ArrayList<Control>();
	private boolean isActive = false;

	public TowerScreen() {
		reloadControls();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		for (Control c : controls) {
			c.render(g);
		}
		reloadControls();
	}

	@Override
	public void reloadControls() {
		controls.clear();

		Textbox txtClass = new Textbox();
		txtClass.setLocation(5, 85);
		txtClass.setWidth(175);
		controls.add(txtClass);

		ComboBox cmbClass = new ComboBox();
		cmbClass.setLocation(5, 110);
		cmbClass.setWidth(175);
		controls.add(cmbClass);
	}

	@Override
	public void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub

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
