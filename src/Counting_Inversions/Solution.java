package Counting_Inversions;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static class data{
        int start;
        int end;
        long conversions;
        data(int start,int end, long conversions){
            this.start = start;
            this.end = end;
            this.conversions = conversions;
        }
    }

    public static List<Integer> readData(){
        List<Integer> data = new ArrayList<>();
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Counting_Inversions/Data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                data.add(Integer.parseInt(st));
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static data combine(List<Integer> givenList,data firstHalf, data secondHalf){
        List<Integer> mergedData = new ArrayList<>();
        long conversions = 0;
        int ptr1 = firstHalf.start;
        int ptr2 = secondHalf.start;
        while(ptr1 <= firstHalf.end && ptr2 <= secondHalf.end){
            if(givenList.get(ptr1) <= givenList.get(ptr2)){
                ptr1++;
                mergedData.add(givenList.get(ptr1-1));
            }else{
                ptr2++;
                mergedData.add(givenList.get(ptr2-1));
                conversions += firstHalf.end - ptr1 + 1;
            }
        }
        while(ptr2 <= secondHalf.end){
            ptr2++;
            mergedData.add(givenList.get(ptr2-1));
        }
        while(ptr1 <= firstHalf.end){
            ptr1++;
            mergedData.add(givenList.get(ptr1-1));
        }
        for(int i=firstHalf.start;i<=secondHalf.end;i++){
            givenList.set(i,mergedData.get(i-firstHalf.start));
        }
        conversions += firstHalf.conversions + secondHalf.conversions;
        return new data(firstHalf.start,secondHalf.end,conversions);
    }

    public static data divide(data givenData,List<Integer> data){
        if(givenData.start >= givenData.end){
            return givenData;
        }
        int mid = givenData.start + (givenData.end-givenData.start)/2;
        data firstHalf = divide(new data(givenData.start,mid,0),data);
        data secondHalf = divide(new data(mid+1,givenData.end,0),data);
        return combine(data,firstHalf,secondHalf);
    }

    public static void main(String[] args){
        List<Integer> data = readData();
        System.out.println(divide(new data(0,data.size()-1,0),data).conversions);
    }
}


