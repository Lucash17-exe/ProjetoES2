package stateManager;

import categoriesManager.PasswordManager;

import java.util.Stack;

public class PasswordHistoryManager {
    private Stack<PasswordStateManager> history = new Stack<>();

    public void saveState(PasswordManager manager) {
        history.push(manager.saveState());
    }

    public void undo(PasswordManager manager) {
        if (!history.isEmpty()) {
            manager.restoreState(history.pop());
        }
    }
}
