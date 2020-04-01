package Course_3.Huffman_Compression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public static class Node implements Comparable<Node>{
        Node left;
        Node right;
        long weight;
        public Node(Node n1, Node n2){
            left = n1;
            right = n2;
            weight = n1.weight + n2.weight;
        }
        public Node(int weight){
            left = null;
            right = null;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node n) {
            return Long.compare(this.weight, n.weight);
        }

    }



    public static int[] readData(){
        int[] data = new int[1000];
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/Huffman_Compression/data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null){
                data[i] = Integer.parseInt(st);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static Node getRoot(PriorityQueue<Node> pq){
        while (pq.size() > 1){
            Node n1 = pq.poll();
            Node n2 = pq.poll();
            Node n3 = new Node(n1,n2);
            pq.add(n3);
        }
        return pq.peek();
    }

    public static void main(String[] args){
        int[] data = readData();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i=0;i<data.length;i++){
            pq.add(new Node(data[i]));
        }
        Node root = getRoot(pq);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int height = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                Node x = q.poll();
                if(x.left == null && x.right == null){
                    min = Math.min(min, height);
                    max = Math.max(max, height);
                }
                if(x.left != null){
                    q.add(x.left);
                }
                if(x.right != null){
                    q.add(x.right);
                }
            }
            height++;
        }
        System.out.println(min);
        System.out.println(max);
    }
}
