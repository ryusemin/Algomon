class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int hp = health;
        int cnt = 0;
        int attackIdx = 0;

        for (int i = 1; i <= attacks[attacks.length-1][0]; i++) {
            if (i != attacks[attackIdx][0]) {
                hp += bandage[1];
                cnt++;
                if (cnt == bandage[0]) {
                    hp += bandage[2];
                    cnt = 0;
                }
                if (hp > health)
                    hp = health;
            } else {
                hp -= attacks[attackIdx][1];
                cnt = 0;
                attackIdx++;
                if (hp <= 0)
                    return -1;
            }
        }

        return hp;
    }
}