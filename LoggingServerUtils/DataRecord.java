package edu.umd.cs.cmsc433.LoggingServerUtils;

public class DataRecord {

	final String mSender, mMsg;

	public static final String SEP = "###";

	// Break the request into its part
	
	public DataRecord(String stringRep) {
		String[] parts = stringRep.split(SEP);
		if (parts.length == 2) {
			this.mSender = parts[0];
			this.mMsg = parts[1];
		} else {
			this.mSender = this.mMsg = "";
		}
	}

	public String getSender() {
		return mSender;
	}

	public String getMsg() {
		return mMsg;
	}

	@Override
	public String toString() {
		return mSender + SEP + mMsg;
	}
}
