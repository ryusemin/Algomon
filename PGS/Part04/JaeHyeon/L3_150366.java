import java.util.*;

class Solution {
    public String[] solution(String[] commands) {
        String[] answer = {};
        List<String> result = new ArrayList<>();
        
        String[][] map = new String[51][51];
        String[][] root = new String[51][51];
        
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                root[i][j] = i + " " + j;
            }
        }
        
        StringTokenizer st;
        for (String cmd : commands) {
            String[] split = cmd.split(" ");
            
            switch (split[0]) {
                case "UPDATE":
                    if (split.length == 4) {
                        int r = Integer.parseInt(split[1]);
                        int c = Integer.parseInt(split[2]);
                        String value = split[3];
                        
                        st = new StringTokenizer(root[r][c]);
                        int rootR = Integer.parseInt(st.nextToken());
                        int rootC = Integer.parseInt(st.nextToken());
                        map[rootR][rootC] = value;
                    } else {
                        String value1 = split[1];
                        String value2 = split[2];
                        
                        for (int i = 1; i < 51; i++) {
                            for (int j = 1; j < 51; j++) {
                                if (map[i][j] != null && map[i][j].equals(value1)) {
                                    map[i][j] = value2;
                                }
                            }
                        }
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(split[1]);
                    int c1 = Integer.parseInt(split[2]);
                    int r2 = Integer.parseInt(split[3]);
                    int c2 = Integer.parseInt(split[4]);
                    
                    String root2 = root[r2][c2];
                    String root1 = root[r1][c1];
                    
                    st = new StringTokenizer(root1);
                    int rootR1 = Integer.parseInt(st.nextToken());
                    int rootC1 = Integer.parseInt(st.nextToken());
                    
                    st = new StringTokenizer(root2);
                    int rootR2 = Integer.parseInt(st.nextToken());
                    int rootC2 = Integer.parseInt(st.nextToken());
                    
                    String rvalue = map[rootR1][rootC1] == null 
                                    ? map[rootR2][rootC2]
                                    : map[rootR1][rootC1];
                    
                    for (int i = 1; i < 51; i++) {
                        for (int j = 1; j < 51; j++) {
                            if (root[i][j].equals(root2)) {
                                root[i][j] = root1;
                            }
                        }
                    }
                    
                    map[rootR1][rootC1] = rvalue;
                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    
                    String rt = root[r][c];
                    st = new StringTokenizer(rt);
                    int rootR = Integer.parseInt(st.nextToken());
                    int rootC = Integer.parseInt(st.nextToken());
                    String real = map[rootR][rootC];
                    
                    for (int i = 1; i < 51; i++) {
                        for (int j = 1; j < 51; j++) {
                            if (rt.equals(root[i][j])) {
                                root[i][j] = i + " " + j;
                                map[i][j] = null;
                            }
                        }
                    }
                    
                    map[r][c] = real;
                    break;
                case "PRINT":
                    int rr = Integer.parseInt(split[1]);
                    int cc = Integer.parseInt(split[2]);
                    
                    st = new StringTokenizer(root[rr][cc]);
                    int rootRr = Integer.parseInt(st.nextToken());
                    int rootCc = Integer.parseInt(st.nextToken());
                    String value = map[rootRr][rootCc];
                    
                    result.add(value == null ? "EMPTY" : value);
                    break;
                default:
                    break;
            }
        }
        
        return result.toArray(String[]::new);
    }
}