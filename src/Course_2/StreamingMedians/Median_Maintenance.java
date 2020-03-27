package Course_2.StreamingMedians;
import java.util.Collections;
import java.util.PriorityQueue;

public class Median_Maintenance {
    PriorityQueue<Integer> maxPQ;
    PriorityQueue<Integer> minPQ;
    int size;

    Median_Maintenance(){
        maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        minPQ = new PriorityQueue<>();
        size = 0;
    }

    public void addNumber(int num){
        if(size==0){
            maxPQ.add(num);
        }else if(size == 1){
            int cur = maxPQ.isEmpty() ? minPQ.poll() : maxPQ.poll();
            int small = Math.min(cur, num);
            int large = Math.max(cur, num);
            minPQ.add(large);
            maxPQ.add(small);
        }else{
            if(num > minPQ.peek()){
                minPQ.add(num);
            }else if(num < maxPQ.peek()){
                maxPQ.add(num);
            }else{
                maxPQ.add(num);
            }
        }
        while(minPQ.size() > maxPQ.size()){
            maxPQ.add(minPQ.poll());
        }
        while(maxPQ.size() > minPQ.size() + 1){
            minPQ.add(maxPQ.poll());
        }
        size++;
    }

    public int getMedian(){
        return maxPQ.peek();
    }
}
