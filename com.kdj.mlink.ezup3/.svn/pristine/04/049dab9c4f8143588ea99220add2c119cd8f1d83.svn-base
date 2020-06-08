package com.kdj.mlink.ezup3.shop.job;

import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.SimpleThreadPool;

import com.kdj.mlink.ezup3.shop.dao.ScheduleInfoDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;

import static org.quartz.JobBuilder.newJob;

import java.util.List;
import java.util.Properties;


import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class JobContext {
	private static JobContext instance = new JobContext();
	StdSchedulerFactory schedulerFactory = null;
    Scheduler scheduler = null;
    String name ;
    String group;
    ScheduleInfoDto dto;
    List<ShopOrderMstDto> list;
    public static JobContext get() {
//    	if(instance.scheduler ==null)
//    	{
//    	
    		try {
				instance.initDetectionScheduler() ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
            try {
            	System.out.println();
            	instance.scheduler = instance.schedulerFactory.getScheduler();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
    	//}
    	
        return instance;
    }
    
    
    private void initDetectionScheduler() throws Exception{
    	schedulerFactory=new StdSchedulerFactory();
    	Properties mergedProps = new Properties();
    	mergedProps.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, SimpleThreadPool.class.getName());
    	mergedProps.setProperty("org.quartz.scheduler.makeSchedulerThreadDaemon", "true");
    	mergedProps.setProperty("org.quartz.scheduler.instanceName", "UfloClusterHeartbeatScheduler");
    	mergedProps.setProperty("org.quartz.scheduler.instanceId", "UfloHeartbeatDetectionScheduler");
    	mergedProps.setProperty("org.quartz.threadPool.threadCount","10");
    	schedulerFactory.initialize(mergedProps);
    	scheduler=schedulerFactory.getScheduler();
    }

    private  JobContext()   {
    	
    } // 싱글턴 객 체..


   public  JobContext addJobNamGroup(String name , String group, ScheduleInfoDto dto, List<ShopOrderMstDto> list){
        this.name = name;
        this.group = group;
        this.dto = dto;
        this.list = list;
        return this;
   }


    //  Job 을 추가한다.
    public JobContext addJob(Class<? extends Job> jobClass, String cronExpression ) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(this.name, this.dto);
        jobDataMap.put("SHOP", this.list);
        //jobDataMap.put("data", this.dto);
        JobDetail jobDetail = newJob(jobClass)
                .usingJobData(jobDataMap)
                .build();
        System.out.println("트리거시작전 시작");
        
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail) //"*/10 * * * * ?"  10 초마다..
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        scheduler.scheduleJob(jobDetail, trigger);
        
        return this;
    }

    // 스케줄러 실행.
    public void start() throws SchedulerException {
    	
        scheduler.start();
    }


    public  void stop() throws SchedulerException {
        scheduler.shutdown();
    }
}
