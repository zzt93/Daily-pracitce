
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zzt on 3/11/15.
 */
public class Scan {
    static String attributes = "║[\\w\\s]+│";
    static String end = "║";
    static String mid = "│";

    static final String infoFolder = "player/info/";
    String path;
    Scanner scanner;


    public Scan() {

    }

    public ArrayList<PlayerVO> read() throws FileNotFoundException {
        File file = new File(infoFolder);
        assert file.isDirectory();
        ArrayList<PlayerVO> res = new ArrayList<PlayerVO>();
        for (File file1 : file.listFiles()) {
            res.add(read(file1.getName()));
        }
        return res;
    }

    private PlayerVO read(String name) throws FileNotFoundException {
        path = infoFolder + name;
        scanner = new Scanner(new BufferedInputStream(new FileInputStream(new File(path))));

        String playerName = readTableTwoLine();
        int num = Integer.parseInt(readTableTwoLine().trim());
        String[] tmp = readTableTwoLine().split("-");
        Positions[] positions = new Positions[tmp.length];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = Positions.valueOf(tmp[i]);
        }
        String height = readTableTwoLine();
        String weight = readTableTwoLine();
        String birth = readTableTwoLine();
        BasicInfo basicInfo = new BasicInfo(playerName, num, positions, height, weight, birth);
        return new PlayerVO(basicInfo);
    }

    private String readTableTwoLine() {
        scanner.nextLine();//escape the first line of table
//        scanner.useDelimiter(end);
        String line = scanner.nextLine();
        String value = line.split(mid)[1];
        return value.substring(0, value.length()-2);
    }

    public static void main(String[] args) {
        try {
            new Scan().read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
