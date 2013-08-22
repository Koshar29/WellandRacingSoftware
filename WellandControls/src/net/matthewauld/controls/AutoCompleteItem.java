package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.matthewauld.graphics.Colors;

public class AutoCompleteItem implements Control {
	private Object text = "";
	private int x = 0, y = 0, w = 0, h = 0, id = -1;
	private boolean isHovering = false;

	@Override
	public void render(Graphics g) {
		if (this.isHovering) {
			g.setColor(Colors.white50percent);
			g.fillRect(this.x + 1, this.y, this.w - 1, this.h);
		}
		g.setColor(Color.BLACK);
		g.drawString(text.toString(), x + 5, y + 12);
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
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
		return this.text;
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
		this.text = text;
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
	
	public boolean mouseClickedInt(int mx,int my){
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h) {
			return true;
		}
		return false;
	}

	public void mouseMovedInt(int mx, int my) {
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h - 1) {
			isHovering = true;
		} else {
			isHovering = false;
		}
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
