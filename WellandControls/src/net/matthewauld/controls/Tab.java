package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import net.matthewauld.graphics.Colors;

public class Tab implements ITab {
	private int x = 0, y = 0, w = 75, h = 30;
	private String text = "";
	private boolean isActive = false, isHovering = false;
	private Font activeFont = new Font("Arial", Font.BOLD, 12);
	private Font defaultFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Colors.borderColor);
		g2.drawRect(x, y, w, h);

		if (isActive) {
			g2.setColor(Colors.softwareColor);
			g2.fillRect(this.x + 1, this.y + 1, this.w - 1, this.h + 1);
			g2.setColor(Colors.white50percent);
			// DrawBorders
			g2.drawLine(this.x + 2, this.y + 1, this.x + this.w - 2, this.y + 1);
			g2.drawLine(this.x + this.w - 1, this.y + 1, this.x + this.w - 1, this.y + this.h);
			g2.drawLine(this.x + 1, this.y + 1, this.x + 1, this.y + this.h);
			// Draw Text
			g2.setColor(Color.BLACK);
			g2.setFont(activeFont);
			g2.drawString(this.text, this.x + (this.w / 2) - (g.getFontMetrics().stringWidth(this.text) / 2), this.y + 20);
		} else {
			if (isHovering) {
				g2.setColor(Colors.softwareInactiveColor.brighter());
				g2.fillRect(this.x + 1, this.y + 1, this.w - 1, this.h - 1);
			} else {
				g2.setColor(Colors.softwareInactiveColor);
				g2.fillRect(this.x + 1, this.y + 1, this.w - 1, this.h - 1);
			}

			g2.setColor(Colors.white50percent);
			g2.drawRect(this.x + 1, this.y + 1, this.w - 2, this.h - 2);
			g2.setColor(Color.BLACK);
			g2.setFont(defaultFont);
			g2.drawString(this.text, this.x + (this.w / 2) - (g.getFontMetrics().stringWidth(this.text) / 2), this.y + 20);
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
		this.text = text.toString();
		Graphics graphics = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
		this.setWidth(graphics.getFontMetrics().stringWidth(text.toString()) + 12);
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
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public void mouseMoved(int mx, int my) {
		if (mx >= this.x + 1 && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h) {
			isHovering = true;
		} else {
			isHovering = false;
		}
	}

	@Override
	public void mouseClicked(int mx, int my) {
		if(my >= this.y && my <= this.y + this.h){
			if (mx >= this.x + 1 && mx <= this.x + this.w) {
				this.isActive = true;
			}else{
				this.isActive = false;
			}
		}
	}

	@Override
	public boolean isClicked(int mx, int my) {
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h) {
			return true;
		}else{
			return false;
		}
	}

}
