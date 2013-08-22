package com.wcmc.Software.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.wcmc.Software.Client;
import com.wcmc.Software.excel.ExportCMAPoints;
import com.wcmc.Software.excel.ExportNewsPaper;

import net.matthewauld.controls.Button;
import net.matthewauld.controls.Control;
import net.matthewauld.controls.Label;
import net.matthewauld.controls.ProgressBar;

public class TreasurerScreen implements Screen {
	private boolean isActive = false;
	private ArrayList<Control> controls = new ArrayList<Control>();
	private Button btnFullStandings = null, btnNewsPaper = null;
	public ProgressBar prgExport = new ProgressBar(), prgClass = new ProgressBar();
	public Label classSpecific = new Label(), overall = new Label();;

	public TreasurerScreen() {
		reloadControls();
	}

	@Override
	public void reloadControls() {
		controls.clear();

		btnNewsPaper = new Button();
		btnNewsPaper.setText("News Paper");
		btnNewsPaper.setLocation(60, 85);
		controls.add(btnNewsPaper);

		btnFullStandings = new Button();
		btnFullStandings.setText("Full Standings");
		btnFullStandings.setLocation(165, 85);
		controls.add(btnFullStandings);

		prgExport.setLocation((Client.WIDTH / 2) - 250, Client.HEIGHT / 2 - prgExport.getHeight() / 2);
		prgExport.setWidth(500);
		prgExport.setPercent(50);
		prgExport.setVisible(false);
		controls.add(prgExport);

		overall.setText("Overall");
		overall.setLocation(Client.WIDTH / 2 - 250, Client.HEIGHT / 2 - 29);
		overall.setVisible(false);
		controls.add(overall);

		classSpecific.setText("Class: ");
		classSpecific.setLocation(Client.WIDTH / 2 - 250, Client.HEIGHT / 2 + 15);
		classSpecific.setVisible(false);
		controls.add(classSpecific);

		prgClass.setWidth(500);
		prgClass.setHeight(15);
		prgClass.setLocation((Client.WIDTH / 2) - 250, Client.HEIGHT / 2 - prgExport.getHeight() / 2 + 44);
		prgClass.setVisible(false);
		controls.add(prgClass);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		String txt = "Export To: ";
		g2.drawString(txt, 5, 103);

		for (Control c : controls) {
			c.render(g);
		}

		// reloadControls();
	}

	@Override
	public void mouseMove(MouseEvent e) {
		for (Control c : controls) {
			c.mouseMoved(e);
		}
	}

	@Override
	public void mouseClick(MouseEvent e) {
		if (btnNewsPaper.isClicked(e)) {
			ExportNewsPaper eNP = new ExportNewsPaper();
			eNP.setName("Exporting To Excel News Report");
			eNP.start();
		}
		if(btnFullStandings.isClicked(e)){
			ExportCMAPoints eCMA = new ExportCMAPoints();
			eCMA.setName("Exporting To Excel Total Year Standings");
			eCMA.start();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
