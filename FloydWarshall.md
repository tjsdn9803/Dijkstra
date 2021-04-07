## FloydWarshall 알고리즘

----------------------

플로이드 와샬 알고리즘은 다익스트라 알고리즘이 하나의 출발점으로부터 다른 정점까지의 최단경로를 구하는것과 달리 모든정점에서 다른 모든정점으로의 최단경로를 구하는 알고리즘이다.

```java
public void FloydWarshall(){
        for(int i=0; i<maps.length; i++) {//거쳐가는 정점
            for(int j=0; j<maps.length; j++) {//출발하는 정점
                for(int k=0; k<maps.length; k++) {//도착 정점
                    if(maps[j][k] > maps[j][i] + maps[i][k])
                        maps[j][k] = maps[j][i] + maps[i][k];
                }
            }
        }
        for(int i=0; i<maps.length; i++) {//출력부
            for(int j=0; j<maps.length; j++) {
                System.out.print(maps[i][j]+ " ");
            }System.out.println();
        }
    }
```

플로이드 와샬 알고리즘은 출발정점, 거쳐가는 정점, 도착 정점을 이용합니다.        

현재 maps배열에서의 출발정점에서부터 도착정점까지의 가중치 maps\[j]\[k]가       

출발정점에서 거쳐가는 정점을 거쳐 도착정점까지의 가중치 maps\[j]\[i] + maps\[i]\[k] 보다 크다면    

maps\[j]\[k] 를 maps\[j]\[i] + maps\[i]\[k]로 갱신해줍니다.   

이 알고리즘은 3중 for문 의 형태이고 각for문 마다 모든 정점을 방문 하므로 시간복잡도는 O(n^3)입니다.     



