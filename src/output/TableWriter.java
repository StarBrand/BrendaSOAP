package output;

import static java.lang.StrictMath.max;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class TableWriter {

  public static void write(List<String> row, String sep, BufferedWriter bw) throws IOException {
    String line = "";
    for(String col:row){
      line += col + sep;
    }
    line = line.substring(0, max(line.length() - 1, 0));
    bw.write(line);
    bw.newLine();
  }

}
