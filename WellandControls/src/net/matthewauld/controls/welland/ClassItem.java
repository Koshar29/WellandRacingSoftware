package net.matthewauld.controls.welland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.matthewauld.controls.Control;
import net.matthewauld.graphics.Colors;

public class ClassItem implements Control{
	private Object text = "";
	private int x = 0, y = 0,w = 0,h = 15,id = -1;
	private boolean isHovering = false;
	@Override
	public void render(Graphics g) {
		if(this.isHovering){
			g.setColor(Colors.white50percent);
			g.fillRect(this.x+1,this.y,this.w,this.h);
		}
		g.setColor(Color.BLACK);
		g.drawString(this.text.toString(),this.x + (this.w / 2) - (g.getFontMetrics().stringWidth(this.text.toString()) / 2),this.y + 12);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocation(Point coords) {
		// TODO Auto-generated method stub
		
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
	
	public void mouseMovedInt(int mx, int my) {
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h - 1) {
			isHovering = true;
		} else {
			isHovering = false;
		}
	}

	public boolean isClicked(int mx, int my) {
		if (mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h - 1) {
			return true;
		}
		return false;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
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
