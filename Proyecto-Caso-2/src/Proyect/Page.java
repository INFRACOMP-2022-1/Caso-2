package Proyect;

import java.util.ArrayList;

public class Page {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
     * The int size in bytes
     */
    public int intSize;

    /*
     * The page size in bytes
     */
    public int pageSize;

    /*
     * The number of references in page
     */
    public int capacity;

    /*
     * The list containing the "integers" saved in the page
     */
    public ArrayList<Integer> pageContent;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * Creates a new empty page with a set capacity based on the int size and the page size
     * @param intSize
     * @param pageSize
     * @param capacity
     */
    public Page(int intSize, int pageSize, int capacity) {
        this.intSize = intSize;
        this.pageSize = pageSize;
        this.capacity = capacity;//its pre-calculated based on integer size and page size
        this.pageContent = new ArrayList<>();
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    /**
     * Adds a new integer to the page
     * @param element
     * @throws Exception
     */
    public void addInteger(int element) throws Exception{
        if(pageContent.size()<capacity){
            pageContent.add(element);
        }
        else{
            throw new Exception("Not enough capacity in the page to save current element.");
        }
    }

    public int getIntSize() {
        return intSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Integer> getPageContent() {
        return pageContent;
    }
}
