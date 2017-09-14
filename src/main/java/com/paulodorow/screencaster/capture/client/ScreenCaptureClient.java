package com.paulodorow.screencaster.capture.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import com.paulodorow.screencaster.capture.shared.ScreenCaptureDefaults;

/**
 * Client to receive screenshots from the server.
 * 
 * @author Paulo
 *
 */
@Component
public class ScreenCaptureClient {

	 /*
	  * Calculates initial array size based on
	  * an image having at most 150K in size
	  */
	// TODO transform this into a configurable parameter of the class
	private static final int IMAGE_SIZE = 150 * 1024;
	
	private String host;
	private int port;

	/**
	 * Initializes client with host information.
	 * @param host the IP or DNS-resolvable host name of the server.
	 * @param port the port the server is listening on.
	 */
	public ScreenCaptureClient(String host, int port) {
		this.setHost(host);
		this.setPort(port);
	}

	/**
	 * Initializes client with default host information
	 */
	public ScreenCaptureClient() {
		this(ScreenCaptureDefaults.CAPTURE_SERVER_HOST, ScreenCaptureDefaults.CAPTURE_SERVER_PORT);
	}

	/**
	 * <p>The capture method is used to receive the latest
	 * screenshot from the server.
	 * <p>A new socket is opened every time this method is called. The 
	 * @return byte array of the screenshot in the format
	 * transmitted by the server.
	 */
	public byte[] capture() throws IOException {
		
	     try (Socket socket = new Socket(getHost(), getPort())) {

		     ByteArrayOutputStream output = new ByteArrayOutputStream(IMAGE_SIZE);
		     
		     //TODO The call to toByteArray below creates an in memory copy of the array. Find a better way of having only one copy of the array 
		     IOUtils.copy(socket.getInputStream(), output);
		     
		     return output.toByteArray();

	     }
	     
	}

	/**
	 * Host that this client is connecting to.
	 * @return host name or IP
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets host name or IP that this client will connect to.
	 * @return
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Port that this client is connecting to.
	 * @return port number
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets port that this client will connect to.
	 * @param port port number
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
}
