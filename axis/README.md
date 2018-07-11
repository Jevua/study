### wsdl文件链接转为java代码

axis-1_4  
1、下载相应包并解压  
2、写个bat文件  
bat文件如下：（去掉后面备注）  
set Axis_Lib=C:\axislib\axislib\lib    （这是axis的lib包）
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Output_Path=C:\soap_src\src （这是生成的java代码输出的位置）
set Package=com.huawei.mdn.wsi.engine.client
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package% --server-side TvodMergeStatus.wsdl （源wsdl文件）
3、运行就可以得到相应的文件


axis2  
1、下载解压包  
2、进入bin目录执行命令  
wsdl2java.bat -uri http://10.6.135.5/iAppServices/iApp/ThirdDataServies.asmx?wsdl -o X:\axis -p com.bd.zd  
-uri : wsdl文件的位置，注意检查文件路径之间不要有空格哦~有空格就需要把这段路径加“”（引号）  
-o:文件的输出位置。默认情况两个文件（ java文件及build.xml）都在axis2-1.5\bin目录下  
-p:生成的java文件的包名  
注意生成的java包目录会默认加上一层src（如axis2-1.5.5\bin\src\com\zhejiangcourt\ssoverifyweb）  