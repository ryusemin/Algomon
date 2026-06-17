import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static final int[] dRow = {0, 1, 0, -1};
    static final int[] dCol = {1, 0, -1, 0};

    static final int MAX_JUMP_POWER = 5;

    static int gridSize, queryCount;

    static char[][] lakeGrid;

    static ArrayList<Edge>[] stateGraph;

    static class Edge {
        int nextState;
        int timeCost;

        Edge(int nextState, int timeCost) {
            this.nextState = nextState;
            this.timeCost = timeCost;
        }
    }

    static int getStateId(int row, int col, int jumpPower) {
        return MAX_JUMP_POWER * ((row - 1) * gridSize + (col - 1)) + (jumpPower - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gridSize = Integer.parseInt(br.readLine());
        lakeGrid = new char[gridSize + 1][gridSize + 1];

        for (int row = 1; row <= gridSize; row++) {
            String rowString = br.readLine();

            for (int col = 1; col <= gridSize; col++) {
                lakeGrid[row][col] = rowString.charAt(col - 1);
            }
        }

        int totalStates = gridSize * gridSize * MAX_JUMP_POWER;

        stateGraph = new ArrayList[totalStates];

        for (int i = 0; i < totalStates; i++) {
            stateGraph[i] = new ArrayList<>();
        }

        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                if (lakeGrid[row][col] != '.') continue;

                for (int jumpPower = 1; jumpPower <= MAX_JUMP_POWER; jumpPower++) {
                    int currentState = getStateId(row, col, jumpPower);

                    if (jumpPower < MAX_JUMP_POWER) {
                        int increasedJumpPower = jumpPower + 1;
                        int nextState = getStateId(row, col, increasedJumpPower);
                        int timeCost = increasedJumpPower * increasedJumpPower;

                        stateGraph[currentState].add( new Edge(nextState, timeCost));
                    }

                    for (int newJumpPower = 1; newJumpPower < jumpPower; newJumpPower++) {
                        int nextState = getStateId(row, col, newJumpPower);

                        stateGraph[currentState].add(new Edge(nextState, 1));
                    }

                    for (int direction = 0; direction < 4; direction++) {
                        int landingRow = row;
                        int landingCol = col;

                        boolean validJump = true;

                        for (int step = 0; step < jumpPower; step++) {
                            landingRow += dRow[direction];
                            landingCol += dCol[direction];

                            if (landingRow < 1 || landingRow > gridSize
                                    || landingCol < 1 || landingCol > gridSize
                                    || lakeGrid[landingRow][landingCol] == '#') {
                                validJump = false;
                                break;
                            }
                        }

                        validJump = validJump && (lakeGrid[landingRow][landingCol] == '.');
                        
                        if (validJump) {
                            int nextState = getStateId(
                                    landingRow,
                                    landingCol,
                                    jumpPower
                            );

                            stateGraph[currentState].add(
                                    new Edge(nextState, 1)
                            );
                        }
                    }
                }
            }
        }

        queryCount = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int query = 0; query < queryCount; query++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            int totalStatesCount = gridSize * gridSize * MAX_JUMP_POWER;

            int[] distance = new int[totalStatesCount];
            Arrays.fill(distance, INF);

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            int startState = getStateId(startRow, startCol, 1);

            distance[startState] = 0;
            pq.offer(new int[]{0, startState});

            int answerTime = INF;

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();

                int currentTime = cur[0];
                int currentState = cur[1];

                if (distance[currentState] < currentTime) {
                    continue;
                }

                int tempState = currentState;

                int currentJumpPower =
                        (tempState % MAX_JUMP_POWER) + 1;

                tempState /= MAX_JUMP_POWER;

                int currentCol =
                        (tempState % gridSize) + 1;

                tempState /= gridSize;

                int currentRow =
                        (tempState % gridSize) + 1;

                if (currentRow == endRow && currentCol == endCol) {
                    answerTime = currentTime;
                    break;
                }

                for (Edge edge : stateGraph[currentState]) {
                    int nextState = edge.nextState;
                    int transitionCost = edge.timeCost;

                    if (currentTime + transitionCost < distance[nextState]) {
                        distance[nextState] =
                                currentTime + transitionCost;

                        pq.offer(
                                new int[]{
                                        distance[nextState],
                                        nextState
                                }
                        );
                    }
                }
            }

            sb.append(answerTime < INF ? answerTime : -1)
              .append('\n');
        }

        System.out.print(sb);
    }
}