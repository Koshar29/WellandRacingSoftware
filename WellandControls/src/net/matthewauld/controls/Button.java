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

public class Button implements Control {
	public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 25;
	private int x = 0, y = 0, w = DEFAULT_WIDTH, h = DEFAULT_HEIGHT;
	private Object text = "";
	private boolean isHover = false;

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
		return text;
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
	public void render(Graphics g) {
		// Draw Fills
		if (isHover) {
			g.setColor(Colors.buttonHoverTop);
			g.fillRect(x, y + 1, w, h / 2);
			g.setColor(Colors.buttonHoverBottom);
			g.fillRect(x, y + h / 2 + 1, w, h / 2);
		} else {
			g.setColor(Colors.barColorTop);
			g.fillRect(x, y + 1, w, h / 2);
			g.setColor(Colors.barColorBottom);
			g.fillRect(x, y + 1 + h / 2, w, h / 2);
		}

		// Draw Border
		g.setColor(Colors.borderColor);
		g.drawRect(x, y, w, h);
		g.setColor(Colors.white50percent);
		g.drawRect(x + 1, y + 1, w - 2, h - 2);

		// Draw Text
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Colors.white50percent);
		g2.drawString(text.toString(), x + (w / 2) - (g.getFontMetrics().stringWidth(text.toString()) / 2), y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2) + 1);

		g2.setColor(Color.BLACK);
		g2.drawString(text.toString(), x + (w / 2) - (g.getFontMetrics().stringWidth(text.toString()) / 2), y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2));

		g2.dispose();
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
		int mx = e.getX(), my = e.getY();
		if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
			isHover = true;
		} else {
			isHover = false;
		}
	}
	
	//Button Specific
	
	public boolean isClicked(MouseEvent e){
		int mx = e.getX(), my = e.getY();
		if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
			return true;
		} else {
			return false;
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
