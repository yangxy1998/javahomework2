package util;

import vo.StockInfo;

public class StockSorterImpl implements StockSorter {
    /**
     * Accepting series of stock info, sorting them ascending according to their comment length.
     * List.sort() or Arrays.sort() are not allowed.
     * You have to write your own algorithms,Bubble sort、quick sort、merge sort、select sort,etc.
     *
     * @param info stock information
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info) {//从大到小排列
        //TODO: write your code here
        QuickDescendingsort(info,1,10000);
        return info;
    }

    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info, boolean order) {
        //TODO: write your code here
        if(order) {//order=true,升序快速排列
            QuickAscendingsort(info, 1, 10000);
        }
        else{//order=false,降序快速排列
            QuickDescendingsort(info, 1, 10000);
        }
        return info;
    }

    void QuickAscendingsort(StockInfo[] a, int low, int high)
    {
        if(low >= high)
        {
            return;
        }
        int first = low;
        int last = high;
        StockInfo key = new StockInfo(a[first]);
                /*用字表的第一个记录作为基准*/

        while(first < last)
        {
            while(first < last && a[last].getAnswerLength() >= key.getAnswerLength())
            {
                --last;
            }

            a[first] = new StockInfo(a[last]);
                    /*将比第一个小的移到低端*/

            while(first < last && a[first].getAnswerLength() <= key.getAnswerLength())
            {
                ++first;
            }

            a[last] = new StockInfo(a[first]);
                    /*将比第一个大的移到高端*/
        }
        a[first] = new StockInfo(key);/*基准记录*/
        QuickAscendingsort(a, low, first-1);
        QuickAscendingsort(a, first+1, high);
    }
    void QuickDescendingsort(StockInfo[] a, int low, int high)
    {
        if(low >= high)
        {
            return;
        }
        int first = low;
        int last = high;
        StockInfo key = new StockInfo(a[first]);
                /*用字表的第一个记录作为基准*/

        while(first < last)
        {
            while(first < last && a[last].getAnswerLength() <= key.getAnswerLength())
            {
                --last;
            }

            a[first] = new StockInfo(a[last]);
                    /*将比第一个大的移到低端*/

            while(first < last && a[first].getAnswerLength() >= key.getAnswerLength())
            {
                ++first;
            }

            a[last] = new StockInfo(a[first]);
                    /*将比第一个小的移到高端*/
        }
        a[first] = new StockInfo(key);/*基准记录*/
        QuickDescendingsort(a, low, first-1);
        QuickDescendingsort(a, first+1, high);
    }
}
