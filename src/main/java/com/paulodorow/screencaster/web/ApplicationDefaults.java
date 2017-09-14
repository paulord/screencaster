package com.paulodorow.screencaster.web;

import com.paulodorow.screencaster.capture.shared.ScreenCaptureDefaults;

public class ApplicationDefaults {

	public static final String 	CAPTURE_SERVER_HOST = "127.0.0.1";
	public static final int 		CAPTURE_SERVER_PORT = ScreenCaptureDefaults.CAPTURE_SERVER_PORT; 

	/**
	 * Paths for HTTP requests
	 * @author Paulo
	 *
	 */
	public static class Paths {
		
		/**
		 * Root path mapping
		 */
		public static final String ROOT = "/";

		private static final String SCREEN = "/screen";

		/**
		 * Path mapping for static JPG
		 */
		public static final String SCREEN_JPG = SCREEN + "/jpg";
		
		/**
		 * Path mapping for MJPG movie
		 */
		public static final String SCREEN_MJPG = SCREEN + "/mjpg";

	}
	
}
