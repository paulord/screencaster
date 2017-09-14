package com.paulodorow.screencaster.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paulodorow.screencaster.capture.client.ScreenCaptureClient;

/**
 * Main controller for the Screencaster application.
 * 
 * @author Paulo
 *
 */
@Controller
public class Screencaster {
	
	private static final String BOUNDARY = "ScreencasterBoundary";
	private static final String CONTENT_TYPE_MOVIE = "multipart/x-mixed-replace; boundary=" + BOUNDARY;
	private static final String CONTENT_TYPE_STATIC = "image/jpeg";
	
	@Autowired
	private ScreenCaptureClient screenCapture;
	
	/**
	 * Root mapping. Redirects to main page.
	 * @return
	 */
	@GetMapping(ApplicationDefaults.Paths.ROOT)
	public String index() {
		return "index.html";
	}
	
	/**
	 * Returns a static JPG of latest screenshot
	 */
	@GetMapping(path=ApplicationDefaults.Paths.SCREEN_JPG)
	@ResponseBody
	public void screenImage(HttpServletResponse response) {
		
		setNoCacheResponseHeaders(response);
        response.setHeader("Content-Type", CONTENT_TYPE_STATIC);

        try {
        	
	        	OutputStream responseBody = response.getOutputStream();

            byte[] data = screenCapture.capture();

            responseBody.write(data);
            responseBody.flush();
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
            
	}
	
	/**
	 * Returns a MJPG stream of screenshots
	 */
	@GetMapping(path=ApplicationDefaults.Paths.SCREEN_MJPG)
	@ResponseBody
    public void screencast(HttpServletResponse response) {

		setNoCacheResponseHeaders(response);
        response.setHeader("Content-Type", CONTENT_TYPE_MOVIE);

	        ScreenCaptureClient screenCapture = new ScreenCaptureClient();
	        
	        while (true) {

	            try {
	            	
	    	        		OutputStream responseBody = response.getOutputStream();

		            byte[] data = screenCapture.capture();

		            responseBody.write(("--" + BOUNDARY + "\r\n"
		                    + "Content-type: image/jpg\r\n"
		                    + "Content-Length: "
		                    + data.length
		                    + "\r\n\r\n").getBytes());

		            responseBody.write(data);
		            responseBody.flush();
		            
		            //TODO make the sleep time configurable
		            Thread.sleep(200);

		            
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
	            
	        }		
		
    }
	
	/**
	 * Set headers of an HTTP response required to avoid caching contents
	 * @param response
	 */
	private static void setNoCacheResponseHeaders(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, private");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Max-Age", "0");
        response.setHeader("Expires", "0");
	}
		
}
