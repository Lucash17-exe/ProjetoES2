import connectionsManager.ConnectionsPool;
import java.util.Stack;

public class StateManager {
    private static StateManager instance = null;
    private Stack<SavedState> history = new Stack<>();

    private StateManager() {}

    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }

    public void saveState() {
        ConfigManager config = ConfigManager.getInstance();
        ConnectionsPool connections = ConnectionsPool.getInstance();
        history.push(new SavedState(config.getFileName(), config.getApiURL(), connections.getActiveConnections()));
    }

    public void restoreState() {
        if (!history.isEmpty()) {
            SavedState savedState = history.pop();
            ConfigManager config = ConfigManager.getInstance();
            config.setFileName(savedState.getFileName());
        }
    }
}
