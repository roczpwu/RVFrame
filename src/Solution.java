import java.util.*;

class Node implements Comparable {
    public int val;

    public Node (int val) {
        this.val = val;
    }

    @Override
    public int compareTo(Object o) {
        int tmp1 = this.val;
        int tmp2 = ((Node)o).val;
        int length1 = this.getBitCount();
        int length2 = ((Node)o).getBitCount();
        long c1 = (long) (tmp1 * Math.pow(10,length2)) + tmp2;
        long c2 = (long) (tmp2 * Math.pow(10,length1)) + tmp1;
        if (c1 == c2) return 0;
        else if (c1 > c2) return 1;
        else return -1;
    }

    public int getBitCount() {
        int cnt = 0;
        int tmp = val;
        while (tmp > 0) {
            tmp /= 10;
            cnt++;
        }
        return cnt;
    }
}

public class Solution {

    public int getLargestInteger(int []arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        Node []nodeArr = new Node[n];
        for (int i = 0; i < n; i++) {
            nodeArr[i] = new Node(arr[i]);
        }
        Arrays.sort(nodeArr);
        int tmp = 0;
        for (int i = n-1; i >= 0; i--) {
            tmp *= Math.pow(10, nodeArr[i].getBitCount());
            tmp += nodeArr[i].val;
        }
        return tmp;
    }

    public static void main(String[] args) {
//        int []arr = {3,30,34,5,9};
        int []arr = {121,12};
        Solution solution = new Solution();
        int res = solution.getLargestInteger(arr);
        System.out.println(res);
    }

}