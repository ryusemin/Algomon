class Solution {
    public int solution(int dist_limit, int split_limit) {
        long answer = 1;
        long pow2 = 1;

        for (int a = 0; pow2 <= split_limit; a++) {
            long cost2 = pow2 - 1;
            long pow3 = 1;

            for (int b = 0; pow2 * pow3 <= split_limit; b++) {
                
                long leaf = pow2 * pow3;
                long cost3 = pow2 * (pow3 - 1) / 2;
                long used = cost2 + cost3;

                if (used > dist_limit) break;

                long remain = dist_limit - used;
                answer = Math.max(answer, leaf);


                long partial = Math.min(remain, leaf);

                if (leaf * 3 <= split_limit) answer = Math.max(answer, leaf + partial * 2); 
                else if (leaf * 2 <= split_limit) answer = Math.max(answer, leaf + partial);

                if (pow3 > split_limit / 3) break;
                pow3 *= 3;
            }

            if (pow2 > split_limit / 2) break;
            pow2 *= 2;
        }

        return (int) answer;
    }
}