package com.wcmc.Software.screens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Screen {
	public void reloadControls();

	public void render(Graphics g);

	public void mouseMove(MouseEvent e);

	public void mouseClick(MouseEvent e);

	public void keyTyped(KeyEvent e);

	public void setActive(boolean isActive);

	public int getHeight();

	public int getWidth();

	public boolean isActive();

	public String getName();
}
