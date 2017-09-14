package com.paulodorow.screencaster.capture.shared;

/**
 * Holds defaults for the application.
 * 
 * @author Paulo
 *
 */
public class ScreenCaptureDefaults {

	/**
	 * Default host the client will connect to.
	 */
	public static final String CAPTURE_SERVER_HOST = "127.0.0.1";

	/**
	 * Default TCP port the server will listen on.
	 */
	public static final int CAPTURE_SERVER_PORT = 35733;
	
	/**
	 * Interval in miliseconds in which screenshots will be taken.
	 */
	public static final int REFRESH_RATE = 200;

}
