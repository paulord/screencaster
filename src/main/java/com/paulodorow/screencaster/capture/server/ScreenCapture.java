package com.paulodorow.screencaster.capture.server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * Takes a screenshot.
 * 
 * Relies on AWT API.
 * 
 * @author Paulo
 *
 */
public class ScreenCapture {

	private Robot robot;
	private Rectangle screenRect;
	private Toolkit toolkit;

	public ScreenCapture() throws AWTException {

		toolkit = Toolkit.getDefaultToolkit();
		refreshScreenParameters();
		
		robot = new Robot();
		
	}
	
	/**
	 * Captures the current screen.
	 * @return BufferedImage containing screenshot
	 */
	public BufferedImage capture() {
		return robot.createScreenCapture(screenRect);
	}

	/**
	 * This method needs to be called if screen size or resolution changes
	 * to update the store parameters of screen configuration.
	 * 
	 */
	public void refreshScreenParameters() {
		Dimension screenSize = toolkit.getScreenSize();
		screenRect = new Rectangle(screenSize);
	}
	
}
