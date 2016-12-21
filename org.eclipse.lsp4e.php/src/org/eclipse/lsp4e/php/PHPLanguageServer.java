/*******************************************************************************
 * Copyright (c) 2016 Rogue Wave Software Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Michał Niewrzał (Rogue Wave Software Inc.) - initial implementation
 *******************************************************************************/
package org.eclipse.lsp4e.php;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.lsp4e.LanguageServerPlugin;
import org.eclipse.lsp4e.ProcessStreamConnectionProvider;
import org.osgi.framework.Bundle;

public class PHPLanguageServer extends ProcessStreamConnectionProvider {

	public PHPLanguageServer() {
		List<String> commands = new ArrayList<>();
		commands.add("php");

		Bundle bundle = Activator.getContext().getBundle();
		try {
			Path path = new Path(FileLocator.toFileURL(FileLocator.find(bundle, new Path("server"), null)).getPath());
			commands.add(path.append("/bin/php-language-server.php").toOSString());
			setCommands(commands);
			setWorkingDirectory(path.toOSString());
		} catch (IOException e) {
			LanguageServerPlugin.logError(e);
		}
	}

}
