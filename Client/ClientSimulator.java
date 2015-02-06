package edu.umd.cs.cmsc433.Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.umd.cs.cmsc433.LoggingServerUtils.DataRecord;

// Driver program to simulate multiple users
public class ClientSimulator {

	final private static int DEFAULT_PORT = 49152;
	public static final int PORT = Integer.getInteger("port", DEFAULT_PORT).intValue();

	int mPort, mRunLength, mNumClients;
	InetAddress mInetAddr;

	ClientSimulator(InetAddress addr, int port, int numClients, int runLength) {

		mPort = port;
		mInetAddr = addr;
		mNumClients = numClients;
		mRunLength = runLength;

	}

	public void go() {

		Socket socket = null;
		PrintWriter outRemote = null;

		for (int i = 0; i < mRunLength; i++) {
			for (int j = 0; j < mNumClients; j++) {
				try {

					// Connect to Server
					socket = new Socket(mInetAddr, mPort);

					// Get and wrap socket output stream
					outRemote = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())),
							true);

					// Write record to Server
					outRemote.println(String.valueOf(i) + DataRecord.SEP
							+ String.valueOf(j));

				} catch (IOException e) {
					System.err.println(e);
				} finally {

					// Close down connect
					if (outRemote != null)
						outRemote.close();
					if (socket != null)
						try {
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

				}
			}
		}
	}

	public static void main(String[] args) {

		InetAddress addrTest;
		long startTime = System.currentTimeMillis();

		try {

			// Create a ClientSimulator
			addrTest = InetAddress.getByName(System.getProperty("host"));
			ClientSimulator testClient = new ClientSimulator(addrTest, PORT,
					25, 25);

			// Start the simulation
			testClient.go();

		} catch (UnknownHostException e) {

			System.err.println(e);

		}

		// Print out run time
		long endTime = System.currentTimeMillis();
		System.out.println("Elapsed Time:" + (endTime - startTime));

	}

}