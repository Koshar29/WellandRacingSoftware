package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Control {
	// Input
	public void render(Graphics g);
	// Getters
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public Object getText();
	public boolean isVisible();
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
	public void setForeColor(Color color);
	public void setVisible(boolean isVisible);
	//Controllers
	public void keyPressed(KeyEvent e);
	public void keyTyped(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void mouseClicked(MouseEvent e);
	public void mouseMoved(MouseEvent e);
}
