package model;

public enum TransactionStatus {
COMPLETE(0),FAIL(1),IN_PROGRESS(2);

    private Integer value;

    TransactionStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
