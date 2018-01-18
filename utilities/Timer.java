package utilities;

public class Timer {
	
	private long startTime;
	private long stopTime;
	private long total = 0;
	private boolean stopped = true;
	
	public void start() {
		if(stopped) {
			startTime = System.currentTimeMillis();
			stopped = false;
		}
	}
	
	public void stop() {
		stopTime = System.currentTimeMillis();
		stopped = true;
		total = total + stopTime - startTime;
	}
	
	public void reset() {
		stopped = true;
		startTime = 0;
		stopTime = 0;
	}
	
	public long getTime() {
		return total + System.currentTimeMillis() - startTime;
	}

}
