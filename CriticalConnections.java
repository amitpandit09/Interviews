// "static void main" must be defined in a public class.

class PairInt{
    Integer first;
    Integer second;
    PairInt(Integer first,Integer second){
        this.first = first;
        this.second=second;
    }
}

public class Main {
    
    static int time = 0;
    static final int NIL = -1;
    static List<List<PairInt>> ans1 = new LinkedList<>();
    
    static class Graph{
        private int v;
        private LinkedList<Integer> adj[]; 
        Graph(int V) 
        { 
            v = V; 
            adj = new LinkedList[V+1]; 
            for (int i=0; i<V+1; ++i) 
                adj[i] = new LinkedList(); 
        }
        
        void addEdge(int V, int w) 
        { 
            this.adj[V].add(w);
            this.adj[w].add(V);
        }
    }
    
     static void bridgeDfs(Graph g,int u,boolean visited[], int disc[], int low[], int parent[] )
    {
        visited[u] = true;
        disc[u]=low[u]=++time;
        
        Iterator<Integer> i = (g.adj[u]).iterator();
        while(i.hasNext())
        {
            int v = i.next();
            if(!visited[v])
            {
                parent[v]=u;
                bridgeDfs(g,v,visited,disc,low,parent);
                low[u] = Math.min(low[u],low[v]);
                if(low[v]>disc[u]){
                    //ans1.add(new Integer[]{u,v});
                    ans1.add(Arrays.asList(new PairInt(u,v)));
                }
            }
            else if(v!=parent[u])
            {
                low[u] = Math.min(low[u],disc[v]);
            }
        }
    }
    
    static int[][] bridges(List<PairInt> edges,int v)
    {
        Graph g = new Graph(v);
        for(int i=0;i<edges.size();i++)
        {
            g.addEdge(edges.get(i).first,edges.get(i).second);
           // g.addEdge(edges[i][0],edges[i][1]);
        }
        boolean visited[] = new boolean[v+1];
        int disc[] = new int[v+1];
        int low[] = new int[v+1];
        int parent[] = new int[v+1];
        
        for(int i=0;i<v;i++)
        {
            parent[i]=NIL;
            visited[i]=false;
        }
        

        for(int i=0;i<v;i++)
        {
            if(!visited[i])
            {
                bridgeDfs(g,i, visited, disc, low, parent);
            }
        }
        
        int[][] res = new int[ans1.size()][2];
        int index=0;
        for(List<PairInt> temp: ans1)
        {
            res[index][0]=temp.get(0).first;
            res[index][1]=temp.get(0).second;
            index++;
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        List<PairInt> connections=new ArrayList<>();
        int numOfServers = 13;
        int numOfConnections = 13;
    
        connections.add(new PairInt(1,2));
        connections.add(new PairInt(1,3));
        connections.add(new PairInt(2,3));
        connections.add(new PairInt(3,4));
        connections.add(new PairInt(4,5));
        connections.add(new PairInt(4,6));
        connections.add(new PairInt(5,6));
        connections.add(new PairInt(5,7));
        connections.add(new PairInt(6,7));
        connections.add(new PairInt(7,8));
        connections.add(new PairInt(8,9));
        connections.add(new PairInt(8,10));
        connections.add(new PairInt(9,10));
    
        int[][] ans = bridges(connections,numOfServers);
        if(ans.length==0)
            System.out.print("[]");
        for(int[] arr : ans)
            System.out.print(Arrays.toString(arr)+",");
        
    }
}
