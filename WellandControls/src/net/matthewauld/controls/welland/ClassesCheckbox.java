package net.matthewauld.controls.welland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.matthewauld.controls.Control;
import net.matthewauld.graphics.Colors;

public class ClassesCheckbox implements Control {
	private int x = 0, y = 0, w = 550, h = 15, cid = -1,plateNumber = 0;
	private String title = "",brand = "";
	private boolean isActive = false;
	
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}
	
	public void setNumber(int plateNumber){
		this.plateNumber = plateNumber;
	}
	
	public void setID(int cid) {
		this.cid = cid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(new GradientPaint(new Point(this.x, this.y), Colors.barColorTop, new Point(this.x, this.y + 15), Colors.barColorBottom));
		g2.fillRect(this.x + 3, this.y + 1, 13, 13);
		if (this.isActive) {
			g2.setColor(Colors.arrowTopGradientTop);
		} else {
			g2.setColor(Colors.softwareColor);
		}
		g2.fillRect(this.x + 5, this.y + 3, 9, 9);

		if (this.isActive) {
			g2.setColor(Colors.arrowBorder);
		} else {
			g2.setColor(Colors.borderColor);
		}

		g2.drawRect(this.x + 5, this.y + 3, 9, 9);

		g.setColor(Colors.borderColor);
		g.drawRect(this.x + 3, this.y + 1, 13, 13);

		g.setColor(Color.BLACK);
		g.drawString(title + " - #" + plateNumber + " " + brand, this.x + 20, this.y + 12);

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
		// TODO Auto-generated method stub

	}

	@Override
	public void setForeColor(Color color) {
		// TODO Auto-generated method stub

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

	public boolean isClicked(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + (this.h - 1)) {
			this.isActive = !isActive;
			return true;
		}
		return false;
	}

	public String getTitle() {
		return this.title;
	}

	public int getID() {
		return this.cid;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public int getBikeNumber() {
		return plateNumber;
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
