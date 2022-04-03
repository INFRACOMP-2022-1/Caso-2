package Proyect;

import java.util.ArrayList;

public class Page {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
     * The page number within the context of the page table
     */
    public int pageNumber;

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
     * @param pageNumber
     */
    public Page(int intSize, int pageSize, int capacity, int pageNumber) {
        this.intSize = intSize;
        this.pageSize = pageSize;
        this.capacity = capacity;//its pre-calculated based on integer size and page size
        this.pageNumber = pageNumber;
        this.pageContent = new ArrayList<>();
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    /**
     * Adds a new integer to the page.
     * @param integer the integer that is going to be added to the page
     * @throws Exception If there is not enough capacity in the page for the element it throws an exception
     */
    public void addInteger(int integer) throws Exception{
        if(pageContent.size()<capacity){
            pageContent.add(integer);
        }
        else{
            throw new Exception("Page capacity is not enough. Add to new page");
        }
    }

    /**
     * Gets the displacement within the page, in bytes, of a given integer
     * @param integer the integer whose displacement you want to know
     * @return
     */
    public int getIntegerDisplacement(int integer){
        int position=0;
        try{
            position = getIntegerPosition(integer);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return position*getIntSize();

    }

    /**
     * Gets the position of the integer within the array
     * @param integer the integer whose position you want to know
     * @return the position of the given integer within the array
     * @throws Exception if the integer given isnt in the page
     */
    public int getIntegerPosition(int integer) throws Exception{
        for(int i = 0 ; i < pageContent.size();i++){
            if(pageContent.get(i)==integer){
                return i;
            }
        }
        throw new Exception("The integer is not in this page.");
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
