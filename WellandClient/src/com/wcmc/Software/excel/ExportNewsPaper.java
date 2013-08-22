package com.wcmc.Software.excel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.matthewauld.controls.welland.ClassItem;
import net.matthewauld.racetrack.constants.CONST;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.wcmc.Software.Client;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportNewsPaper extends Thread {
	private WritableFont arial10bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
	private WritableCellFormat arial10boldformat = new WritableCellFormat(arial10bold);
	private WritableCellFormat cf = null, cfCenter = null, cfPosition = null, cfFinal = null;
	private JSONParser parser = new JSONParser();

	public ExportNewsPaper() {
		cf = new WritableCellFormat();
		cfCenter = new WritableCellFormat();
		cfPosition = new WritableCellFormat();
		cfFinal = new WritableCellFormat();
		try {
			cf.setFont(arial10bold);
			cf.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cf.setAlignment(Alignment.LEFT);
			cfCenter.setFont(arial10bold);
			cfCenter.setBorder(Border.BOTTOM, BorderLineStyle.THICK);
			cfCenter.setAlignment(Alignment.CENTRE);
			cfPosition.setFont(arial10bold);
			cfPosition.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cfPosition.setAlignment(Alignment.CENTRE);
			cfFinal.setFont(arial10bold);
			cfFinal.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
		}
	}

	@Override
	public void run() {
		DateTime now = new DateTime(DateTimeZone.forID("EST"));
		DateTime saturday = now.withDayOfWeek(6);
		if (saturday.isAfter(now)) {
			saturday = saturday.minusWeeks(1);
		}
		Date lastSat = saturday.toDate();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		JFileChooser saveAs = new JFileChooser(System.getProperty("user.home"));
		saveAs.setDialogTitle("Save Standings for " + date.format(lastSat));
		saveAs.setDialogType(JFileChooser.SAVE_DIALOG);

		saveAs.setFileFilter(new FileNameExtensionFilter("Excel 2003 (.xls)", "xls"));
		if (saveAs.showSaveDialog(Client.window) == JFileChooser.APPROVE_OPTION) {
			File exportFile = null;
			if (!saveAs.getSelectedFile().toString().endsWith(".xls")) {
				exportFile = new File(saveAs.getSelectedFile().getAbsolutePath() + ".xls");
			} else {
				exportFile = saveAs.getSelectedFile();
			}
			try {
				WritableWorkbook excelFile = Workbook.createWorkbook(exportFile);
				WritableSheet sheet1 = excelFile.createSheet("June 29th 2013", 0);

				Label dateCell = new Label(0, 0, "Date:", arial10boldformat);
				sheet1.addCell(dateCell);

				DateFormat customDateFormat = new DateFormat("MMMM dd yyyy");
				WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
				dateFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
				dateFormat.setAlignment(Alignment.LEFT);
				dateFormat.setFont(arial10bold);
				jxl.write.DateTime dateFormatCell = new jxl.write.DateTime(1, 0, lastSat, dateFormat);
				sheet1.mergeCells(1, 0, 2, 0);
				sheet1.addCell(dateFormatCell);

				// Adjust Columns Settings
				sheet1.setColumnView(0, 10); 
				sheet1.setColumnView(3, 30);
				sheet1.setColumnView(5, 30);
				int currentClass = 0;
				int classHeight = 0;
				// Start grabbing info
				Client.ms.trS.prgExport.setVisible(true);
				Client.ms.trS.prgExport.setPercent(0);
				Client.ms.trS.prgClass.setVisible(true);
				Client.ms.trS.prgClass.setPercent(0);
				Client.ms.trS.overall.setVisible(true);
				Client.ms.trS.classSpecific.setVisible(true);
				for (int i = 0; i < Client.ms.rS.classes.getClasses().size(); i++) {
					ClassItem c = Client.ms.rS.classes.getClasses().get(i);
					Client.sc.send(CONST.GET_CLASS_STANDING + " " + c.getID() + CONST.seperator + date.format(lastSat));
					Client.ms.trS.classSpecific.setText("Class: " + c.getText());
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
					}

					String riderInfo = Client.sc.getInfo();
					if (riderInfo != null) {
						try {
							JSONObject classInfo = (JSONObject) parser.parse(riderInfo);
							if (classInfo != null) {
								JSONArray riders = (JSONArray) classInfo.get("riders");

								if (riders.size() > 0) {
									sheet1.addCell(new Label(0, (currentClass + 1) * 2 + classHeight, "Class", arial10boldformat));
									sheet1.mergeCells(1, (currentClass + 1) * 2 + classHeight, 2, (currentClass + 1) * 2 + classHeight);
									sheet1.addCell(new Label(1, (currentClass + 1) * 2 + classHeight, c.getText().toString(), cf));

									sheet1.addCell(new Label(0, (currentClass + 1) * 2 + classHeight + 2, "Final", cfCenter));
									sheet1.addCell(new Label(1, (currentClass + 1) * 2 + classHeight + 2, "Rider #", cfCenter));
									sheet1.addCell(new Label(3, (currentClass + 1) * 2 + classHeight + 2, "Rider Name", cfCenter));
									sheet1.addCell(new Label(5, (currentClass + 1) * 2 + classHeight + 2, "Home Town", cfCenter));

									for (int r = 0; r < riders.size(); r++) {
										sheet1.addCell(new jxl.write.Number(0, (currentClass + 1) * 2 + classHeight + 3, (r + 1), cfFinal));

										JSONObject rider = (JSONObject) riders.get(r);
										sheet1.addCell(new jxl.write.Number(1, (currentClass + 1) * 2 + classHeight + 3, Integer.parseInt(rider.get("bike_number").toString()), cfPosition));

										sheet1.addCell(new Label(3, (currentClass + 1) * 2 + classHeight + 3, rider.get("name").toString(), cf));
										sheet1.addCell(new Label(5, (currentClass + 1) * 2 + classHeight + 3, rider.get("hometown").toString(), cf));
										classHeight++;
										Client.ms.trS.prgClass.setPercent(((double) r / (double) riders.size()) * 100);
									}
									classHeight += 6;
								}
							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					Client.ms.trS.prgExport.setPercent(((double) i / (double) Client.ms.rS.classes.getClasses().size()) * 100);
				}

				excelFile.write();
				excelFile.close();
				Client.ms.trS.prgExport.setVisible(false);
				Client.ms.trS.prgClass.setVisible(false);
				Client.ms.trS.overall.setVisible(false);
				Client.ms.trS.classSpecific.setVisible(false);
			} catch (IOException | WriteException e) {
				e.printStackTrace();
			}

		}
	}

	public void writeClass(WritableSheet w) {

	}
}
