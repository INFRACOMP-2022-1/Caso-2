package Proyect;

import java.util.ArrayList;

public class Buffer {
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------

    /*
    Page frame numbers. Sets the limit of page references a page frame can hava
     */
    public int pageFrames;

    /*
    This table contains an array of pages and the time since the page was last refrenced
    [[pageNumberReferenced,timeSinceLastReference],...,[,]]
     */
    public ArrayList<ArrayList<Integer>> pageFrameTable;

    /*
    The number of memory failures
     */
    public int memoryFailures;

    /*
    The table of pages referenced, created by the run type in option 1.
     */
    public ArrayList<ElementInfo> referenceTable;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public Buffer(int pageFrames, ArrayList<ElementInfo> referenceTable) {
        this.pageFrames = pageFrames;
        this.pageFrameTable = new ArrayList<>();
        this.memoryFailures = 0;
        this.referenceTable = referenceTable;
    }


    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------


}
