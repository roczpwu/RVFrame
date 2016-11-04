import com.roc.core.Utils.StringUtil;
import com.roc.core.user.UserDAO;
import com.roc.core.user.UserDTO;

import java.util.*;

/**
 * Created by rocwu on 16/7/24.
 */
class Point1 {
    int x=0;
    int y=0;
    int z=0;
}

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println(StringUtil.getMd5("22222"));

        UserDAO dao = new UserDAO();
        UserDTO dto = (UserDTO) dao.where("1=1").one();
        dto.setNick_name("22222");
        dao.save(dto);
        dto = new UserDTO();
        dto.setNick_name("33333");
        dao.save(dto);

        int M, N, P;
        int[][][] magicCube;
        List<int[][][]> smallCubes = new ArrayList<>();
        List<Point1> smallCubePositions = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        M = in.nextInt();
        N = in.nextInt();
        P = in.nextInt();

        magicCube = new int[M][M][M];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                for (int k = 0; k < M; k++)
                    magicCube[i][j][k] = in.nextInt();
        for (int o = 0; o < N; o++) {
            int size = in.nextInt();
            int[][][] smallCube = new int[size][size][size];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    for (int k = 0; k < size; k++)
                        smallCube[i][j][k] = in.nextInt();
            smallCubes.add(smallCube);
            smallCubePositions.add(new Point1());
        }

        solve(magicCube, smallCubes, smallCubePositions, 0, P);
        for (Point1 smallCubePosition : smallCubePositions) {
            System.out.println(smallCubePosition.x + " "
                    + smallCubePosition.y + " "
                    + smallCubePosition.z);
        }
    }

    private static boolean solve(int[][][]currentCube,
                                 List<int[][][]> smallCubes,
                                 List<Point1> smallCubePositions,
                                 int currentIndex,
                                 int P) {
        int smallCudeSize = smallCubes.get(currentIndex).length;
        int magicCubeSize = currentCube.length;
        int maxOffset = magicCubeSize - smallCudeSize;
        for (int i = 0; i <= maxOffset; i++) {
            for (int j = 0; j <= maxOffset; j++) {
                for (int k = 0; k <= maxOffset; k++) {

                    smallCubePositions.get(currentIndex).x = i;
                    smallCubePositions.get(currentIndex).y = j;
                    smallCubePositions.get(currentIndex).z = k;
                    for (int _i = 0; _i < smallCudeSize; _i++)
                        for (int _j = 0; _j < smallCudeSize; _j++)
                            for (int _k = 0; _k < smallCudeSize; _k++)
                                currentCube[i+_i][j+_j][k+_k]+=smallCubes.get(currentIndex)[_i][_j][_k];
                    if (smallCubes.size() == currentIndex+1) {
                        boolean isMagic = true;
                        for (int _i = 0; _i < magicCubeSize; _i++)
                            for (int _j = 0; _j < magicCubeSize; _j++)
                                for (int _k = 0; _k < magicCubeSize; _k++)
                                    if (currentCube[_i][_j][_k] % P != 0) {
                                        isMagic = false;
                                        break;
                                    }
                        if (isMagic) return true;
                    } else {
                        return solve(currentCube, smallCubes, smallCubePositions, currentIndex+1, P);
                    }
                    for (int _i = 0; _i < smallCudeSize; _i++)
                        for (int _j = 0; _j < smallCudeSize; _j++)
                            for (int _k = 0; _k < smallCudeSize; _k++)
                                currentCube[i+_i][j+_j][k+_k]-=smallCubes.get(currentIndex)[_i][_j][_k];
                }
            }
        }
        return false;
    }
}
