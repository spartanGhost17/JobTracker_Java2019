import java.time.LocalDateTime;

/**
 * Represents an individual progress update for a job
 * @author Adam
 */
public class JobProgressInfo {
	
	public static final int PROGRESS_STARTED = 0;
	public static final int PROGRESS_FINISHED = 1;
	
	private int jobID;
	private String processTitle;
	private int progressType;	
	private LocalDateTime timestamp;
	
	/**
	 * Constructs a JobProgressInfo object
	 */
	public JobProgressInfo() {
		
	}
	
	/**
	 * Utility constructor for creating a JobProgressInfo object from pre-prepared params
	 */
	public JobProgressInfo(int jobId, String processTitle, int progressType, LocalDateTime timestamp) {
		this.setJobID(jobId);
		this.setProcessTitle(processTitle);
		this.setProgressType(progressType);
		this.setTimestamp(timestamp);
	}

	/**
	 * Returns the ID of the job that this JobProgressInfo is about
	 * @return the ID of the job
	 */
	public int getJobID() {
		return jobID;
	}

	/**
	 * Sets the ID of the job that this JobProgressInfo is about
	 * @param jobID the ID of the job
	 */
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	/**
	 * Returns the name of the process that this JobProgressInfo is referring to
	 * @return the process name
	 */
	public String getProcessTitle() {
		return processTitle;
	}

	/**
	 *  Sets the name of the process that this JobProgressInfo is referring to
	 * @param processTitle the process name
	 */
	public void setProcessTitle(String processTitle) {
		this.processTitle = processTitle;
	}

	/**
	 * Returns either PROGRESS_STARTED or PROGRESS_FINISHED, depending on whether this process has started or finished
	 * @return the progress status code as an integer, either PROGRESS_STARTED or PROGRES_FINISHED
	 */
	public int getProgressType() {
		return progressType;
	}

	/**
	 * Sets the progress status for this JobProgressInfo, either PROGRESS_STARTED or PROGRESS_FINISHED
	 * @param progressType either PROGRESS_STARTED or PROGRESS_FINISHED
	 * @throws IllegalArgumentException if the status code is neither of the above values
	 */
	public void setProgressType(int progressType) {
		if ( progressType != PROGRESS_STARTED && progressType != PROGRESS_FINISHED ) {
			//unchecked exception, no try/catch necessary
			throw new IllegalArgumentException("progressType must be either PROGRESS_STARTED or PROGRESS_FINISHED");
		}
		this.progressType = progressType;
	}
	
	/**
	 * Returns the time that this JobProgressInfo happened
	 * @return the time that this JobProgressInfo happened
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the time that this JobProgressInfo happened
	 * @param timestamp the time of the update, usually LocalDateTime.now()
	 */
	public void setTimestamp(LocalDateTime timestamp) {		
		this.timestamp = timestamp;
	}
}
