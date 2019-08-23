package datastructure.heap;

/**
 * A heap element contains two attributes: a key which will be used to build the
 * tree (int or double, either primitive type or object) and any kind of
 * IMMUTABLE object the user sees fit to carry any information he/she likes. Be
 * aware that the use of a mutable object might jeopardize the integrity of this
 * information.
 */
public class HeapElement {

    private final double key;
    private final Object additionalInfo;

    /**
     * constructors
     * 
     * @param key  - a number of primitive type (int/double/Integer/Double)
     * @param info - any kind of IMMUTABLE object. May be null, since the purpose is
     *             only to carry additional information of use for the user
     */
    public HeapElement(int key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    public HeapElement(double key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    public HeapElement(Integer key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    public HeapElement(Double key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    public HeapElement(int key) {
        this.key = key;
        this.additionalInfo = null;
    }

    public HeapElement(double key) {
        this.key = key;
        this.additionalInfo = null;
    }

    public HeapElement(Integer key) {
        this.key = key;
        this.additionalInfo = null;
    }

    public HeapElement(Double key) {
        this.key = key;
        this.additionalInfo = null;
    }

    /**
     * Getters methods
     */
    public double getKey() {
        return key;
    }

    public Object getInfo() {
        return additionalInfo;
    }

    public boolean equals(HeapElement otherHeapElement) {
        return (this.key == otherHeapElement.key) && (this.additionalInfo.equals(otherHeapElement.additionalInfo));
    }
}