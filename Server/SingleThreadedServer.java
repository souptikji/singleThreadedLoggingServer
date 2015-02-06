package edu.umd.cs.cmsc433.Server;

import edu.umd.cs.cmsc433.LoggingServerUtils.DataRecord;
import edu.umd.cs.cmsc433.LoggingServerUtils.LoggingServerCore;
import edu.umd.cs.cmsc433.LoggingServerUtils.MsgHandler;


public class SingleThreadedServer extends LoggingServerCore {

	// sets up server listening on Port "portNum"
	SingleThreadedServer(int portNum) {
		super(portNum);
	}


	// Process one request
	public void process(DataRecord record) {

		(new MsgHandler (record)).run();
	
	}
	
	public static void main(String[] args) {

		
		// Create and start the server
		SingleThreadedServer s = new SingleThreadedServer(DEFAULT_PORT);

		System.out.println("Starting server on port:" + DEFAULT_PORT);

		s.go();
		
		System.out.println("Shutting down server on port:" + DEFAULT_PORT);

	}
}