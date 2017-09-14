package com.paulodorow.screencaster.capture.server;

import java.awt.AWTException;
import java.io.IOException;

import com.paulodorow.screencaster.capture.shared.ScreenCaptureDefaults;

/**
 * <p>Launches a server that will serve screenshots over a TCP socket.
 * 
 * <p>Screenshots are taken on the local computer at a specified interval and transmitted over the socket in JPG format.
 * 
 * <p>Multiple clients can connect to obtain screenshots. Clients will receive the last 
 * screenshot taken.
 * 
 * <p><b>IMPORTANT:</b> in its current incarnation the server will transmit information without encryption
 * or authorization to anyone that connects. Please only use it in a local environment/network that you trust.
 * 
 * @author Paulo
 *
 */
public class ScreenCaptureServer {

	//TOOD parameters to add: port, capture interval
	public static void main(String[] args) {
		
		/*
		 *  Launches thread responsible for taking the screenshots 
		 *  at the specified interval.
		 */
		ScreenCaptureThread captureThread = null;
		try {
			
			captureThread = new ScreenCaptureThread(200);
			captureThread.start();
			
		} catch (AWTException e) {
			
			System.err.println("Unable to capture screen.");
			e.printStackTrace();
			System.exit(-1);
			
		}

		/*
		 *  Launches socket server to listen for connections. 
		 */
		try {
			
			new ScreenCaptureServerListener(ScreenCaptureDefaults.CAPTURE_SERVER_PORT, captureThread).listen();
			
		} catch (IOException e) {

			System.err.println("Unable to run capture server.");
			e.printStackTrace();
			System.exit(-1);

		}
		
	}
	
}
