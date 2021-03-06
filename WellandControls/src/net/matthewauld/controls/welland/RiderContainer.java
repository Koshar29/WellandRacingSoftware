package net.matthewauld.controls.welland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.wcmc.Software.Client;

import net.matthewauld.controls.Control;
import net.matthewauld.graphics.Colors;
import net.matthewauld.racetrack.constants.CONST;

public class RiderContainer implements Control {
	private int x = 0, y = 0, w = 200, h = 25;
	private ArrayList<ClassItem> riders = new ArrayList<ClassItem>();
	private String title = "Riders";

	public void clear() {
		this.riders.clear();
		this.h = 25;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void add(ClassItem i) {
		riders.add(i);
		this.h = (riders.size() * 15) + 25;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Colors.borderColor);
		g.drawRect(this.x, this.y, this.w, this.h);
		g.drawLine(this.x, this.y + 23, this.x + this.w, this.y + 23);
		g.setColor(Colors.softwareInactiveColor);
		g.fillRect(this.x + 1, this.y + 1, this.w - 2, 22);
		g.setColor(Colors.white50percent);
		g.drawRect(this.x + 1, this.y + 1, this.w - 2, 21);
		Graphics2D g3 = (Graphics2D) g.create();
		g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g3.setColor(Color.BLACK);
		g3.setFont(CONST.header);
		g3.drawString(title, this.x + (this.w / 2) - (g3.getFontMetrics().stringWidth(title) / 2), this.y + 18);
		g3.dispose();

		Graphics2D g2 = (Graphics2D) g.create(this.x, this.y + 25, this.w, this.h);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (ClassItem c : riders) {
			c.render(g2);
		}
		g2.dispose();
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		return this.w;
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
		this.w = w;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX() - this.x, my = e.getY() - this.y - 25;
		for (ClassItem i : riders) {
			if (i.isClicked(mx, my)) {
				Client.ms.rS.currentRider = i.getID();
				Client.sc.send(CONST.GET_RIDER_INFO + " " + i.getID());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX() - this.x, my = e.getY() - this.y - 25;
		for (ClassItem i : riders) {
			i.mouseMovedInt(mx, my);
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
