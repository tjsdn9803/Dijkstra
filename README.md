## Dijsktra /-> (해당 부분 readme.md파일&코드 작성자 : 연수)
: Dijkstra algorithm을 구현하라. 

Dijkstra algorithm이란??

하나의 정점으로부터 다른 모든 노드에 접근할 때의 최소 경로를 구하는 알고리즘이라 할 수 있다. 


**문제해결방법**

1. priorityQueue를 사용하여 시작노드, 도착노드, 가중치값을 배열로 받고 그 중 가장 작은 값을 찾아내는 방법
2. floydWarshall algorithm을 이용하여 문제 해결
3. 네트워크의 인접행렬을 만들어두고 for문과 if문을 통해서 최소 경로 구하기. 



**<지금은 3번 방법을 다뤄볼 것>**

구현할 함수 : main(), get_nearestIndex(), dijkstra()

**get_nearestIndex()** : 가장 최소 거리를 가지는 정점을 반환하는 함수

**dijkstra()** : dijkstra를 실행하여 지정한 start노드에서 다른 모든 노드에 접근할 때의 최솟값을 찾는 함수.

------------------------

전체적인 코드는 다음과 같다. 

```
public class Dijkstra {
    static int number = 6;
    static int INF = 1000000000;

    static int a[][] = { //각 노드에 대한 가중치값 초기화시킨 배열
            {0,INF,3,5,INF,2},
            {INF,0,1,8,4,INF},
            {3,1,0,INF,INF,INF},
            {5, 8, INF, 0, 6, 1},
            {INF,4,INF,6,0,4},
            {2,INF,INF,1,4,0}
    };

    static int v[] = new int [number]; //노드에 방문한 여부 표시
    static int d[] = new int [6]; //노드 간의 최소값 거리 저장할 배열

    //가장 최소 거리를 가지는 정점을 반환.
    public static int get_nearestIndex() {
        int min = INF;
        int index = 0;
        for(int i = 0; i < number; i++) {
            if(d[i] < min && v[i] != 1) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    public static void dijkstra(int start) {
        for(int i = 0; i<number; i++) {
            d[i] = a[start][i];
        }
        v[start] = 1;
        for(int i = 0; i < number -1; i++) {
            int current = get_nearestIndex();
            v[current] = 1;
            for (int j = 0; j < 6; j++) {
                if (v[j] != 1) {
                    if (d[current] + a[current][j] < d[j])
                        d[j] = d[current] + a[current][j];
                }
            }
        }
    }

    public static void main(String []args) {
        Dijkstra.dijkstra(0);

        for(int i = 0; i<number; i++) {
            System.out.printf("%d ", Dijkstra.d[i]);
        }
    }
}
```

#### 변수와 배열 설명

1. number : 최소 경로를 찾을 네트워크의 노드 수를 나타낸다. 
2. INF : 직접적으로 연결되어 있지 않은 노드 사이의 가중치를 인피니트라고 했을 때, 이를 나타내기 위한 매우 큰 수를 저장하는 변수. 
3. 배열 d[] : dijkstra()에서 매개변수로 시작할 노드를 받으면 그 노드로부터 다른 노드들에 접근할 때의 최소경로를 찾게 된다. 이때 그 **최소 경로 값**들을 저장하는 배열.
4. 배열 v[] : 각 노드에 접근을 했었는 지에 대한 여부를 나타내기 위한 배열 ( 따라서 이 배열의 크기도 number를 따라가게 됨 ) 
5. 배열 a[][] [][][] [] : 각 노드 사이에 **가중치 값**을 인접행렬로 나타낸 배열. 



----------------------------------------------------

#### 함수설명

1. get_nearestIndex()

```
public static int get_nearestIndex() {
        int min = INF;
        int index = 0;
        for(int i = 0; i < number; i++) {
            if(d[i] < min && v[i] != 1) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }
```

: 우선 min이라는 변수에 INF값을 대입하고, 최종적으로 찾을 노드의 값을 저장할 변수 index도 선언하고 0으로 초기화를 해둡니다. for문을 통해서 모든 노드에 접근하도록 하고 그 안에 if문을 실행시켜줍니다.

여기서 if문의 역할은 start할 노드에 연결된 노드들의 가중치 값을 하나씩 보면서 그 값이 min값보다 작고 아직 그 노드에 접근하지 않았다고 한다면, **min값을 갱신하는 역할**을 합니다. 

따라서 start할 노드에 연결된 노드들 중 **가장 작은 가중치 값을 갖는 노드를 찾아내는 것**이고, 그 노드의 인덱스 번호를 index에 넣어놨다가 최종적으로 모든 과정이 끝나게 되면 그 인덱스값을 반환하게 되는 것입니다. 



2. dijkstra()

```
public static void dijkstra(int start) {
        for(int i = 0; i<number; i++) {
            d[i] = a[start][i];
        }
        v[start] = 1;
        for(int i = 0; i < number -1; i++) {
            int current = get_nearestIndex();
            v[current] = 1;
            for (int j = 0; j < number; j++) {
                if (v[j] != 1) {
                    if (d[current] + a[current][j] < d[j])
                        d[j] = d[current] + a[current][j];
                }
            }
        }
    }
```

: 시작할 노드에 연결된 다른 노드들과의 가중치 값을 for문을 통해서 배열 d[]에 넣어줍니다. 

시작할 노드는 우리가 이미 접근을 한 것이라고 할 수 있기 때문에 v[start]에 값을 1로 넣어줌으로써 접근했다는 표시를 남겨줍니다. 

이제 for문을 통해서 최소 경로들을 찾아가도록 하겠습니다. 

다른 for문과 if문을 감싸는 첫 번째 for문은 다른 노드들에 한 번씩 접근하는 횟수만큼 실행되야하므로 자기자신에 접근하는 경우를 뺀, **number-1보다 i 값이 작은 동안만 실행되도록** 조건식을 작성했습니다. 

current라는 변수에는 위에서 구현한 함수를 통해 찾은 **가장 가까운 노드의 인덱스 번호**를 넣어주게 됩니다.

current에 해당하는 노드에도 접근하기 전에 미리 1로 표시를 해주고, 두 번째 for문에 들어가게 됩니다.

앞에서 진행한 코드는 start노드로부터 가장 가까운 노드(current) 하나를 구한 것이고 두 번째 for문에서는 노드 **current에 연결된 노드들 중 가장 가까운 노드**를 구하는 과정입니다. 

if문을 통해 아직 접근하지 않은 노드(j)에 접근합니다. 

**start노드에서 current노드를 거쳐 그 노드(j)에 접근하는 것과 start노드에서 바로 노드 (j)에 접근하는 것** 중 더 작은 값을 찾아서 최종적으로 더 작은 값을 배열 d[] 에 넣어주게 되는 겁니다. 

-----------------------------

dijkstra()를 보면 for문 안에 for문이 사용된 것을 알 수 있습니다. for문을 한 번 실행할 때 시간복잡도가 n인데 for문이 이중으로 사용되었기 때문에 n*n으로 최종적인 시간복잡도는 **O(n^2)**가 됩니다. 





**<이번에는 2번 방법으로 다뤄볼 것>**
## FloydWarshall 알고리즘 /-> (해당 부분 readme.md파일&코드 작성자 : 선우 )

----------------------

:FloydAlgorithm이란??    

플로이드 와샬 알고리즘은 다익스트라 알고리즘이 하나의 출발점으로부터 다른 정점까지의 최단경로를 구하는것과 달리 모든정점에서 다른 모든정점으로의 최단경로를 구하는 알고리즘이다.    

---------

전체적인 코드는 다음과 같다.   

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

**변수와 배열 설명**     

1.  maps\[]\[] : 각노드 사이에 **가중치 값**을 인접행렬로 나타낸 배열.     

2. i : 거쳐가는 정점의 인덱스     
3. j : 출발하는 정점의 인덱스     
4. k : 도착하는 정점의 인덱스       

----------

플로이드 와샬 알고리즘은 출발정점, 거쳐가는 정점, 도착 정점을 이용합니다.        

현재 maps배열에서의 출발정점에서부터 도착정점까지의 가중치 maps\[j]\[k]가       

출발정점에서 거쳐가는 정점을 거쳐 도착정점까지의 가중치 maps\[j]\[i] + maps\[i]\[k] 보다 크다면    

maps\[j]\[k] 를 maps\[j]\[i] + maps\[i]\[k]로 갱신해줍니다.    

이렇게 3중 for문을 통해 모든 정점을 탐색하면 모든 정점에서부터 다른 모든 정점까지의 최단거리가 갱신됩니다.        

플로이드 와샬 알고리즘은 구현하기 쉽고 점정간의 가중치가 음수여도 계산이 가능하다는 장점이 있지만,     

3중for문으로 이루어져 정점의 갯수가 늘어나면 늘어날수록 계산이 급격히 늘어난다는 단점이 있습니다.     

-----------

**시간복잡도**     

이 알고리즘은 3중 for문 의 형태이고 각for문 마다 모든 정점을 방문 하므로 시간복잡도는 O(n^3)입니다.     



## Dijsktra와 FloydWarshall알고리즘의 차이 /-> (readme.md 내용 작성자 : 나현 )

##### 다익스트라 알고리즘

: '하나의 정점'에서 '모든 정점'에 갈 수 있는 최단 경로를 구하는 알고리즘

##### 플로이드 와샬 알고리즘

: '모든 정점'에서 '모든 정점'으로 가는 최단 경로 쌍을 구하는 알고리즘

- 두 알고리즘은 비슷하지만 목적에 따라 사용하는 경우가 다르다. 예를 들어, 인천에서 다른 한 지역까지의 최단거리를 구하고자 한다면 다익스트라 알고리즘을 사용하지만 인천에서 서울, 서울에서 전주, 전주에서 인천 등 모든 조합의 최단 경로를 알고 싶다면 프림 알고리즘을 사용한다.

- 다익스트라 알고리즘은 **음수인 간선이 있을 경우 사용하지 못한다**. 하지만 플로이드 와샬 알고리즘은 간선의 가중치가 음수여도 사용이 가능하다. 다익스트라 알고리즘의 경우,  많은 경로가 음수인 간선을 거치려고 하기 때문에 최단 경로 값이 작아진다. 경로가 길수록 계산 횟수가 많아지기 때문에 많은 경로에서 원하는 답을 얻을 수 없게 된다.

- 다익스트라 알고리즘의 시간복잡도는 **O(n^2)**이고 플로이드 와샬 알고리즈믜 시간복잡도는 **O(n^3)**이다.

  

#### 시간복잡도

![다익스트라 알고리즘 시간복잡도](https://user-images.githubusercontent.com/80876327/114013004-0753fa00-98a2-11eb-8a8d-1479b3503ed9.png)

다익스트라 알고리즘의 시간복잡도는 **O(n^2)** 이므로 그래프로 나타내면 이와 같다.

![플로이드 와샬 알고리즘 시간복잡도](https://user-images.githubusercontent.com/80876327/114013266-4f731c80-98a2-11eb-9380-5233de8a9534.png)

플로이드 와샬 알고리즘의 시간복잡도는 **O(n^3)**이므로 그래프로 나타내면 이와 같다.

![다익스트라 알고리즘과 플로이드 와샬 알고리즘의 시간복잡도 비교](https://user-images.githubusercontent.com/80876327/114013456-81847e80-98a2-11eb-9579-5da2238e432c.png)

각각 **적색그래프**는 다익스트라 알고리즘의 시간복잡도 이고 **녹색그래프**는 플로이드 와샬 알고리즘의 시간복잡도를 나타낸다. 이를 통해 다익스트라 알고리즘이 플로이드 와샬 알고리즘에 비해 시간복잡도가 더욱 좋다고 할 수 있다. 하지만 __경우에 따라 플로이드 와샬 알고리즘이 좋을 수 있다는 것을 알아두어야한다.__
