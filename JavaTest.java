package Test;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
public class FileOutputTest {
	
//function to create Table schema information in metadata file;
public static void createTable(String query) throws IOException {
	//create Table Customer(col1 type1 constraint1,col2 type2 constraint2);
	try {
		FileOutputStream fout=new FileOutputStream("j://JavaTest/metadata.txt");
		int lastIndexOfTable=0;
		int stIndexOfOB=0;
		int stIndexOfCB=0;
		Pattern table=Pattern.compile("table");
		Matcher m=table.matcher(query.toLowerCase());
		if(m.find()) {
			lastIndexOfTable=m.end();
		}
	    stIndexOfOB=query.indexOf("(");
	    stIndexOfCB=query.lastIndexOf(")");
		String tableName=query.substring(lastIndexOfTable+1,stIndexOfOB);
		String colInfo=query.substring(stIndexOfOB+1,stIndexOfCB);
		String[] col=colInfo.split(",");
		byte[] t=tableName.getBytes();
		fout.write(t);
		fout.write(13);
		for(int i=0;i<col.length;i++) {
			String s=col[i].trim();
			byte[] b=s.getBytes();
			fout.write(b);
			fout.write(13);
		}
		fout.close();
		System.out.println("Table created successfully ****** info is available in metadata.txt file");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//function to insert values in data_file;
public static void insertValues(String query) {
	//insert into Customer(col1,col2,col3)VALUES("Ramayan","56","mahabharat"),("Karan","87","Kishan");
	String values="";
	Pattern p=Pattern.compile("values");
	Matcher m=p.matcher(query.toLowerCase());
	int endIndexOfVal=0;
	if(m.find()) {
		endIndexOfVal=m.end();
	}
	Pattern p1=Pattern.compile("\\(");
	Matcher m1=p1.matcher(query.toLowerCase());
	if(m1.find(endIndexOfVal)) {
	values=query.substring(m1.end()-1);
	}
    ArrayList<ArrayList<Integer>> al=new ArrayList<>();
    ArrayList<Integer> index=new ArrayList<>();
    for(int i=0;i<values.length();i++) {
    	if(values.charAt(i)=='(') {
    		index.add(i);
    	}
    	else if(values.charAt(i)==')') {
    		index.add(i);
    	}
    	if(index.size()==2) {
    		al.add(index);
    		index=new ArrayList<>();
    	}
    }
    	try {
    	    FileOutputStream fout=new FileOutputStream("j://JavaTest/data.txt");
    	    for(int i=0;i<al.size();i++) {
    	    String data=values.substring(al.get(i).get(0)+1,al.get(i).get(1));
    	    data=data.replaceAll("\\,"," ");
    	    data=data.replaceAll("\\'", "");
    		byte[] b=data.getBytes();
    		fout.write(b);
    		fout.write(13);}
    	    System.out.println("values inserted into table successfully ************** you can find it on data.txt file");
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    }


public static void main(String arg[]) throws IOException {
System.out.println("************ Enter your sql query *************");
Scanner sc=new Scanner(System.in);
String query=sc.nextLine().trim();
if(query.toLowerCase().contains("create table")) {
	createTable(query);
}
else if(query.toLowerCase().contains("insert into")) {
	insertValues(query);
}
else {
	System.out.println("query syntax error *******");
}
}
}

