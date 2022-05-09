package Proyect;

/**
 * Element info
 */
public class ElementInfo {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
     * The value of the integer
     */
    public int integerValue;

    /*
     * The displacement in bytes of the integer
     */
    public int displacement;

    /*
     * The page number where the integer is located
     */
    public int pageNumber;

    /*
     * The position within the page array that the element is located in
     */
    public int integerPosition;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public ElementInfo(int integerValue, int displacement, int pageNumber, int integerPosition) {
        this.integerValue = integerValue;
        this.displacement = displacement;
        this.pageNumber = pageNumber;
        this.integerPosition = integerPosition;
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------


    public int getIntegerValue() {
        return integerValue;
    }

    public int getDisplacement() {
        return displacement;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getIntegerPosition() {
        return integerPosition;
    }
}
