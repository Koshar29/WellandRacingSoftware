package com.wcmc.Software.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets extends Thread implements Runnable {
	public BufferedImage logo,closeButton;

	private void loadResources() throws IOException {
		// logo =
		// ImageIO.read(Assets.class.getResource("/com/wcmc/Software/resources/banner.jpg"));
		closeButton = ImageIO.read(Assets.class.getResource("/com/wcmc/Software/resources/close.png"));
	}

	public void run() {
		try {
			loadResources();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
