package com.paulodorow.screencaster.capture.server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;

/**
 * A worker to server screenshot.
 * <p>Each connection is handled by a different worker instance.
 * <p>The worker will simply request a screen capture for the screen capture provider and
 * then immediately send it over the socket.
 * @author Paulo
 *
 */
public class ScreenCaptureServerWorker extends Thread {
	
	private Socket socket;
	private ProvidesScreenCapture screenCaptureProvider;

	/**
	 * Initializes worker with socket and screen capture provider.
	 * @param socket active socket used for communication.
	 * @param screenCaptureProvider provider for the screen capture image.
	 */
	public ScreenCaptureServerWorker(Socket socket, ProvidesScreenCapture screenCaptureProvider) {
		this.socket = socket;
		this.screenCaptureProvider = screenCaptureProvider;
	}

	@Override
	public void run() {
		
		try {
			
			BufferedImage screenCapture = screenCaptureProvider.getScreenCapture();
			ImageIO.write(screenCapture, "jpg", socket.getOutputStream());

		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
