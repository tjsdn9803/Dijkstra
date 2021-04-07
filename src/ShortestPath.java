import java.util.Scanner;
class Graph {
    public int n;           //노드들의 수
    public int maps[][];    //노드들간의 가중치 저장할 변수

    public Graph(int n) {
        this.n = n;
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


    public void dijkstra(int s) {
        int distance[] = new int[n+1];          //최단 거리를 저장할 변수
        boolean[] check = new boolean[n+1];     //해당 노드를 방문했는지 체크할 변수

        for(int i=1;i<n+1;i++){
            distance[i] = Integer.MAX_VALUE;
        }

        distance[s] =0;
        check[s] =true;

        for(int i=1;i<n+1;i++){
            if(!check[i] && maps[s][i] !=0){
                distance[i] = maps[s][i];
            }
        }



    }
}


public class ShortestPath {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("노드 갯수 입력");
//        int n = 8;//노드의 갯수
//
//        System.out.println("선분 갯수 입력");
//        int m = 10;//선분의 갯수
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



        int s;
        System.out.println("출발지 입력");
        s = 1; //출발지 입력

        g.FloydWarshall();
        //g.normalcase();
    }
}
