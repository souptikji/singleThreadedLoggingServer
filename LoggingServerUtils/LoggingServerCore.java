package edu.umd.cs.cmsc433.LoggingServerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class LoggingServerCore {

	final public static int NO_PORT = -1;
	final public static int MIN_PORT = 59152;
	final public static int MAX_PORT = 65535;
	final public static int DEFAULT_PORT = MIN_PORT;
	
	private int mPort = NO_PORT;
	private BufferedReader mIn;
	private ServerSocket mServerSocket;

	public LoggingServerCore(int port) {

		// Limit port numbers to 59152--65535
		if (port >= MIN_PORT && port <= MAX_PORT)
				this.mPort = port;
		else 
			mPort = DEFAULT_PORT;

	}

	public void go() {

		try {

			mServerSocket = new ServerSocket(mPort);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// server loop
		while (!Thread.interrupted()) {

			Socket socket = null;

			try {

				// block until connection request arrives
				socket = mServerSocket.accept();

				// read request data from client

				mIn = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				// process client message
				process(new DataRecord(mIn.readLine().trim()));

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {

					if (mIn != null)
						mIn.close();
					if (socket != null)
						socket.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			// close the socket down
			mServerSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void process(DataRecord record);

}