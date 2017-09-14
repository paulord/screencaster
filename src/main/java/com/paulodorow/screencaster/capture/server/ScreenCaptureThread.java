package com.paulodorow.screencaster.capture.server;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

/**
 * Thread that captures screenshots at a specified interval.
 * 
 * @author Paulo
 *
 */
public class ScreenCaptureThread extends Thread implements ProvidesScreenCapture {

	private ScreenCapture screenCapture;
	private BufferedImage capture;
	private int interval;

	/**
	 * Creates thread to capture screenshots at configured interval.
	 * @param interval interval at which to capture the screenshots.
	 * @throws AWTException
	 */
	public ScreenCaptureThread(int interval) throws AWTException  {
		screenCapture = new ScreenCapture();
		this.interval = interval;
	}
	
	@Override
	public void run() {

		capture = screenCapture.capture();

		while (this.isAlive()) {

			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				return;
			}

			synchronized (capture) {
				capture = screenCapture.capture();
			}

		}
		
	}
	
	/**
	 * Returns the last captured screenshot.
	 * This method is thread-safe and can be called from multiple threads.
	 */
	@Override
	public BufferedImage getScreenCapture() {
		synchronized (capture) {
			return capture;
		}
	}

}
