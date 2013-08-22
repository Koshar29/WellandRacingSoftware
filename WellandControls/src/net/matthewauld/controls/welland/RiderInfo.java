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

public class RiderInfo implements Control {
	private int x = 0, y = 0, w = 650, h = 400, rid = -1;
	private String riderName = "", address = "", homePhone = "", cellPhone = "", email = "", license = "";
	private ArrayList<ClassesCheckbox> classes = new ArrayList<ClassesCheckbox>();

	public void clear() {
		classes.clear();
	}

	public void addClass(ClassesCheckbox classBox) {
		classBox.setLocation(this.x + 100, this.y + 116 + classes.size() * 15);
		classes.add(classBox);
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCellPhone(String number) {
		this.cellPhone = number;
	}

	public void setHomePhone(String number) {
		this.homePhone = number;
	}

	public void setAddress(String street, String city, String province, String country) {
		String address_const = "";

		if (street != "") {
			address_const += street;
		}
		if (!street.equals("") && !city.equals("")) {
			address_const += ", " + city;
			province = province.substring(0, 2).toUpperCase();
			address_const += ", " + province;
			address_const += ", " + country;
		} else if (!city.equals("")) {
			address_const += city;
			province = province.substring(0, 2).toUpperCase();
			address_const += ", " + province;
			address_const += ", " + country;
		}
		this.address = address_const;
	}

	public void setName(String name) {
		this.riderName = name;
	}

	public void setRiderID(int rid) {
		this.rid = rid;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Colors.softwareInactiveColor);
		g.fillRect(this.x, this.y, this.w, 24);
		g.setColor(Colors.barColorBottom);
		g.fillRect(this.x, this.y + 25, 100, this.h - 25);
		g.setColor(Colors.black10percent);
		g.fillRect(this.x, this.y + 41, this.w, 15);
		g.fillRect(this.x, this.y + 71, this.w, 15);
		g.fillRect(this.x, this.y + 101, this.w, 15);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.setFont(CONST.header);
		g2.drawString("Rider Information", this.x + (w / 2) - (g2.getFontMetrics().stringWidth("Rider Information") / 2), this.y + 18);
		g2.setFont(CONST.default_font);

		// Draw Labels
		String txt = "Rider Name";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 38);
		g2.drawString(riderName, this.x + 105, this.y + 38);
		txt = "Address";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 53);
		g2.drawString(address, this.x + 105, this.y + 53);
		txt = "Home Phone";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 68);
		g2.drawString(homePhone, this.x + 105, this.y + 68);
		txt = "Cell Phone";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 83);
		g2.drawString(cellPhone, this.x + 105, this.y + 83);
		txt = "E-Mail";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 98);
		g2.drawString(email, this.x + 105, this.y + 98);
		txt = "Licence Number";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 113);
		g2.drawString(license, this.x + 105, this.y + 113);
		txt = "Classes";
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), this.y + 128);
		for (ClassesCheckbox cb : classes) {
			cb.render(g2);
		}

		txt = "Sponsors";
		int sponsorsY = this.y + 143;
		if (classes.size() > 1) {
			sponsorsY = sponsorsY + (classes.size() - 1) * 15;
		}

		g.fillRect(this.x, sponsorsY - 11, this.w, 15);
		g2.drawString(txt, this.x + 95 - g.getFontMetrics().stringWidth(txt), sponsorsY);

		// Draw Borders
		g.setColor(Colors.borderColor);
		g.drawRect(this.x, this.y, this.w, this.h);
		g.drawLine(this.x, this.y + 24, this.x + this.w, this.y + 24);
		g.drawLine(this.x + 100, this.y + 25, this.x + 100, this.y + this.h);
		g.setColor(Colors.white50percent);
		g.drawRect(this.x + 1, this.y + 1, this.w - 2, 22);
		g.drawRect(this.x + 1, this.y + 25, 98, this.h - 26);
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
		for (ClassesCheckbox c : classes) {
			if (c.isClicked(e)) {
				if (c.isActive()) {
					// Sign In
					System.out.println(c.getID() + " " + c.getTitle() + " " + c.getBikeNumber());
					Client.sc.send(CONST.SIGN_IN_RIDER + " " + rid + CONST.seperator + c.getID() + CONST.seperator + c.getBikeNumber());
				} else {
					// Sign Out
					Client.sc.send(CONST.SIGN_OUT_RIDER + " " + rid + CONST.seperator + c.getID() + CONST.seperator + c.getBikeNumber());
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

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
