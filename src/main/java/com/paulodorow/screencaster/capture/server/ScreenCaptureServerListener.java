package com.paulodorow.screencaster.capture.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket listener that will spawn workers for each client connection.
 * @author Paulo
 *
 */
public class ScreenCaptureServerListener {

	private int port;
	private ProvidesScreenCapture screenCaptureProvider;

	/**
	 * Initializes listener on specified port
	 * @param port port to listen on.
	 * @param screenCaptureProvider service that will provide the screenshot images.
	 */
	public ScreenCaptureServerListener(int port, ProvidesScreenCapture screenCaptureProvider) {
		this.port = port;
		this.screenCaptureProvider = screenCaptureProvider;
	}
	
	/**
	 * Starts listening on configured port.
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {

		try (ServerSocket server = new ServerSocket(port)) {
			
			//TODO make it possible to stop listener gracefully.
			while (true) {

				Socket socket = server.accept();
				new ScreenCaptureServerWorker(socket, screenCaptureProvider).start();

			}
			
		}

	}
	
}
