public enum OrderStatus {
    PROCESSING("processing"),
    IN_DELIVERY("in delivery"),
    COMPLETED("completed");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
