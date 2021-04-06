import java.util.Scanner;

class Graph{
    private int n;           //노드들의 수
    private int maps[][];    //노드들간의 가중치 저장할 변수

    public Graph(int n){
        this.n = n;
        maps = new int[n+1][n+1];

    }
    public void input(int i,int j,int w){
        maps[i][j] = w;
        maps[j][i] = w;
    }

    public void dijkstra(int v) {

    }
}
public class ShortestPath {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("노드 갯수 입력");
        int n = 8;//노드의 갯수

        System.out.println("선분 갯수 입력");
        int m = 10;//선분의 갯수
        Graph g = new Graph(n);//노드의 갯수

        int s;
        System.out.println("출발지 입력");
        s = 1; //출발지 입력
        g.dijkstra(s);
        //g.normalcase();
    }
}
