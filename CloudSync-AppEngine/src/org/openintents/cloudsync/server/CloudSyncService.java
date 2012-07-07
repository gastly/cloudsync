package org.openintents.cloudsync.server;

import java.util.List;

import org.openintents.cloudsync.annotation.ServiceMethod;

import com.ibm.icu.util.Calendar;




public class CloudSyncService {

	static DataStore db = new DataStore();

	@ServiceMethod
	public Task createTask() {
		return db.update(new Task());
	}

	@ServiceMethod
	public Task readTask(Long id) {
		return db.find(id);
	}

	@ServiceMethod
	public Task updateTask(Task task) {
		//this is where you can put c2dm message
		task.setEmailAddress(DataStore.getUserEmail());
		task = db.update(task);
		//DataStore.sendC2DMUpdate(TaskChange.UPDATE + TaskChange.SEPARATOR + task.getId());
		//In the above line the TaskChange.UPDATE only means "update" string and separator is :
		return task;
	}

	@ServiceMethod
	public void deleteTask(Task task) {
		db.delete(task.getId());

	}

	@ServiceMethod
	public List<Task> queryTasks() {
		return db.findAll();
	}
	
	@ServiceMethod
	public Long getAppEngineTime() {
		//Calendar cal = Calendar.getInstance();
		//return cal.getTimeInMillis();
		return (long) DataStore.getTime();
	}
	
	@ServiceMethod
	public List<Task> queryTasks(String packageName) {
		return db.findAll(packageName);
	}
	
	@ServiceMethod
	public List<Task> queryTasks(String packageName, Long timestamp) {
		return db.findAll(packageName,timestamp);
	}
	
	@ServiceMethod
	public List<Task> queryExactTimeStamp(String packageName, Long timestamp) {
		return db.findExactTimeStamp(packageName,timestamp);
	}
	
	@ServiceMethod
	public List<Task> queryGoogleIdList(String packageName, List<Long> idList) {
		return db.findGoogleIdList(packageName,idList);
	}
	
	@ServiceMethod
	public Integer deleteAll(String packageName) {
		return db.purgeAll(packageName);
	}
	
	
	

}
