package vprus.dsf.template;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class Activator extends AbstractUIPlugin {

	private static Activator instance;
	
	public static Activator getDefault() {
		return instance;
	}
	
	public Activator() {
		instance = this;
	}	
}
