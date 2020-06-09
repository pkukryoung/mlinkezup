/*******************************************************************************
 *  Copyright (c) 2006, 2017 IBM Corporation and others.
 *
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.internal.p2.jarprocessor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PackStep extends CommandStep {

	protected static String packCommand = null;
	private static Boolean canPack = null;

	private Set<String> exclusions = Collections.emptySet();

	public static boolean canPack() {
		if (canPack != null)
			return canPack.booleanValue();

		String[] locations = Utils.getPack200Commands("pack200"); //$NON-NLS-1$
		if (locations == null) {
			canPack = Boolean.FALSE;
			packCommand = null;
			return false;
		}

		int result;
		for (String location : locations) {
			if (location == null) {
				continue;
			}
			result = execute(new String[]{location, "-V"}); //$NON-NLS-1$
			if (result == 0) {
				packCommand = location;
				canPack = Boolean.TRUE;
				return true;
			}
		}

		canPack = Boolean.FALSE;
		return false;
	}

	public PackStep(Properties options) {
		super(options, null, null, false);
		exclusions = Utils.getPackExclusions(options);
	}

	public PackStep(Properties options, boolean verbose) {
		super(options, null, null, verbose);
		exclusions = Utils.getPackExclusions(options);
	}

	@Override
	public String recursionEffect(String entryName) {
		if (canPack() && entryName.endsWith(".jar") && !exclusions.contains(entryName)) { //$NON-NLS-1$
			return entryName + Utils.PACKED_SUFFIX;
		}
		return null;
	}

	@Override
	public File preProcess(File input, File workingDirectory, List<Properties> containers) {
		return null;
	}

	@Override
	public File postProcess(File input, File workingDirectory, List<Properties> containers) {
		if (canPack() && packCommand != null) {
			Properties inf = Utils.getEclipseInf(input, verbose);
			if (!shouldPack(input, containers, inf))
				return null;
			File outputFile = new File(workingDirectory, input.getName() + Utils.PACKED_SUFFIX);
			try {
				String[] cmd = getCommand(input, outputFile, inf, containers);
				int result = execute(cmd, verbose);
				if (result != 0 && verbose)
					System.out.println("Error: " + result + " was returned from command: " + Utils.concat(cmd)); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (IOException e) {
				if (verbose)
					e.printStackTrace();
				return null;
			}
			return outputFile;
		}
		return null;
	}

	protected boolean shouldPack(File input, List<Properties> containers, Properties inf) {
		//1: exclude by containers
		// innermost jar is first on the list, it can override outer jars
		for (Properties container : containers) {
			if (container.containsKey(Utils.MARK_EXCLUDE_CHILDREN_PACK)) {
				if (Boolean.parseBoolean(container.getProperty(Utils.MARK_EXCLUDE_CHILDREN_PACK))) {
					if (verbose)
						System.out.println(input.getName() + " is excluded from pack200 by its containers."); //$NON-NLS-1$
					return false;
				}
				break;
			}
		}

		//2: excluded by self
		if (inf != null && inf.containsKey(Utils.MARK_EXCLUDE_PACK) && Boolean.parseBoolean(inf.getProperty(Utils.MARK_EXCLUDE_PACK))) {
			if (verbose)
				System.out.println("Excluding " + input.getName() + " from " + getStepName()); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}

		return true;
	}

	protected String[] getCommand(File input, File outputFile, Properties inf, List<Properties> containers) throws IOException {
		String[] cmd = null;
		String arguments = getArguments(input, inf, containers);
		if (arguments != null && arguments.length() > 0) {
			String[] args = Utils.toStringArray(arguments, ","); //$NON-NLS-1$
			cmd = new String[3 + args.length];
			cmd[0] = packCommand;
			System.arraycopy(args, 0, cmd, 1, args.length);
			cmd[cmd.length - 2] = outputFile.getCanonicalPath();
			cmd[cmd.length - 1] = input.getCanonicalPath();
		} else {
			cmd = new String[] {packCommand, outputFile.getCanonicalPath(), input.getCanonicalPath()};
		}
		return cmd;
	}

	protected String getArguments(File input, Properties inf, List<Properties> containers) {
		//1: Explicitly marked in our .inf file
		if (inf != null && inf.containsKey(Utils.PACK_ARGS)) {
			return inf.getProperty(Utils.PACK_ARGS);
		}

		//2: Defaults set in one of our containing jars
		for (Properties container : containers) {
			if (container.containsKey(Utils.DEFAULT_PACK_ARGS)) {
				return container.getProperty(Utils.DEFAULT_PACK_ARGS);
			}
		}

		//3: Set by name in outside pack.properties file
		Properties options = getOptions();
		String argsKey = input.getName() + Utils.PACK_ARGS_SUFFIX;
		if (options.containsKey(argsKey)) {
			return options.getProperty(argsKey);
		}

		//4: Set by default in outside pack.properties file
		if (options.containsKey(Utils.DEFAULT_PACK_ARGS)) {
			return options.getProperty(Utils.DEFAULT_PACK_ARGS);
		}

		return ""; //$NON-NLS-1$
	}

	@Override
	public String getStepName() {
		return "Pack"; //$NON-NLS-1$
	}

	@Override
	public boolean adjustInf(File input, Properties inf, List<Properties> containers) {
		if (input == null || inf == null)
			return false;

		//don't be verbose to check if we should mark the inf
		boolean v = verbose;
		verbose = false;
		if (!shouldPack(input, containers, inf)) {
			verbose = v;
			return false;
		}
		verbose = v;

		//mark as conditioned if not previously marked.  A signed jar is assumed to be previously conditioned.
		if (inf.getProperty(Utils.MARK_PROPERTY) != null)
			return false;

		inf.put(Utils.MARK_PROPERTY, "true"); //$NON-NLS-1$
		//record arguments used
		String arguments = inf.getProperty(Utils.PACK_ARGS);
		if (arguments == null) {
			arguments = getArguments(input, inf, containers);
			if (arguments != null && arguments.length() > 0)
				inf.put(Utils.PACK_ARGS, arguments);
		}
		return true;
	}
}
