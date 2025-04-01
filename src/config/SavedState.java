public class SavedState {
    private final String fileName;
    private final String apiUrl;
    private final int activeConnections;

    public SavedState(String fileName, String apiUrl, int activeConnections) {
        this.fileName = fileName;
        this.apiUrl = apiUrl;
        this.activeConnections = activeConnections;
    }

    public String getFileName() {
        return fileName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public int getActiveConnections() {
        return activeConnections;
    }
}
