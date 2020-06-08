package com.kdj.mlink.ezup.model;

public interface IDataProvider {
	public void addSink(Object sink);
	public void removeSink(Object sink);
	public void run();
	public long getIntervalMilliseconds();
	public long getTimePassedSinceLastRun();
	public long getTimeOverdue();
	public void addDataProviderListener(IDataProviderListener listener);
	public void removeDataProviderListener(IDataProviderListener listener);
	public void bump();
}
