package Course_3.Clustering_Bits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public static Node getParent(Node n){
        while(n.parent != n){
            n = n.parent;
        }
        return n;
    }

    public static int union(Node n1, Node n2){
        n1 = getParent(n1);
        n2 = getParent(n2);
        if(n1.size < n2.size){
            n1.parent = n2;
            n2.size += n1.size;
            return n2.val;
        }else{
            n2.parent = n1;
            n1.size += n2.size;
            return n1.val;
        }
    }

    public static boolean find(Node n1, Node n2){
        return getParent(n1) == getParent(n2);
    }
    public static class Node{
        int val;
        Node parent;
        int size;
        public Node(int num){
            val = num;
            size = 1;
            parent = this;
        }
    }

    public static int changeBit(int num, int idx){
        int mask = 1;
        mask = mask << idx-1;
        return num ^ mask;
    }


    public static int getNum(String[] bits){
        int n = 0;
        for(int i=bits.length-1;i>=0;i--){
            n = n << 1;
            if(bits[i].equals("1")){
                n = n | 1;
            }
        }
        return n;
    }

    public static int[] readData(){
        int[] data = new int[200000];
        //106
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/Clustering_Bits/data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                data[i] = getNum(splitted);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static void main(String[] args){
        int[] nums = readData();
        Arrays.sort(nums);
        HashMap<Integer, Node> mp = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!mp.containsKey(nums[i])){
                mp.put(nums[i], new Node(nums[i]));
            }
        }
        for(int i=0;i<nums.length;i++){
            for (int j=1;j<=24;j++){
                if(mp.containsKey(changeBit(nums[i], j))){
                    Node n1 = mp.get(nums[i]);
                    Node n2 = mp.get(changeBit(nums[i],j));
                    if (find(n1, n2)){
                        continue;
                    }
                    union(n1,n2);
                }
            }
        }
        for(int i=0;i<nums.length;i++){
            for (int j=1;j<=24;j++){
                for(int k=1;k<=24 && k!=j;k++){
                    int n_1 = changeBit(nums[i],j);
                    int n_2 = changeBit(n_1,k);
                    if(mp.containsKey(n_2)){
                        Node n1 = mp.get(nums[i]);
                        Node n2 = mp.get(n_2);
                        if (find(n1, n2)){
                            continue;
                        }
                        union(n1,n2);
                    }
                }
            }
        }

        HashMap<Integer, Integer> saved = new HashMap<>();
        int ans = 0;
        for(int i=0;i<nums.length;i++){
            Node n = mp.get(nums[i]);
            if(!saved.containsKey(getParent(n).val)){
                saved.put(getParent(n).val,1);
                ans++;
            }
        }
        System.out.println(ans);
    }
}
