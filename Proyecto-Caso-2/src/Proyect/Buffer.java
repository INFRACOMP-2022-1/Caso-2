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
    The number of page errors (fallos de pagina)
     */
    public int pageErrors;

    /*
    The table of content
     */
    //TODO: Santiago elabora esto
    public ArrayList<Integer> contentTable;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * Constructor for the buffer. It guards the pageFrameTable
     * @param pageFrames the number of page frames for the page frame table
     */
    public Buffer(int pageFrames) {
        this.pageFrames = pageFrames;//marcos pagina
        this.pageFrameTable = new ArrayList<>();//TP in the original buffer
        this.pageErrors = 0;//Num fallos
        this.contentTable = new ArrayList<>();//Content page in the original buffer

        initializePageFrameTableValues();//inserts the tuple that handles the refrences to the pages in the frame and the time since last reference
    }

    //--------------------------------------------------------------------------
    // Synced Methods
    //--------------------------------------------------------------------------

    public synchronized void addPage(int pageNumber){
        while(!canAddNewPage()){
           try {
               wait();
           }
           catch (InterruptedException e){
               e.printStackTrace();
           }
        }

        insertPage(pageNumber);

        synchronized (this){
            notify();
        }
    }

    public synchronized void age(){
        while(!canAddNewPage()){
            try {
                wait();//incoming message will wait until someone wakes it up
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        makeOlder();

        synchronized (this){
            notify();
        }
    }


    //--------------------------------------------------------------------------
    // Normal Methods
    //--------------------------------------------------------------------------

    /**
     * Ages a page in the page frame table by one unit
     */
    public void makeOlder(){
        for(int i = 0; i < pageFrameTable.size();i++){
            if(getPageInFrameTable(i) != -1){
                int number = getPageLastTimeSinceReferenceInFrameTable(i);
                number += 1;
                setPageLastTimeSinceReferenceInFrameTable(i,number);
            }
        }
    }

    /**
     * Initializes all the values according to the specified number of frames and initialices the <page,timeSinceLastReference> tuple within the array
     */
    public void initializePageFrameTableValues(){
        for(int i = 0; i < pageFrames;i++){
            int page = -1; //initializes all page references as -1 (null)
            int timeSinceLastReference = 0; //edad in original buffer. All are initialy 0

            ArrayList<Integer> frame = new ArrayList<>();//marco in original buffer

            frame.add(page);//page is in pos 0 within the internal tuples
            frame.add(timeSinceLastReference);//time since last reference is in pos 1 within the internal tuple

            pageFrameTable.add(frame);//adds tuple of frame to page frame table
        }
    }

    /**
     * Flattens out the original page reference table to just be an int array of the referenced pages
     * @param originalReferenceTable Original reference table with ElementInfo (basically the page referenced and other useful data such as displacement and originally referenced element of the matrix)
     * @return
     */
    public ArrayList<Integer> convertOriginalReferenceTableToFlatReferenceTable(ArrayList<ElementInfo> originalReferenceTable){
        ArrayList<Integer> flatReferenceTable = new ArrayList<>();

        for(int i = 0; i < originalReferenceTable.size();i++){
            flatReferenceTable.add(originalReferenceTable.get(i).getPageNumber());
        }
        return flatReferenceTable;
    }


    /**
     * Checks if it is possible to add a new page to the page frame table without generating a memory failure (fallo de pagina)
     * comprobación() in original buffer
     * @return boolean. True if it is possible to add a new page, false the contrary.
     */
    public boolean canAddNewPage(){

        for(int i =0;i < pageFrameTable.size(); i++){
            //TODO: V -> No entiendo tanto la logica de este if , no seria siempre true?
            if(getPageInFrameTable(i) == -1|| getPageInFrameTable(i) != -1){
                return true;
            }
        }
        return false;
    }


    public void insertPage(int pageNumber){
        boolean centinel = true;
        int num = 0;

        if(contentTable.size()< pageFrames || contentTable.contains(pageNumber) == true){
            if(canAddNewPage()){
                while(centinel == true && num < pageFrameTable.size()){
                    //finds empty space where it can add the page
                    if(getPageInFrameTable(num) == -1){
                        setPageInFrameTable(num,pageNumber);
                        centinel = false;
                        contentTable.add(pageNumber);
                    }

                    //finds an older reference to the current pageNumber, so it updates the time since last reference to 0
                    if(getPageInFrameTable(num) == pageNumber){
                        setPageLastTimeSinceReferenceInFrameTable(num,0);
                        centinel = false;
                    }

                    num += 1;
                }

                //TODO: V -> Tampoco entiendo la nesecidad de esto
                num = 0;
                centinel = true;
            }
            else {
                pageError(pageNumber);
            }

        }
    }

    //TODO: V -> Explicame todo este metodo
    public void pageError(int pageNumber){
        int num = 0;
        int indicated = 0;

        for(int i=0; i < pageFrameTable.size();i++){
            if(getPageLastTimeSinceReferenceInFrameTable(i) > num){
                num = getPageLastTimeSinceReferenceInFrameTable(i);
                indicated = getPageInFrameTable(i);
            }
        }

        for(int j = 0; j < contentTable.size();j++){
            if(contentTable.get(j) ==indicated){
                contentTable.remove(j);
            }
        }

        for(int k = 0; k < pageFrameTable.size(); k++){
            if(getPageInFrameTable(k) == indicated){
                setPageInFrameTable(k,pageNumber);
                setPageLastTimeSinceReferenceInFrameTable(k,0);
                contentTable.add(pageNumber);
            }
        }

        pageErrors += 1;
    }

    /**
     * Gets the page in theg selected frameIndex of the page frame table
     * @param frameIndex the frame index
     * @return the page in the selected frame index
     */
    public int getPageInFrameTable(int frameIndex){
        return pageFrameTable.get(frameIndex).get(0);
    }

    /**
     * Replaces value of page number in the selected frame index of the page frame table
     * @param frameIndex the index of the page frame table where the page wants to be changed
     * @param newPageNumber the new page number set for that frame in the page frame table
     */
    public void setPageInFrameTable(int frameIndex, int newPageNumber){
        pageFrameTable.get(frameIndex).set(0,newPageNumber);
    }

    public void setPageLastTimeSinceReferenceInFrameTable(int frameIndex, int newLastTimeSinceReference){
        pageFrameTable.get(frameIndex).set(1,newLastTimeSinceReference);
    }

    public int getPageLastTimeSinceReferenceInFrameTable(int frameIndex){
        return pageFrameTable.get(frameIndex).get(1);
    }

    public int getPageFrames() {
        return pageFrames;
    }

    public ArrayList<ArrayList<Integer>> getPageFrameTable() {
        return pageFrameTable;
    }

    public int getPageErrors() {
        return pageErrors;
    }

    public ArrayList<Integer> getContentTable() {
        return contentTable;
    }
}
