package br.com.cauag.serena.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleController {
	
	private Map<List<Date>, Integer> scheduled;
	private List<Date> dates;
	private Integer startEndSchedule;
	private boolean scheduling;
	private boolean executing;

	public ScheduleController() {
		this.scheduled = new LinkedHashMap<List<Date>, Integer>();
	}
	
	public void startSchedule(int startIndex) {
		this.dates = new ArrayList<Date>();
		this.startEndSchedule = startIndex;
		this.scheduling = true;
	}
	
	public void addDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("date should not be null.");
		}
		
		dates.add(date);
	}
	
	public void endSchedule() {
		if ((!isExecuting() && dates == null) || dates.isEmpty()) {
			throw new IllegalArgumentException("SCHEDULE statement is needed.");
		}
		
		this.scheduled.put(new ArrayList<>(dates), startEndSchedule);
		this.dates = null;
		this.startEndSchedule = null;
		this.scheduling = false;
	}
	
	public void run(Core core) {		
		for (Map.Entry<List<Date>, Integer> entry : scheduled.entrySet()) {
			List<Date> dates = entry.getKey();
			Integer index = entry.getValue();
			
			for (Date date : dates) {
				Timer timer = new Timer();
				
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						core.scheduleController.on();
						core.index = index;
						core.run();
						timer.cancel();
					}
				};
				
				timer.schedule(task, date);
			}
			
			scheduled.clear();;
		}
	}
	
	public void on() {
		this.executing = true;
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
