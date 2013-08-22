package net.matthewauld.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import net.matthewauld.graphics.Colors;

public class ProgressBar implements Control {
	private boolean isVisible = true;
	public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 25;
	private int x = 0, y = 0, w = DEFAULT_WIDTH, h = DEFAULT_HEIGHT, percent;
	private double percentage = 0.0;
	private Object text = "0%";

	public ProgressBar() {
		calculatePercentage();
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
		calculatePercentage();
	}

	@Override
	public void setHeight(int h) {
		this.h = h;
	}

	@Override
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
		calculatePercentage();
	}

	@Override
	public void setSize(Dimension size) {
		this.w = size.width;
		this.h = size.height;
		calculatePercentage();
	}

	@Override
	public void setText(Object text) {
		this.text = text;
	}

	@Override
	public void render(Graphics g) {
		if(isVisible){
			// Render the progress bar
	
			// Draw Percentage Fill
			g.setColor(Colors.progressBarTop);
			g.fillRect(x, y, percent, h / 2);
			g.setColor(Colors.progressBarBottom);
			g.fillRect(x, y + (h / 2), percent, h / 2);
			// Draw Hightlights
			g.setColor(Colors.borderColor);
			g.drawRect(x, y, w - 1, h - 1);
			g.setColor(Colors.white50percent);
			g.drawRect(x + 1, y + 1, w - 3, h - 3);
	
			// Render Text
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Colors.black50percent);
	
			g2.drawString(text.toString(), x + (w / 2) - (g.getFontMetrics().stringWidth(text.toString()) / 2), y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2));
			g2.setColor(Color.WHITE);
			g2.drawString(text.toString(), x + (w / 2) - (g.getFontMetrics().stringWidth(text.toString()) / 2), y + (h / 2) + (g.getFontMetrics().getMaxAscent() / 2) - 1);
		}
	}

	// ProgressBarSpecific
	private void calculatePercentage() {
		percent = (int) ((percentage / 100.0) * (double) w);
		DecimalFormat df = new DecimalFormat("#.##");
		text = df.format(percentage) + "%";
	}

	public void setPercent(double percent) {
		percentage = percent;
		calculatePercentage();
	}

	public double getPercent() {
		return this.percentage;
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

	@Override
	public void setForeColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
