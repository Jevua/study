set Axis_Lib=X:\axis\axis-1_4\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Output_Path=X:\axis
set Package=com.test
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package% --server-side http://10.6.135.5/iAppServices/iApp/ThirdDataServies.asmx?wsdl