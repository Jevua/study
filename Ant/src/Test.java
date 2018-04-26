import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
public class Test {  
    public static void main(String[] args)  throws IOException{  
		Scanner sc = new Scanner(System.in);
		System.out.println("输入目录名:");
		String content=sc.next();
		content = content.trim();
		File dir = new File(content);
		dir.mkdirs();
		Scanner sc1 = new Scanner(System.in);
		System.out.println("输入任务单号:");
		String number=sc1.next();
		String path = dir.getAbsolutePath();
		System.out.println(path);
		FileUtils.copyFile(new File("单元测试报告_任务单.doc"), new File("单元测试报告_任务单2.doc"));
		File file = new File("单元测试报告_任务单2.doc");
		file.renameTo(new File(path+"/单元测试报告_任务单"+number+".doc"));
    }  
}  