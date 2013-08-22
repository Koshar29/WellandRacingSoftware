package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.matthewauld.graphics.Colors;

public class TabContainer implements Control {
	private int x = 0, y = 0, w = 100, h = 100;
	private ArrayList<ITab> tabs = new ArrayList<ITab>();

	public void add(Tab tab) {
		int totalWidth = 0;
		for (ITab t : tabs) {
			totalWidth += t.getWidth();
		}
		tab.setX(totalWidth);
		// System.out.println("Tabs Width: " + totalWidth);
		tabs.add(tab);
	}

	public ArrayList<ITab> getTabs() {
		return tabs;
	}

	public void clear() {
		tabs.clear();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Colors.borderColor);
		g.drawRect(x, y + 30, w, h - 30);
		g.setColor(Colors.white50percent);
		g.drawLine(this.x, this.y + 31, this.x + this.w, this.y + 31);
		Graphics2D g2 = (Graphics2D) g.create(x, y, w, h);
		for (ITab tab : tabs) {
			tab.render(g2);
		}
		g2.dispose();
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
		return w;
	}

	@Override
	public int getHeight() {
		return h;
	}

	@Override
	public Object getText() {
		// TODO Auto-generated method stub
		return null;
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
		this.w = w;
	}

	@Override
	public void setHeight(int h) {
		this.h = h;
	}

	@Override
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}

	@Override
	public void setSize(Dimension size) {
		this.w = size.width;
		this.h = size.height;
	}

	@Override
	public void setText(Object text) {

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
		int mx = e.getX(), my = e.getY() - this.y;
		for (ITab t : tabs) {
			t.mouseClicked(mx, my);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX(), my = e.getY() - this.y;

		for (ITab t : tabs) {
			t.mouseMoved(mx, my);
		}
	}

	@Override
	public void setForeColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

}
