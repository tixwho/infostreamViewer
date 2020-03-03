package reader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class StreamUserReader {
    List<String> names;
    Document document;
    public StreamUserReader(String rawHtmlAddr) throws IOException {
      //getNames方法，初步解析html用方法，读取存储html用的txt的内容，并使用jsoup对其进行分析、提取信息
        //使用的jsoup selector是".ipsStreamItem_status > a:first-child"，即每个.ipsStreamItem_status类的元素下第一个a标签的元素
        //这些元素的内容，也就是内容发布者的用户名会存储在一个List中，这个List就是该方法的返回值
        //Credit:DZero
        File input = new File(rawHtmlAddr);
        document = Jsoup.parse(input, "UTF-8");
        Elements elements = document.select(".ipsStreamItem_status > a:first-child");
        this.names = elements.eachText();

    }
    public StreamUserReader(File input) throws IOException {
        //getNames方法，初步解析html用方法，读取存储html用的txt的内容，并使用jsoup对其进行分析、提取信息
          //使用的jsoup selector是".ipsStreamItem_status > a:first-child"，即每个.ipsStreamItem_status类的元素下第一个a标签的元素
          //这些元素的内容，也就是内容发布者的用户名会存储在一个List中，这个List就是该方法的返回值
          //Credit:DZero
          document = Jsoup.parse(input, "UTF-8");
          Elements elements = document.select(".ipsStreamItem_status > a:first-child");
          this.names = elements.eachText();

      }
    
    public List<String> getPostUserList() {
        return names;
    }

}
