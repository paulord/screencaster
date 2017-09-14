# Screencaster

Screencaster allows sharing of screen from a single computer to multiple clients on a local network or over the Internet.

## Architecture

`[CAPTURE SERVER] --> [CAPTURE CLIENT] <-- [WEB SERVER] --> [CLIENT]`

When a `[CLIENT]` requests screen capture from a `[WEB SERVER]`, the `[WEB SERVER]` will use a `[CAPTURE CLIENT]` to connect to a `[CAPTURE SERVER]`,
retrieve screen captures and deliver them to `[CLIENT]`.

### Capture Server

The capture server is a small program that runs in the background to capture screenshots at a specified interval.

It delivers the screenshots over a TCP socket.

The current implementation of the capture server is unsafe and will deliver screen captures to any clients that connect without authorization or encryption.

It's a small Java application that can be run from class `com.paulodorow.screencaster.capture.server.ScreenCaptureServer`.

### Capture Client

Connects to capture server to retrieve screen captures.

### Web Server

The web server component serves captured screenshots over HTTP.

The web server is capable of delivering the last screenshot taken by the capture server as a static JPG or a streaming MJPG.

The web server is a Spring Boot application that can be launched from class `com.paulodorow.screencaster.web.Application`.

### Client

A client can be any browser or other HTTP client capable of rendering either JPG or MJPG.

### Capture server vs. Web server

The screen can be captured on a different computer that the web server simply by installing the Capture Server on a machine and pointing the Capture Client on the Web Server to connect to that machine. 