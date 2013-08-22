package net.matthewauld.controls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

public interface ITab{
	// Input
	public void render(Graphics g);
	// Getters
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public Object getText();
	public boolean isActive();
	// Setters
	public void setX(int x);
	public void setY(int y);
	public void setLocation(int x,int y);
	public void setLocation(Point coords);
	public void setWidth(int w);
	public void setHeight(int h);
	public void setSize(int w,int h);
	public void setSize(Dimension size);
	public void setText(Object text);
	public void setActive(boolean isActive);
	//Controllers
	public void keyPressed(KeyEvent e);
	public void keyTyped(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void mouseMoved(int mx, int my);
	public void mouseClicked(int mx, int my);
	public boolean isClicked(int mx, int my);
}
