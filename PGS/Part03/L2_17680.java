class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int answer = 0;
        Queue<String> cache = new LinkedList<>(); 

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();


            if (cache.remove(city)) {
                cache.add(city); 
                answer += 1; 
            } else {
    
                if (cache.size() == cacheSize) {
                    cache.poll();
                    cache.add(city); 
                } else {
                    cache.add(city); 
                }
                answer += 5; 
            }
        }

        return answer;
    }
}