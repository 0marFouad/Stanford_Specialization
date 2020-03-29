package Course_3.Clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
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
    public static int[][] readData(){
        int[][] data = new int[124750][3];
        //106
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/Clustering/data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                data[i][0] = Integer.parseInt(splitted[0]);
                data[i][1] = Integer.parseInt(splitted[1]);
                data[i][2] = Integer.parseInt(splitted[2]);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }



    public static void main(String[] args){
        int[][] data = readData();
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        int clusers = 500;
        HashMap<Integer, Node> mp = new HashMap<>();
        for (int i=0;i<data.length && clusers > 4;i++){
            int node1 = data[i][0];
            int node2 = data[i][1];
            int cost = data[i][2];
            if (!mp.containsKey(node1)){
                Node new1 = new Node(node1);
                mp.put(node1, new1);
            }
            if (!mp.containsKey(node2)){
                Node new2 = new Node(node2);
                mp.put(node2, new2);
            }
            Node n1 = mp.get(node1);
            Node n2 = mp.get(node2);
            if(find(n1,n2)){
                continue;
            }
            clusers--;
            union(n1,n2);
            if(clusers == 4){
                for (int j=i+1;j<data.length;j++){
                    int no1 = data[j][0];
                    int no2 = data[j][1];
                    if(!mp.containsKey(no1) || !mp.containsKey(no2)){
                        System.out.println(data[j][2]);
                        return;
                    }else{
                        if(!find(mp.get(no1),mp.get(no2))){
                            System.out.println(data[j][2]);
                            return;
                        }
                    }
                }
            }
        }
    }
}
