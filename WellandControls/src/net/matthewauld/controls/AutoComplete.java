package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.matthewauld.graphics.Colors;

public class AutoComplete implements Control {
	private int x = 0, y = 0, w = 400, h = 150;
	private ArrayList<AutoCompleteItem> riders = new ArrayList<AutoCompleteItem>();

	public int getRiderID(MouseEvent e) {
		for (AutoCompleteItem rider : riders) {
			int mx = e.getX() - this.x, my = e.getY() - this.y;
			if (rider.mouseClickedInt(mx, my)) {
				return rider.getID();
			}
		}

		return -1;
	}

	public int getSize() {
		return riders.size();
	}

	public void add(AutoCompleteItem item) {
		riders.add(item);
		if (this.riders.size() < 10) {
			this.h = (riders.size() * 15) + 1;
		}
	}

	public void clear() {
		riders.clear();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(this.x, this.y, this.w, this.h);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);

		if (riders.size() == 0) {
			// Draw Nothing
		} else {
			g.setColor(Colors.softwareColor);
			g.fillRect(this.x, this.y, this.w, this.h);
			g.setColor(Colors.borderColor);
			g.drawRect(this.x, this.y, this.w, this.h);
		}

		for (AutoCompleteItem r : riders) {
			r.render(g2);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
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

	public boolean isClicked(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (AutoCompleteItem i : riders) {
			i.mouseMovedInt(e.getX() - this.x, e.getY() - this.y);

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
