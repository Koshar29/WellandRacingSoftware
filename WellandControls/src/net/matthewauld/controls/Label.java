package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.matthewauld.graphics.Colors;

public class Label implements Control {
	private Object text = "";
	private int x = 0, y = 0;
	private boolean isVisible = true;

	@Override
	public void render(Graphics g) {
		if(isVisible){
			Graphics2D g2 = (Graphics2D)g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Colors.white50percent);
			g2.drawString(text.toString(), x, y + 13);
			g2.setColor(Color.BLACK);
			g2.drawString(text.toString(), x, y + 12);
			g2.dispose();
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public Object getText() {
		return null;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setLocation(Point coords) {
		this.x = coords.x;
		this.y = coords.y;
	}

	@Override
	public void setWidth(int w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeight(int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSize(int w, int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSize(Dimension size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setText(Object text) {
		this.text = text;
	}

	@Override
	public void setForeColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
