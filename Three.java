/**
 * Created by jzhen on 6/16/2018.
 */
public class Three {
    int changePossibilities(int amount, int denomination[]) {
        if(amount < 0 || denomination == null || denomination.length == 0) return 0;

        int runningCount[] = new int[amount + 1];
        runningCount[0] = 1;

        for(int i = 0; i < denomination.length; i++){
            for (int j = denomination[i]; j <= amount; j++){
                runningCount[j] += runningCount[j - denomination[i]];
            }
        }

        return runningCount[amount];
    }
}
