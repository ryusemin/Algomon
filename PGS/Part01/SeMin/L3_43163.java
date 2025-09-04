import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Word> q = new LinkedList<>();
        q.offer(new Word(begin, 0));
        
        while(!q.isEmpty()){
            Word now = q.poll();
            String word = now.word;
            int count = now.count;
            
            if(word.equals(target)){
                return count;
            }
            
            for(int i = 0; i< words.length; i++){
                if(visited[i]) continue;
                int c = 0;
                
                String w = words[i];
                
                for(int j = 0; j < target.length(); j++){
                    if(word.charAt(j) != w.charAt(j)){
                        c++;
                    }
                }
                if(c == 1) {
                    q.offer(new Word(w, count + 1));
                    visited[i] = true;
                }
            }
        }
        
        return 0;
    }
}


class Word{
    String word;
    int count;
    
    public Word(String word, int count){
        this.word = word;
        this.count = count;
    }
}