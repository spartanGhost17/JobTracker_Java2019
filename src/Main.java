import java.time.LocalDateTime;


/**
 * Simple demo program to show the JobProgressTracker in action
 * @author Adam
 *
 */
public class Main {

	public static void main(String[] args) {
		JobProgressTracker tracker = new JobProgressTracker("cakes.db");
		
		//uncomment this to empty the file if its getting clumsy
		//you could just delete cakes.db, if you prefer
		tracker.clear();
		
		JobProgressInfo progress = new JobProgressInfo(1234,"Baking",JobProgressInfo.PROGRESS_STARTED,LocalDateTime.now().minusMinutes(30));
		JobProgressInfo progress2 = new JobProgressInfo(1234,"Baking",JobProgressInfo.PROGRESS_FINISHED,LocalDateTime.now().minusMinutes(5));
		
		JobProgressInfo progress6 = new JobProgressInfo(1234,"packaging",JobProgressInfo.PROGRESS_FINISHED,LocalDateTime.now().plusMinutes(30));
		JobProgressInfo progress3 = new JobProgressInfo(2345,"Baking",JobProgressInfo.PROGRESS_STARTED,LocalDateTime.now());
		JobProgressInfo progress4 = new JobProgressInfo(1234,"Icing",JobProgressInfo.PROGRESS_STARTED,LocalDateTime.now());
		JobProgressInfo progress5 = new JobProgressInfo(1234,"Icing",JobProgressInfo.PROGRESS_FINISHED,LocalDateTime.now().plusMinutes(10));
		
		
		tracker.processProgressUpdate(progress);
		tracker.processProgressUpdate(progress2);
		tracker.processProgressUpdate(progress3);
		tracker.processProgressUpdate(progress4);
		tracker.processProgressUpdate(progress5);
		tracker.processProgressUpdate(progress6);
		
		JobProgressInfo[] info = tracker.getJobProgress(1234);
		
		
		for(int i=0;i<info.length;i++) {
			String status = " finished ";
			if ( info[i].getProgressType() == JobProgressInfo.PROGRESS_STARTED ) {
				status = " started ";
			}
			System.out.println("Job "+ info[i].getJobID() + status + info[i].getProcessTitle() + " at "  + info[i].getTimestamp());			
		}
		String processName = "Icing";
		JobProgressInfo[] myProcess = tracker.getProgressByProcess(processName);
		for(int i = 0; i < myProcess.length; i++)
		{
			if(myProcess[i].getProcessTitle() == processName)
			{
				System.out.println("job " + myProcess[i].getJobID()+ " " + myProcess[i].getProcessTitle()+ " " + myProcess[i].getTimestamp());
			}
			
		}
		System.out.println("///////////////////////////////");
		
		JobProgressInfo[] myDespacthed = tracker.getDespatchedJobsInfo();
		for(JobProgressInfo d : myDespacthed)
			System.out.println(d.getJobID()+ " " + d.getProcessTitle()+ " " + d.getProgressType());
		
		tracker.close();
	}

}
