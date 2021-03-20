package com.company.graph2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class GraphTasks2Solution implements GraphTasks2 {
    private ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

    @Override
    public HashMap<Integer, Integer> dijkstraSearch(int[][] adjacencyMatrix, int startIndex) {

        return djikstraAlgoritm(adjacencyMatrix,startIndex);
    }

    private HashMap<Integer, Integer> djikstraAlgoritm(int[][] adjacencyMatrix, int startIndex) {
        HashMap<Integer,Integer> result = new HashMap<>();
        result.put(startIndex,0);
        arrayDeque.offerFirst(startIndex);
        while(!arrayDeque.isEmpty()){
            for (int i =0;i<adjacencyMatrix.length;i++){
                if(adjacencyMatrix[startIndex][i]>0){
                    if (result.containsKey(i)) {
                        if (result.get(i) > result.get(startIndex) + adjacencyMatrix[startIndex][i]) {
                            result.replace(i,result.get(startIndex) +adjacencyMatrix[startIndex][i]);
                            if(!arrayDeque.contains(i)){
                                arrayDeque.offerLast(i);
                            }
                        }
                    } else {
                        result.put(i,result.get(startIndex)+adjacencyMatrix[startIndex][i]);
                        if(!arrayDeque.contains(i)){
                            arrayDeque.offerFirst(i);
                        }
                    }

                }
            }
            if(!arrayDeque.isEmpty()){
                startIndex = arrayDeque.pollLast();
            }
        }
        return null;
    }

    @Override
    public Integer primaAlgorithm(int[][] adjacencyMatrix) {
        return algorithmPrima(adjacencyMatrix);
    }

    private Integer algorithmPrima(int[][] adjacencyMatrix) {
        int result = 0;
        int startIndex=0;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        arrayDeque.offerFirst(0);
        while(arrayDeque.size() < adjacencyMatrix.length){
            for(int i =0;i<adjacencyMatrix.length;i++){
                if(adjacencyMatrix[startIndex][i]>0){
                    if(!arrayDeque.contains(i)){
                        if(map.containsKey(adjacencyMatrix[startIndex][i])){
                            ArrayList<Integer> arrayList = map.get(adjacencyMatrix[startIndex][i]);
                            if(!arrayList.contains(i)){
                                arrayList.add(i);
                            }
                        } else {
                            ArrayList<Integer> arrayList = new ArrayList<>();
                            arrayList.add(i);
                            map.put(adjacencyMatrix[startIndex][i],arrayList);
                        }
                    }
                }
            }

            boolean foundWay = false;
            TreeSet<Integer> set = new TreeSet<>(map.keySet());

             while(!foundWay){
                 Integer indexToDelete = null;
                 for(Integer item : set){
                     if(!foundWay){
                         ArrayList<Integer> array = map.get(item);
                         for(int i =0;i<array.size();i++ ){
                             if(!arrayDeque.contains(array.get(i))){
                                 arrayDeque.offerFirst(array.get(i));
                                 result = result + item;
                                 startIndex = array.get(i);
                                 if(array.size() == 1){
                                     indexToDelete = item;
                                 } else {
                                     array.remove(i);
                                 }
                                 foundWay = true;
                                 break;
                             }
                         }
                     }
                 }
                 if(indexToDelete != null){
                     map.remove(indexToDelete);
                 }
             }
        }
        arrayDeque.clear();
        return result;

    }

    @Override
    public Integer kraskalAlgorithm(int[][] adjacencyMatrix) {
        return null;
    }
}
