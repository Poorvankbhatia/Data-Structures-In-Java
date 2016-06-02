package utility.graphClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 02/06/16.
 */
public class SymbolGraph {

    private TreeMap<String,Integer> map; // String -> Index
    private String[] keys;               // Index -> String
    private Graph G;

    public SymbolGraph(File file, String delimiter) {

        map = new TreeMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line=null;
            while ((line=br.readLine())!=null) {
                String[] input = line.split(delimiter);
                for (int i=0;i<input.length;i++) {
                    if(!map.containsKey(input[i])) {
                        map.put(input[i],map.size());
                    }
                }
            }

            keys = new String[map.size()];
            for (String name : map.keySet()) {
                keys[map.get(name)] = name;
            }
            G = new Graph(map.size());

            br = new BufferedReader(new FileReader(file));
            while ((line=br.readLine())!=null) {
                String[] input = line.split(delimiter);
                int v = map.get(input[0]);
                for (int i=1;i<input.length;i++) {
                    int w = map.get(input[i]);
                    G.addEdge(v,w);
                }
            }

            System.out.println("File read successfully");
            br.close();

        } catch (IOException e) {
            System.err.println("Error whie reading file - " + file.getName());
        }
    }

    public boolean contains(String s) {
        return map.containsKey(s);
    }

    public int index(String s) {
        return map.get(s);
    }

    public String name(int index) {
        return keys[index];
    }

    public Graph getAssociatedGraph() {
        return G;
    }


    public static void main(String[] args) {

        String path = new File("").getAbsolutePath();
        File file = new File(path + "/src/inputFiles/movies.txt");
        SymbolGraph sg = new SymbolGraph(file,"/");
        Graph G = sg.getAssociatedGraph();

        for (int v=0;v<G.getVertexCount();v++) {
            System.out.print(sg.name(v) + " : ");
            for (Integer neighbour : G.getAdj(v)) {
                System.out.print(sg.name(neighbour) + " ");
            }
            System.out.println();
        }

    }


}


/*

 A symbol table st with String keys (vertex names) and int values (indices)
 An array keys[] that serves as an inverted index, giving the vertex name associated
with each integer index
 A Graph G built using the indices to refer to vertices

 */