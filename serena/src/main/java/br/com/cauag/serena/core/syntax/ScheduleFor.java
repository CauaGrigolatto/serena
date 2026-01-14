package br.com.cauag.serena.core.syntax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.core.Core;

public class ScheduleFor extends ParameterReceiver {
	
	public ScheduleFor() {
		super();
	}
	
	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (complement == null || complement.isBlank()) throw new IllegalArgumentException("SCHEDULE statement is needed.");
		
		core.scheduleController.startSchedule(core.index + 1);
		
		List<String> times = getTimes(complement);
		int n = times.size();
		
		SimpleDateFormat formatter = new SimpleDateFormat(
			core.configController.getConfig("DATE_TIME_FORMAT")
		);
		
		for (int i = 0; i < n; i++) {
			String time = times.get(i).trim();
			time = applyParametersAndVariables(time, core);
			Date date = formatter.parse(time);
			core.scheduleController.addDate(date);
		}
		
		return core.index;
	}
	
	private List<String> getTimes(String str) {				
		List<String> times = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
				
		for (boolean canAppend = false; i < str.length(); i++) {
			if (str.charAt(i) == '"') {
				canAppend = !canAppend;
				
				if (! sb.isEmpty()) {
					times.add(sb.toString());
					sb.setLength(0);
				}
			}
			else if (canAppend) {
				sb.append( str.charAt(i) );
			}
		}
		
		return times;
	}
}
