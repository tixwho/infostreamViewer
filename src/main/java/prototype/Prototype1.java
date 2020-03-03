//姑且也写上导入列表，主要是io类(Exception标记和读取、写入文件)和util类(List相关的一些方法)，
//还有最重要的是jsoup，需要用来解析html文件
package prototype;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Prototype1 {
    
    static Document document;

    public static void main(String[] args) throws Exception {
        //main，程序启动并寻求用户输入，确认输入0或1后执行writeData方法
        Scanner inputScanner = new Scanner(System.in);
        int choice;
        
        System.out.println("请选择模式：");
        System.out.println("0:整理toParse.txt中的数据");
        System.out.println("1:查找toCompare.txt中的误差");
        do {
            choice = Integer.parseInt(inputScanner.nextLine());
            if (!(choice == 0) && !(choice == 1))
                System.out.println("请输入0或1");
        } while (!(choice == 0) && !(choice == 1));
        
        inputScanner.close();
        writeData(choice);
    }
    
    public static List<String> getNames() throws IOException {
        //getNames方法，初步解析html用方法，读取存储html用的txt的内容，并使用jsoup对其进行分析、提取信息
        //使用的jsoup selector是".ipsStreamItem_status > a:first-child"，即每个.ipsStreamItem_status类的元素下第一个a标签的元素
        //这些元素的内容，也就是内容发布者的用户名会存储在一个List中，这个List就是该方法的返回值
        File input = new File("D:/eclipse/eclipse-workspace/jsoup selector/temp/toParse.txt");
        document = Jsoup.parse(input, "UTF-8");
        Elements elements = document.select(".ipsStreamItem_status > a:first-child");
        List<String> names = elements.eachText();
        
        return names;
    }
    
    public static List<String> nameCounter(List<String> names) throws IOException {
        //nameCounter，统计用户用方法，需要一个以用户名作为item的List作为输入值，正常执行程序时默认将getNames方法的返回值作为输入值
        //在List末端加入一个总内容数统计后以List中第一个item不等于内容数统计为条件执行while循环，
        //每次循环会寻找List中第一个item的重复次数，即第一个用户的内容数，随后删除所有该用户的item，并在List最后以"名称   内容数"的格式输入信息
        //返回一个新的List，该List的第一个item是日总内容数，接下来每个item为每个用户的用户名和内容数
        List<String> counter = names;
        String total = "共计 " + counter.size();
        counter.add(total);
        
        while (!counter.get(0).equals(total)) {
            String targetName = counter.get(0);
            int occurences = Collections.frequency(counter, targetName);
            counter.removeAll(Collections.singletonList(targetName));
            counter.add(targetName + "  " + occurences);
        }
        return counter;
    }
    
    public static List<String> nameComparer() throws IOException {
        //nameComparer，比较正倒序动态流差值用方法，需要手动将正序统计结果和倒序统计结果存储在同一个txt中才能正常运作
        //由于统计结果已经转换为"名称    内容数"的格式，该方法仅需删除所有出现次数大于等于2的item即可，剩余item即为两次统计的差，可以简单地人为修正统计结果
        //实际实现方法与nameCounter方法类似，不再赘述
        List<String> file = Files.readAllLines(new File("D:/eclipse/eclipse-workspace/jsoup selector/temp/toCompare.txt").toPath(), Charset.forName("UTF-8"));
        
        file.add("相异内容数如下");
        while (!file.get(0).equals("相异内容数如下")) {
            String targetName = file.get(0);
            int occurences = Collections.frequency(file, targetName);
            file.removeAll(Collections.singletonList(targetName));
            if ((occurences == 1))
                file.add(targetName);
        }
        
        return file;
    }
    
    public static void writeData(int choice) throws Exception {
        //writeData，输出用方法，若main中输入数字为0，读取nameCounter(统计动态流html)方法所返回的List；若main中输入数字为1，读取nameComparer(比较正倒序差异)方法所返回的List。
        //根据输入数字不同，输出文件名称也会不同。获得List之后按每个item(即"名称  内容数"格式的信息)一行的方式将数据写入目标文件
        //若目标文件存在，会先删除再写入，因此比较数据时，在读取倒序动态流前应先将正序动态流保存为不同名的文件
        List<String> names;
        File resultDirection;
        if (choice == 0) {
            names = nameCounter(getNames());
            resultDirection = new File("D:/eclipse/eclipse-workspace/jsoup selector/temp/parsedName.txt");
        } else {
            names = nameComparer();
            resultDirection = new File("D:/eclipse/eclipse-workspace/jsoup selector/temp/comparedAnomaly.txt");
        }
            if(resultDirection.exists()) 
                resultDirection.delete();
            PrintWriter writer = new PrintWriter(resultDirection);
            for(int i = 0; i<names.size(); i++)
                writer.println(names.get(i));
            writer.close();
        
        System.out.print("printed");
    }
}
