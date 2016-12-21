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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.lsp4e.ProcessStreamConnectionProvider;

public class PHPLanguageServer extends ProcessStreamConnectionProvider {

	private static final IPath LANGUAGE_SERVER_PATH = new Path("/home/wywrzal/git/php-language-server");

	public PHPLanguageServer() {
		List<String> commands = new ArrayList<>();
		commands.add("php");
		commands.add(LANGUAGE_SERVER_PATH.append("/bin/php-language-server.php").toOSString());
		setCommands(commands);
		setWorkingDirectory(LANGUAGE_SERVER_PATH.toOSString());
	}

}
