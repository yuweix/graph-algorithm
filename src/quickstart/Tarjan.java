package quickstart;
import java.util.*;

//https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
//followed by the pseudocode in wiki
public class Tarjan {
    //check if is the strongly connected components
    // Complexity:  O(|V|+|E|)
    /** v: number of vertices
     * preCount: preorder number counter
     *  low: low number of v
     *  visited: to check if v is visited
     *  graph : the input graph for Tarjan algorithm
     *  result: store all scc
    **/
    private int V;
    private int preCount;
    private int[] low;
    private boolean[] visited;
    private List<Integer>[] graph;
    private List<List<Integer>> result;
    private Stack<Integer> stack;

    //get all strongly connected components
    public List<List<Integer>> getAllConnected(List<Integer>[] graph)
    {
        V = graph.length;
        this.graph = graph;
        low = new int[V];
        visited = new boolean[V];
        stack = new Stack<Integer>();
        result = new ArrayList<>();

        for (int v = 0; v < V; v++)
            if (!visited[v])
                dfs(v);

        return result;
    }


    public void dfs(int v)
    {
        low[v] = preCount++;
        visited[v] = true;
        stack.push(v);
        int min = low[v];

        //for each (v,w) edge in thr graph, then
        for (int w : graph[v])
        {
            if (!visited[w]){
                dfs(w);
            }
            if (low[w] < min)
                min = low[w];
        }
        if (min < low[v])
        {
            low[v] = min;
            return;
        }


        //  If v is a root node, pop the stack and generate the strongest connected component
        List<Integer> component = new ArrayList<Integer>();
        int w=stack.pop();
        component.add(w);
        low[w] = V;


        while (w != v)
        {
            w = stack.pop();
            component.add(w);
            low[w] = V;
        }
        result.add(component);
    }

    //main funnction to check
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of Vertices\n");
        int V = scan.nextInt();


        List<Integer>[] Graph = new List[V];
        for (int i = 0; i < V; i++)
            Graph[i] = new ArrayList<Integer>();
        System.out.println("Enter the number of edges\n");
        int edge = scan.nextInt();


        System.out.println("Enter "+ edge +" x  y\n");
        for (int i = 0; i < edge; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();
            Graph[x].add(y);
        }

        Tarjan t = new Tarjan();
        System.out.println("result: \n");
        List<List<Integer>> answer = t.getAllConnected(Graph);
        System.out.println(answer);
    }
}

