package com.wcmc.Software.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class HomeScreen implements Screen{

	@Override
	public void reloadControls() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,100,100);
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
