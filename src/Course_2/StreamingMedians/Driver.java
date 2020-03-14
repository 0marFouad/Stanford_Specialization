package Course_2.StreamingMedians;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args){
        ArrayList<Integer> data = readData();
        long answer = 0;
        Median_Maintenance solver = new Median_Maintenance();
        for(int i=0;i<data.size();i++){
            solver.addNumber(data.get(i));
            answer += solver.getMedian()%10000;
            answer %= 10000;
        }
        System.out.println(answer);
    }


    public static ArrayList<Integer> readData(){
        ArrayList<Integer> data = new ArrayList<>();
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_2/StreamingMedians/medians.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                int from = Integer.parseInt(st);
                data.add(from);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }
}
