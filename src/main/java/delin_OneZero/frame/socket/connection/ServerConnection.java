package delin_OneZero.frame.socket.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import delin_OneZero.frame.socket.connection.thread.HandlerThread;

public class ServerConnection implements Runnable{
		private static ServerConnection connection;
		private static ExecutorService exec;
		private int port;
		private Thread current;
		private ServerConnection(int port) {
			this.port=port;
		}
		
		public static void createConnection(int port) {
			if (connection==null) {
				connection=new ServerConnection(port);
			}
			if (exec==null||exec.isShutdown()) {
				exec=Executors.newCachedThreadPool();
			}
			exec.execute(connection);
		}
		
		public static ServerConnection getConnection(boolean create) {
			if (connection==null&&create) {
				createConnection(8088);
			}
			return connection;
		}
		private void connect(int port) {
			current=Thread.currentThread();
			try(ServerSocket serverSocket = new ServerSocket(port)){
				while (true) {
					Socket client = serverSocket.accept();
					exec.execute(new HandlerThread(client));
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				destroy();
			}
		}
		
		public void setPort(int port) {
			this.port=port;
		}
		
		public void destroy() {
			if (exec!=null&&!exec.isShutdown()) {
				exec.shutdownNow();
			}
			exec=null;
			connection=null;
		}
		public void stop() {
			current.interrupt();
		}
		@Override
		public void run() {
			connect(port);		
		}

}
