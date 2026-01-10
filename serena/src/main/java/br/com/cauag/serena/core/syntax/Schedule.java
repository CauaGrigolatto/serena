package br.com.cauag.serena.core.syntax;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.cauag.serena.core.Core;

public class Schedule extends ParameterReceiver {
	
	public Schedule() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		core.scheduleController.startSchedule(core.index + 1);
		
		String[] passedArgs = complement.split(",");
		int n = passedArgs.length;
		
		String[] times = new String[n];
		
		SimpleDateFormat formatter = new SimpleDateFormat(
			core.configController.getConfig("DATE_TIME_FORMAT")
		);
		
		for (int i = 0; i < n; i++) {
			times[i] = passedArgs[i].trim();
			Date date = formatter.parse(times[i]);
			core.scheduleController.addDate(date);
		}
		
		return core.index;
	}
}
