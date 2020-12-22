package relations;

public class AssertionKeyValue {
    public boolean isNULL;
    public boolean isFALSUM;

    public Object value;

    public AssertionKeyValue() {
    }

    public AssertionKeyValue(boolean isNULL, boolean isFALSUM, Object value) {
        this.isNULL = isNULL;
        this.isFALSUM = isFALSUM;
        this.value = value;
    }

    public boolean isNULL() {
        return isNULL;
    }

    public void setNULL(boolean NULL) {
        isNULL = NULL;
    }

    public boolean isFALSUM() {
        return isFALSUM;
    }

    public void setFALSUM(boolean FALSUM) {
        isFALSUM = FALSUM;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AssertionKeyValue{" +
                "isNULL=" + isNULL +
                ", isFALSUM=" + isFALSUM +
                ", value=" + value +
                '}';
    }
}
