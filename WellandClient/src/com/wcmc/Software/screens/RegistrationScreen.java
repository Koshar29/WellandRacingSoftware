package com.wcmc.Software.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.wcmc.Software.Client;

import net.matthewauld.controls.AutoComplete;
import net.matthewauld.controls.AutoCompleteItem;
import net.matthewauld.controls.Button;
import net.matthewauld.controls.Control;
import net.matthewauld.controls.Textbox;
import net.matthewauld.controls.welland.ClassItem;
import net.matthewauld.controls.welland.ClassesCheckbox;
import net.matthewauld.controls.welland.ClassesContainer;
import net.matthewauld.controls.welland.RiderContainer;
import net.matthewauld.controls.welland.RiderInfo;
import net.matthewauld.racetrack.constants.CONST;

public class RegistrationScreen implements Screen {
	public static int activeClass = -1;
	public int currentRider = -1;
	@SuppressWarnings("unused")
	private ArrayList<Control> controls = new ArrayList<Control>(), newRiderControls = new ArrayList<Control>(),existingRiderControls = new ArrayList<Control>();
	private AutoComplete riders = new AutoComplete();
	public ClassesContainer classes = new ClassesContainer();
	private RiderContainer classRiders = new RiderContainer();
	private RiderInfo rider = new RiderInfo();
	private boolean isActive = false, showAutocomplete = false, isShowingNewTools = false;
	private Textbox txtSearch;
	private Button btnNewRider,btnExistingRider;
	private JSONParser jsonParser = new JSONParser();

	public RegistrationScreen() {
		Client.sc.send(CONST.CLASSES_DATA_SEARCH);
		reloadControls();
	}

	@Override
	public void reloadControls() {
		controls.clear();
		existingRiderControls.clear();
		
		btnNewRider = new Button();
		btnNewRider.setText("New Rider");
		btnNewRider.setLocation(8, 90);
		controls.add(btnNewRider);

		btnExistingRider = new Button();
		btnExistingRider.setText("Existing Rider");
		btnExistingRider.setLocation(115, 90);
		controls.add(btnExistingRider);
		
		Button btnRainout = new Button();
		btnRainout.setText("Rain Out");
		btnRainout.setLocation(Client.WIDTH - btnRainout.getWidth() - 125, 90);
		controls.add(btnRainout);
		
		Button btnClear = new Button();
		btnClear.setText("Clear Attendance");
		btnClear.setWidth(Button.DEFAULT_WIDTH + 10);
		btnClear.setLocation(Client.WIDTH - btnClear.getWidth() - 10, 90);
		controls.add(btnClear);

		txtSearch = new Textbox();
		txtSearch.setText("Search For Rider...");
		txtSearch.setLocation(222, 90);
		txtSearch.setWidth(400);
		txtSearch.setCentered(false);
		txtSearch.setForeColor(Color.GRAY);
		existingRiderControls.add(txtSearch);

		classes.setLocation(8, 120);
		classRiders.setLocation(145, 120);
		rider.setWidth(650);
		rider.setLocation(Client.WIDTH - rider.getWidth() - 10, 120);
	}

	@Override
	public void render(Graphics g) {
		for (Control c : controls) {
			c.render(g);
		}
		if (!isShowingNewTools) {
			for(Control c:existingRiderControls){
				c.render(g);
			}
			classes.render(g);
			classRiders.render(g);
			rider.render(g);

			if (showAutocomplete) {
				riders.render(g);
				riders.setLocation(222, 115);
			}
		}
	}

	@Override
	public void mouseMove(MouseEvent e) {
		for (Control c : controls) {
			c.mouseMoved(e);
		}
		if (!isShowingNewTools) {
			for(Control c:existingRiderControls){
				c.mouseMoved(e);
			}
			riders.mouseMoved(e);
			classes.mouseMoved(e);
			classRiders.mouseMoved(e);
		}
	}

	@Override
	public void mouseClick(MouseEvent e) {
		for (Control c : controls) {
			c.mouseClicked(e);
		}
		if (!isShowingNewTools) {
			for(Control c:existingRiderControls){
				c.mouseClicked(e);
			}
			if (btnNewRider.isClicked(e)) {
				isShowingNewTools = true;
			}
			if(!isShowingNewTools){
				if (txtSearch.isClicked(e)) {
					if (txtSearch.getText().toString().equals("Search For Rider...")) {
						txtSearch.setText("");
						txtSearch.setForeColor(Color.BLACK);
					} else {
						if (riders.getSize() > 0 && !showAutocomplete) {
							showAutocomplete = true;
						}
					}
				} else {
					if (riders.isClicked(e)) {
						int riderID = riders.getRiderID(e);
						Client.ms.rS.currentRider = riderID;
						Client.sc.send(CONST.GET_RIDER_INFO + " " + riderID);
						showAutocomplete = false;
					}
					if (txtSearch.getText().toString().equals("") && !txtSearch.getText().equals("Search For Rider...")) {
						txtSearch.setText("Search For Rider...");
						txtSearch.setForeColor(Color.GRAY);
					}
					if (!riders.isClicked(e) && showAutocomplete) {
						showAutocomplete = false;
					}
				}
				if (!showAutocomplete) {
					classes.mouseClicked(e);
					classRiders.mouseClicked(e);
				}
				rider.mouseClicked(e);
			}
		}else{
			if(btnExistingRider.isClicked(e)){
				isShowingNewTools = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (Control c : controls) {
			c.keyTyped(e);
		}
		if (!isShowingNewTools) {
			for(Control c:existingRiderControls){
				c.keyTyped(e);
			}
			if (txtSearch.getText().toString().length() >= 1) {
				Client.sc.send(CONST.RIDER_DATA_SEARCH + " " + txtSearch.getText().toString().replace(" ", CONST.seperator));
			}
		}
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String getName() {
		return null;
	}

	public void setAutocompleteData(String data) {
		try {
			riders.clear();
			JSONObject json = (JSONObject) jsonParser.parse(data);
			JSONArray json_riders = (JSONArray) json.get("riders");
			if (json_riders != null) {
				for (int i = 0; i < json_riders.size(); i++) {
					JSONObject rider = (JSONObject) json_riders.get(i);
					System.out.println(rider);
					AutoCompleteItem item = new AutoCompleteItem();
					item.setWidth(400);
					item.setHeight(15);
					item.setText(rider.get("first_name").toString() + " " + rider.get("last_name").toString());
					item.setID(Integer.parseInt(rider.get("id").toString()));
					item.setY((i * 15) + 1);
					riders.add(item);
				}
			}
			showAutocomplete = true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setClassData(String data) {
		try {
			JSONObject json = (JSONObject) jsonParser.parse(data);
			JSONArray json_classes = (JSONArray) json.get("classes");
			if (json_classes != null) {
				for (int i = 0; i < json_classes.size(); i++) {
					JSONObject json_class = (JSONObject) json_classes.get(i);

					ClassItem classItem = new ClassItem();
					classItem.setText(json_class.get("title"));
					classItem.setID(Integer.parseInt(json_class.get("id").toString()));
					classItem.setY(i * 15);
					classItem.setWidth(classes.getWidth());
					classes.add(classItem);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setRidersData(String data) {
		try {
			JSONObject json = (JSONObject) jsonParser.parse(data);
			JSONObject classInfo = (JSONObject) json.get("class");
			if (classInfo != null) {
				classRiders.setTitle(classInfo.get("title").toString());
			}

			classRiders.clear();

			JSONArray riders = (JSONArray) json.get("riders");

			if (riders != null) {
				for (int i = 0; i < riders.size(); i++) {
					JSONObject rider_info = (JSONObject) riders.get(i);
					ClassItem riderItem = new ClassItem();
					riderItem.setText(rider_info.get("name").toString());
					riderItem.setID(Integer.parseInt(rider_info.get("id").toString()));

					riderItem.setY(i * 15);
					riderItem.setWidth(classRiders.getWidth());

					classRiders.add(riderItem);
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setRiderInfo(String data) {
		try {
			if(!data.equals("{}")){
				JSONObject json = (JSONObject) jsonParser.parse(data);
				if(json != null){
					String name = json.get("first_name").toString() + " " + json.get("last_name").toString();
					if (!json.get("middle_name").toString().equals("")) {
						name = json.get("first_name").toString() + " \"" + json.get("middle_name").toString() + "\" " + json.get("last_name").toString();
					}
					Client.ms.rS.currentRider = Integer.parseInt(json.get("id").toString());
					rider.setName(name);
					rider.setRiderID(Integer.parseInt(json.get("id").toString()));
					rider.setAddress(json.get("address").toString(), json.get("city").toString(), json.get("state").toString(), json.get("country").toString());
					rider.setHomePhone(json.get("home_phone").toString());
					rider.setCellPhone(json.get("cell_phone").toString());
					rider.setEmail(json.get("email").toString());
					rider.setLicense(json.get("license").toString());
					JSONArray classes = (JSONArray) json.get("classes");
					rider.clear();
					for (int i = 0; i < classes.size(); i++) {
						JSONObject classInfo = (JSONObject) classes.get(i);
						ClassesCheckbox classBox = new ClassesCheckbox();
						classBox.setTitle(classInfo.get("title").toString());
						classBox.setID(Integer.parseInt(classInfo.get("id").toString()));
						classBox.setNumber(Integer.parseInt(classInfo.get("bike_number").toString()));
						classBox.setBrand(classInfo.get("bike_brand").toString());
						classBox.setIsActive((boolean) classInfo.get("signed_in"));
						rider.addClass(classBox);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public int getActiveClass() {
		return activeClass;
	}

}
