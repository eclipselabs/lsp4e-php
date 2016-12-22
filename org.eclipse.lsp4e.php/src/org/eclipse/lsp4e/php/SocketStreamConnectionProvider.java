package org.eclipse.lsp4e.php;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.eclipse.lsp4e.ProcessStreamConnectionProvider;

@SuppressWarnings("restriction")
public class SocketStreamConnectionProvider extends ProcessStreamConnectionProvider {

	private int port;
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;

	public SocketStreamConnectionProvider(List<String> commands, String workingDir, int port) {
		super(commands, workingDir);
		this.port = port;
	}

	@Override
	public void start() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(port);
		Thread socketThread = new Thread(() -> {
			try {
				socket = serverSocket.accept();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		socketThread.start();
		super.start();
		try {
			socketThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}

	@Override
	public void stop() {
		super.stop();
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
