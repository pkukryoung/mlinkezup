package org.eclipse.equinox.p2.examples.rcp.cloud;

import org.eclipse.equinox.p2.examples.rcp.cloud.p2.CloudPolicy;
import org.eclipse.equinox.p2.ui.Policy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.equinox.p2.examples.rcp.cloud";

	// The shared instance
	private static Activator plugin;

	ServiceRegistration<Policy> policyRegistration;
	CloudPolicy policy;
	IPropertyChangeListener preferenceListener;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		// XXX register the p2 UI policy
		registerP2Policy(context);
		getPreferenceStore().addPropertyChangeListener(getPreferenceListener());
	}

	private IPropertyChangeListener getPreferenceListener() {
		if (preferenceListener == null) {
			preferenceListener = event -> policy.updateForPreferences();
		}
		return preferenceListener;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		// XXX unregister the UI policy
		policyRegistration.unregister();
		policyRegistration = null;
		getPreferenceStore().removePropertyChangeListener(preferenceListener);
		preferenceListener = null;
		super.stop(context);

	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative
	 * path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	private void registerP2Policy(BundleContext context) {
		policy = new CloudPolicy();
		policy.updateForPreferences();
		policyRegistration = context.registerService(Policy.class, policy, null);
	}
}
