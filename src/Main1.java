import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by rocwu on 2016/10/12.
 */
class Point {
    int index;
    int s;
    Point left = null;
    Point right = null;
    Point parent = null;
}

public class Main1 {

    public static void main(String []args) {
        Scanner in  = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        Map<Integer, List<Integer>> neighbourMap = new HashMap<>();
        Map<Integer, Point> nodeMap = new HashMap<>();
        int[] S = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = in.nextInt();
            neighbourMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            int x = in.nextInt()-1; // modify index started by 0
            int y = in.nextInt()-1;
            neighbourMap.get(x).add(y);
            neighbourMap.get(y).add(x);
        }
        // find a node which has less than three neighbours, mark it as root node
        Point root = null;
        for (int i = 0; i < N; i++) {
            if (neighbourMap.get(i).size() < 3) {
                root = new Point();
                root.index = i;
                root.s = S[i];
                break;
            }
        }
        // mark nodes which have been in the tree
        Set<Integer> visitedSet = new HashSet<>();
        visitedSet.add(root.index);
        List<Point> currentList = new ArrayList<>();
        currentList.add(root);
        while (currentList.size() > 0) {
            List<Point> nextList = new ArrayList<>();
            for (Point currentNode : currentList) {
                nodeMap.put(currentNode.index, currentNode);
                for (int neibourIndex : neighbourMap.get(currentNode.index)) {
                    if (visitedSet.contains(neibourIndex)) continue;
                    Point child = new Point();
                    child.parent = currentNode;
                    child.index = neibourIndex;
                    if (currentNode.left == null) currentNode.left = child;
                    else currentNode.right = child;
                    child.s = S[child.index];
                    visitedSet.add(child.index);
                    nextList.add(child);
                }
            }
            currentList = nextList;
        }
        // -- build tree completed -- //

        Map<Integer, List<Point>> levelMap = new HashMap<>();
        List<Point> currentTmpList = new ArrayList<>();
        currentTmpList.add(root);
        int level = 0;
        while (currentTmpList.size()>0) {
            levelMap.put(level, currentTmpList);
            List<Point> nextTmpList = new ArrayList<>();
            for (int i = 0; i < currentTmpList.size(); i++) {
                if (currentTmpList.get(i).left != null)
                    nextTmpList.add(currentTmpList.get(i).left);
                if (currentTmpList.get(i).right != null)
                    nextTmpList.add(currentTmpList.get(i).right);
            }
            level++;
            currentTmpList = nextTmpList;
        }

        // node i and its children has j routers, show how many satisfaction in total
        int[][][] dp = new int[N][M+1][2];
        boolean[][][] flag = new boolean[N][M+1][2];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M+1; j++)
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = 0;
                    flag[i][j][k] = false;
                }
        visitedSet = new HashSet<>();
        while (levelMap.size() > 0) {
            List<Point> currentNodeList = levelMap.get(levelMap.size()-1);
            for (Point node : currentNodeList) {
                // leaf node
                if (node.left == null && node.right == null) {
                    dp[node.index][1][1] = node.s;
                    flag[node.index][1][1] = true;
                }
                // have two children
                else if (node.left != null && node.right != null) {
                    Point left = node.left;
                    Point right = node.right;
                    for (int j = 1; j <= M; j++) {
                        int max = 0;
                        // node has no router
                        for (int k = 0; k <= j; k++) {
                            int x = k, y = j-k;
                            // left and right children have no router
                            int tmp1 = dp[left.index][x][0] + dp[right.index][y][0];
                            // both left and right children have router
                            int tmp2 = 0;
                            if (dp[left.index][x][1]>0 && dp[right.index][y][1]>0) {
                                tmp2 = dp[left.index][x][1] + dp[right.index][y][1] + node.s;
                            }
                            // only left node has router
                            int tmp3 = 0;
                            if (dp[left.index][x][1]>0) {
                                tmp3 = dp[left.index][x][1] + dp[right.index][y][0] + node.s;
                            }
                            // only right node has router
                            int tmp4 = 0;
                            if (dp[right.index][y][1]>0) {
                                tmp4 = dp[left.index][x][0] + dp[right.index][y][1] + node.s;
                            }
                            int tmp = Math.max(tmp1, tmp2);
                            tmp = Math.max(tmp, tmp3);
                            tmp = Math.max(tmp, tmp4);
                            if (tmp > max) {
                                max = tmp;
                                if (max != tmp1) flag[node.index][j][0] = true;
                            }
                        }
                        dp[node.index][j][0] = max;
                        // node has router
                        max = 0;
                        for (int k = 0; k <= j-1; k++) {
                            int x = k, y = j-1-k;
                            // left child has router
                            int tmp1 = dp[left.index][x][1];
                            // left child has no router
                            int tmp2 = dp[left.index][x][0];
                            if (!flag[left.index][x][0]) tmp2+=left.s;
                            int tmp11 = Math.max(tmp1, tmp2);
                            // right child has router
                            tmp1 = dp[right.index][y][1];
                            // right child has no router
                            tmp2 = dp[right.index][y][0];
                            if (!flag[right.index][y][0]) tmp2+=right.s;
                            int tmp22 = Math.max(tmp1, tmp2);
                            max = Math.max(tmp11+tmp22, max);
                        }
                        dp[node.index][j][1] = max;
                        flag[node.index][j][1] = true;
                    }
                }
                // only have one child
                else {
                    Point child = node.left == null ? node.right : node.left;
                    for (int j = 1; j <= M; j++) {
                        // 1. node has no router
                        // 1.1 child has router
                        int tmp1 = dp[child.index][j][1] + node.s;
                        // 1.2 child has no router
                        int tmp2 = dp[child.index][j][0];
                        if (tmp1 >= tmp2) {
                            dp[node.index][j][0] = tmp1;
                            flag[node.index][j][0] = true;
                        } else {
                            dp[node.index][j][0] = tmp2;
                        }
                        // 2. node has router
                        // 2.1. child has no router
                        tmp1 = dp[child.index][j-1][0] + (flag[child.index][j-1][0]?0:child.s);
                        // 2.2. child has router
                        tmp2 = dp[child.index][j-1][1];
                        //dp[node.index][j][1] = Math.max(dp[child.index][j][0]+node.s, dp[child.index][j][1]);
                        dp[node.index][j][1] += node.s + Math.max(tmp1, tmp2);
                        flag[node.index][j][1] = true;
                    }
                }
            }
            levelMap.remove(levelMap.size()-1);
        }

        //get result
        int result = 0;
        for (int j = 0; j <= M; j++) {
            result = Math.max(dp[root.index][j][0], result);
            result = Math.max(dp[root.index][j][1], result);
        }
        System.out.println(result);
    }

}
