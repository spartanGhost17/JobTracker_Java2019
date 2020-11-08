import java.util.Arrays;

import com.db4o.*;
//import java.util.Arrays;
import java.util.Comparator;

/**
 * DB4o-backed store for JobProgressInfo objects, featuring a variety of methods to find progress on jobs
 * @author Adam
 *
 */
public class JobProgressTracker {

	private ObjectContainer db;
	
	/**
	 * Constructs a new tracker, opening or creating the specified database file
	 * @param filename the database file to open or create
	 */
	public JobProgressTracker(String filename) {
		db = Db4oEmbedded.openFile(filename);
	}
	
	/**
	 * Updates the tracker on the progress of a job
	 * @param The JobProgressInfo object representing our update
	 */
	public void processProgressUpdate(JobProgressInfo update) {
		//just put it in the database
		db.store(update);
	}
	
	/**
	 * Gets all of the JobProgressInfo objects associated with a particular job ID
	 * @param jobID the ID of the job whose progress you'd like to find out about
	 * @return An array of JobProgressInfo objects for the specified job, which will be empty if no progress has been tracked
	 */
	public JobProgressInfo[] getJobProgress(int jobID) {
		//create a blank info object, and set the right job ID
		JobProgressInfo blank = new JobProgressInfo();
		blank.setJobID(jobID);
		
		//query the database for all objects matching the blank
		ObjectSet<JobProgressInfo> resultset = db.queryByExample(blank);
		
		//put the results into an array
		JobProgressInfo[] results = new JobProgressInfo[resultset.size()];		
		for(int i=0;i<resultset.size();i++) {
			results[i] = resultset.get(i);
		}
		
		return results;
	}
	public JobProgressInfo[]  getProgressByProcess(String processName)
	{
		//create a blank info object, and set the process title
		JobProgressInfo blank = new JobProgressInfo();
		blank.setProcessTitle(processName);
		
		//query the database for all objects matching the blank
		ObjectSet<JobProgressInfo> processSet = db.queryByExample(blank);
		
		//put all the objects result into an array 
		JobProgressInfo[] process = new JobProgressInfo[processSet.size()];
		for(int i =0; i<process.length; i++)
		{
			process[i] = processSet.get(i);
		}
		
		return process;
	}
	
	public JobProgressInfo[] getDespatchedJobsInfo()
	{
		JobProgressInfo blank = new JobProgressInfo();
		blank.setProgressType(1);
		
		ObjectSet<JobProgressInfo> type = db.queryByExample(blank);
		JobProgressInfo[] type1 = new JobProgressInfo[type.size()];
		for(int i = 0; i < type.size(); i++)
		{
			type1[i] = type.get(i);
		}
		Arrays.sort(type1, Comparator.comparing(JobProgressInfo::getTimestamp));
		return type1;
			
	}
	/**
	 * Removes all of the stored JobProgressInfo objects from the tracker, and from the database file
	 */
	public void clear() {
		ObjectSet<JobProgressInfo> objects = db.queryByExample(new JobProgressInfo());
		for(JobProgressInfo object : objects) {
			db.delete(object);
		}
	}
	
	/**
	 * Closes the db4o database
	 */
	public void close() {
		db.close();
	}
	
}
