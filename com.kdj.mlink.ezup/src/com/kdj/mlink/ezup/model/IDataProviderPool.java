package com.kdj.mlink.ezup.model;
import java.util.List;

public interface IDataProviderPool {
	public int getProviderCount();
	public void addProvider(IDataProvider provider);
	public void removeProvider(IDataProvider provider);
	public void setIntervalMilliseconds(long interval);
	public long getIntervalMilliseconds();
	public List<IDataProvider> getProviders();
	public void destroy();
}
