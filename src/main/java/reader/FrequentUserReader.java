package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FrequentUserReader {
    private ArrayList<String> frequentUserList = new ArrayList<String>();
    public FrequentUserReader(File inFile) throws IOException {
      //read as a file, then divide into users by line and pack into ArrayList
        InputStreamReader in = new InputStreamReader(new FileInputStream(inFile),"utf-8");
        BufferedReader br=new BufferedReader(in);
        String tempData;
        while((tempData = br.readLine())!=null) {
            frequentUserList.add(tempData);
        }
        br.close();
    }
    public ArrayList<String> getFrequentUserList(){
        return this.frequentUserList;
    }
    

}
