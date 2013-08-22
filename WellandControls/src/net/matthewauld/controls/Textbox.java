package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.matthewauld.graphics.Colors;

public class Textbox implements Control {
	public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 25;
	private int x = 0, y = 0, w = DEFAULT_WIDTH, h = DEFAULT_HEIGHT;
	private Object text = "";
	private String characterList = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+<,>.?/:;\"'{[}]|\\";
	private ArrayList<String> acceptedCharacters = new ArrayList<String>();
	private boolean isPassword = false, isActive = false, isCentered = true;
	private Color foreColor = Color.BLACK;

	public Textbox(){
		loadAcceptedCharacters();
	}
	
	public void loadAcceptedCharacters(){
		String[] list = characterList.split("");
		for(String i:list){
			acceptedCharacters.add(i);
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

		// Draw Fill
		Graphics2D g2 = (Graphics2D) g.create();
		if (isActive) {
			g2.setPaint(new GradientPaint(new Point(x, y), Colors.textboxActiveTop, new Point(x, y + h), Colors.textboxActiveBottom));
		} else {
			g2.setPaint(new GradientPaint(new Point(x, y), Colors.textboxTop, new Point(x, y + h), Colors.textboxBottom));
		}
		g2.fillRect(x, y, w, h);

		// Draw Text
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (isPassword) {
			int count = text.toString().length();
			for (int i = 0; i < text.toString().length(); i++) {
				g2.setColor(Colors.white50percent);
				g2.fillArc(x + (w / 2) + (i * 5) - ((count * 5) / 2), y + (h / 2), 4, 4, 0, 360);
				g2.setColor(foreColor);
				g2.fillArc(x + (w / 2) + (i * 5) - ((count * 5) / 2), y + (h / 2) - 1, 4, 4, 0, 360);
			}
		} else {
			if (isCentered) {
				g2.setColor(Colors.white50percent);
				g2.drawString(text.toString(), x + (w / 2) - (g.getFontMetrics().stringWidth(text.toString()) / 2), y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2) + 1);
				g2.setColor(foreColor);
				g2.drawString(text.toString(), x + (w / 2) - (g.getFontMetrics().stringWidth(text.toString()) / 2), y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2));
			} else {
				g2.setColor(Colors.white50percent);
				g2.drawString(text.toString(), x + 5, y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2) + 1);
				g2.setColor(foreColor);
				g2.drawString(text.toString(), x + 5, y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2));
			}
		}
		// Draw Border
		g.setColor(Colors.borderColor);
		g.drawRect(x, y, w, h);

		// Draw Hightlights
		g.setColor(Colors.white50percent);
		g.drawRect(x + 1, y + 1, w - 2, h - 2);
		g.drawRect(x - 1, y - 1, w + 2, h + 2);
	}

	// Textbox specific
	public void setPassword(boolean isPassword) {
		this.isPassword = isPassword;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		int code = (int) e.getKeyChar();
		if (isActive) {
			if (code == 8) {
				if (text.toString().length() >= 1) {
					text = text.toString().substring(0, text.toString().length() - 1);
				}
			} else {
				String character = "" + (char)code;
				if(acceptedCharacters.contains(character.toLowerCase()))
					text = text.toString() + (char) code;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
			this.isActive = true;
		} else {
			this.isActive = false;
		}
	}
	
	public boolean isActive(){
		return this.isActive;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setCentered(boolean isCentered) {
		this.isCentered = isCentered;
	}
	
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
		this.foreColor = color;
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
