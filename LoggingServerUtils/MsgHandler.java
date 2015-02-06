package edu.umd.cs.cmsc433.LoggingServerUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Encapsulate the logic for processing a request
public class MsgHandler implements Runnable {

	protected final DataRecord mRecord;
	protected PrintWriter mOut;

	public MsgHandler(DataRecord record) {
		this.mRecord = record;
	}

	public void run() {

		System.out.println("Server Side: writing record:" + mRecord);
	
		writeMsg();
	
	}

	protected void writeMsg() {
		try {
						
			mOut = new PrintWriter(new FileWriter(mRecord.getSender()
					+ ".txt", true));
			mOut.print("Begin Record:");
			mOut.flush();
			mOut.print("Sender:" + mRecord.getSender() + ":");
			mOut.flush();
			mOut.print("Message:" + mRecord.getMsg() + ":");
			mOut.flush();
			mOut.println("End Record");
			mOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			mOut.close();
		}
	}
}