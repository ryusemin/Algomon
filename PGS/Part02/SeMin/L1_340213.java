class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
       int start = Integer.parseInt(op_start.substring(0,2)) * 60 + Integer.parseInt(op_start.substring(3, 5));
        
       int end = Integer.parseInt(op_end.substring(0,2)) * 60 + Integer.parseInt(op_end.substring(3, 5));
        
        int p = Integer.parseInt(pos.substring(0,2)) * 60 + Integer.parseInt(pos.substring(3, 5));
        
        int len = Integer.parseInt(video_len.substring(0,2)) * 60 + Integer.parseInt(video_len.substring(3, 5));
        
        if(start <= p &&  p <= end ) p = end;
        
        for(String s : commands){
            switch(s){
                case "prev":
                    p -= 10;
                    if(p < 0) p = 0;
                    if(start <= p &&  p <= end ) p = end;
                    break;
                case "next":
                    p += 10;
                    if(p > len - 10 ) p = len;
                    if(start <= p &&  p <= end ) p = end;
                    break;
            }   
        }
        String minute = Integer.toString(p / 60);
        if(minute.length() == 1) minute = "0" + minute;
        String second = Integer.toString(p % 60);
        if(second.length() == 1) second = "0" + second;
            
        answer =  minute + ":" + second;
        
        return answer;
    }
}