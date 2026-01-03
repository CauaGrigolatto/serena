package br.com.cauag.serena.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleController {
	
	private Map<List<Date>, Integer[]> scheduled;
	private List<Date> dates;
	private Integer[] startEndSchedule;
	private boolean scheduling;
	private boolean executing;

	public ScheduleController() {
		this.scheduled = new LinkedHashMap<List<Date>, Integer[]>();
	}
	
	public void startSchedule(int startIndex) {
		this.dates = new ArrayList<Date>();
		this.startEndSchedule = new Integer[2];
		this.startEndSchedule[0] = startIndex;
		this.scheduling = true;
	}
	
	public void addDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("date should not be null.");
		}
		
		dates.add(date);
	}
	
	public void endSchedule(int endIndex) {
		if (dates.isEmpty()) {
			throw new IllegalArgumentException("SCHEDULE statement is needed.");
		}
		
		this.scheduled.put(new ArrayList<>(dates), startEndSchedule);
		this.dates = null;
		this.startEndSchedule = null;
		this.scheduling = false;
	}
	
	public void run(Core core) {
		this.executing = true;
		
		for (Map.Entry<List<Date>, Integer[]> entry : scheduled.entrySet()) {
			List<Date> dates = entry.getKey();
			Integer[] indexes = entry.getValue();
			
			for (Date date : dates) {
				Timer timer = new Timer();
				
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						core.index = indexes[0];
						core.run();
						timer.cancel();
					}
				};
				
				timer.schedule(task, date);
			}
			
			scheduled.clear();;
		}
	}
	
	public void shutdown() {
		this.executing = false;
	}
	
	public boolean isScheduling() {
		return scheduling;
	}

	public boolean isExecuting() {
		return executing;
	}
}
