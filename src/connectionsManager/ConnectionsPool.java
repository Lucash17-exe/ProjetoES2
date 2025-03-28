package connectionsManager;

import config.ConfigManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ConnectionsPool {
    private static ConnectionsPool singleInstance = null;

    private static ConfigManager configManager = ConfigManager.getInstance();

    private static ArrayList<HttpURLConnection> inUse  = new ArrayList<HttpURLConnection>();
    private static ArrayList<HttpURLConnection> available = new ArrayList<HttpURLConnection>();

    private ConnectionsPool(){}

    public static synchronized ConnectionsPool getInstance()
    {
        if (singleInstance == null)
        {
            singleInstance = new ConnectionsPool();
        }

        return singleInstance;
    }

    public synchronized HttpURLConnection acquire() throws NoConnectionsAvailableException, IOException {
        if (inUse.size() >= configManager.getMaxConnectionPoolSize()) throw new NoConnectionsAvailableException();

        if (available.size() > 0) {
            HttpURLConnection temp = available.get(0);
            inUse.add(temp);
            available.remove(0);
            return temp;
        }

        URL url = new URL(configManager.getApiURL());
        HttpURLConnection newObj = (HttpURLConnection) url.openConnection();
        inUse.add(newObj);
        return newObj;
    }

    public synchronized void resetPool()
    {
        synchronized (this) {
            for (HttpURLConnection conn : inUse) {
                conn.disconnect();
            }
            for (HttpURLConnection conn : available) {
                conn.disconnect();
            }
            inUse.clear();
            available.clear();
        }
    }

    public synchronized void release(HttpURLConnection connection) throws ConnectionNotFoundException, ProtocolException {
        if (inUse.contains(connection)) {
            inUse.remove(connection);

            // Utiliza método personalizado para reset de conexão
            // Connection.disconnect() fecha a conexão, tornando-a inutilizável
            // O método personalizado apenas retorna ao estado padrão, permitindo reutilizar (Objetivo do ObjectPool)
            resetConnection(connection);

            available.add(connection);
        } else {
            throw new ConnectionNotFoundException();
        }
    }

    private void resetConnection(HttpURLConnection conn) throws ProtocolException {
        try {
            if (conn.getInputStream() != null) conn.getInputStream().close();
            if (conn.getOutputStream() != null) conn.getOutputStream().close();
        } catch (IOException ignored) {}
        conn.setRequestProperty("Content-Type", null);
        conn.setRequestMethod("GET");
    }
}
