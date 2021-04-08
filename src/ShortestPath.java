import java.util.Scanner;
class Graph {
    static int n;
    static int INF = 1000000000;//노드들의 수
    static int maps[][];    //노드들간의 가중치 저장할 변수

    public Graph(int n) {
        Graph.n = n;
        maps = new int[n][n];

    }

    public void input(int i, int j, int w) {
        maps[i][j] = w;
        maps[j][i] = w;
    }
    public void init(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                maps[i][j] = 9999;
                if(i==j) maps[i][j] = 0;
            }
        }
    }
    static int v[] = new int [10]; //노드에 방문한 여부 표시
    static int d[] = new int [10]; //노드 간의 최소값 거리 저장할 배열

    //가장 최소 거리를 가지는 정점을 반환.
    public static int get_nearestIndex() {
        int min = INF;
        int index = 0;
        for(int i = 0; i < n; i++) {
            if(d[i] < min && v[i] != 1) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }
    public static void dijkstra(int start) {
        for(int i = 0; i<n; i++) {
            d[i] = maps[start][i];
        }
        v[start] = 1;
        for(int i = 0; i < n -1; i++) {
            int current = get_nearestIndex();
            v[current] = 1;
            for (int j = 0; j < n; j++) {
                if (v[j] != 1) {
                    if (d[current] + maps[current][j] < d[j])
                        d[j] = d[current] + maps[current][j];
                }
            }
        }
        for(int i = 0; i<n; i++) {
            System.out.printf("%d ", d[i]);
        }
    }



    public void FloydWarshall(){
        for(int i=0; i<maps.length; i++) {
            for(int j=0; j<maps.length; j++) {
                for(int k=0; k<maps.length; k++) {
                    if(maps[j][k] > maps[j][i] + maps[i][k])
                        maps[j][k] = maps[j][i] + maps[i][k];
                }
            }
        }
        for(int i=0; i<maps.length; i++) {
            for(int j=0; j<maps.length; j++) {
                System.out.print(maps[i][j]+ " ");
            }System.out.println();
        }
    }
}


public class ShortestPath {

    public static void main(String[] args) {
        Graph g = new Graph(10);//노드의 갯수
        g.init();
        g.input(0,1,12);
        g.input(0,2,15);
        g.input(1,4,4);
        g.input(1,5,10);

        g.input(2,3,21);
        g.input(2,6,7);
        g.input(3,7,25);
        g.input(4,5,3);

        g.input(4,8,13);
        g.input(5,6,10);
        g.input(6,7,19);
        g.input(6,9,9);

        g.input(7,9,5);
        g.input(8,9,15);
        System.out.println("플로이드 와셜의 결과");
        g.FloydWarshall();
        System.out.println(" ");
        Graph g2 = new Graph(10);
        g.init();
        g.input(0,1,12);
        g.input(0,2,15);
        g.input(1,4,4);
        g.input(1,5,10);

        g.input(2,3,21);
        g.input(2,6,7);
        g.input(3,7,25);
        g.input(4,5,3);

        g.input(4,8,13);
        g.input(5,6,10);
        g.input(6,7,19);
        g.input(6,9,9);

        g.input(7,9,5);
        g.input(8,9,15);
        System.out.println("다익스트라의 결과");
        g2.dijkstra(0);

    }
}
