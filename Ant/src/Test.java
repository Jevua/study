import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
public class Test {  
    public static void main(String[] args)  throws IOException{  
		Scanner sc = new Scanner(System.in);
		System.out.println("����Ŀ¼��:");
		String content=sc.next();
		content = content.trim();
		File dir = new File(content);
		dir.mkdirs();
		Scanner sc1 = new Scanner(System.in);
		System.out.println("�������񵥺�:");
		String number=sc1.next();
		String path = dir.getAbsolutePath();
		System.out.println(path);
		FileUtils.copyFile(new File("��Ԫ���Ա���_����.doc"), new File("��Ԫ���Ա���_����2.doc"));
		File file = new File("��Ԫ���Ա���_����2.doc");
		file.renameTo(new File(path+"/��Ԫ���Ա���_����"+number+".doc"));
    }  
}  