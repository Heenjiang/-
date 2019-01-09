package data_compressionAnddecompression;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



import data_compressionAnddecompression.Get_characterCount_hafmantree_codes.Tree;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class operation_interface extends Application{

	
	static String str_codes="";//一个字符串的hfm编码
	static String bits_encode="";//一个hfm编码的字符串解码
	static String codefile="";//存储codefile的数据
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane pane=new GridPane();		
		pane.setVgap(10);
		pane.setHgap(10);		
		pane.add(new Label("Enter a text:"), 5, 5);
        TextField tf=new TextField();
        tf.setPromptText("Enter a text:!");
		tf.setText("");
		pane.add(tf, 6, 5);
		Button Show_hfm_button=new Button("Show Huffman Tree");
		pane.add(Show_hfm_button, 7, 5);
		
		pane.add(new Label("Enter a string to encode:"),5, 6);
		TextField code_str=new TextField();
		code_str.setPromptText("Enter a string!");
		code_str.setText("");
		pane.add(code_str, 6, 6);
		Button str_button=new Button("Decode string");
		pane.add(str_button, 7, 6);
		
		pane.add(new Label("Enter a bit string:"), 5, 7);
		TextField bits=new TextField();
		bits.setText("");
		bits.setPromptText("Enter a bit string!");
		pane.add(bits, 6, 7);
		Button bits_button=new Button("Decode bits");
		pane.add(bits_button, 7, 7);		
		Button codefile_print=new Button("codefile print");
		pane.add(codefile_print, 6, 8);
		Show_hfm_button.setOnAction(e->{
			if(tf.getText().compareTo("")!=0){				
				try {
					Clear_file("codefile.txt");
					Clear_file("treeprint.txt");
					Clear_file("textfile.txt");
					Clear_file("codeprint.txt");
				} //每次输入一个字符串建立一个新的哈夫曼树的时候就清空前一个字符串在文件中的数据
				catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
				int[] counts=Get_characterCount_hafmantree_codes.getCharacterFrequency(tf.getText());
				Get_characterCount_hafmantree_codes.Tree tree=Get_characterCount_hafmantree_codes.getHuffmanTree(counts);
				String[] codes=Get_characterCount_hafmantree_codes.getCode(tree.root);
				
				File file=new File("treeprint.txt");
				File hfmtree=new File("hfmtree.txt");
				try {
					FileWriter fw=new FileWriter(hfmtree,true);
					PrintWriter pw=new PrintWriter(file);
					pw.print(tree.root);
					fw.write(tf.getText()+"\r\n");
					fw.close();
					pw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Pane pane1=new Pane();
				Stage stage1=new Stage();
				Scene scene1=new Scene(TreeView.displayTree(pane1,tree.root, 300, 20, 150));
				stage1.setTitle("Huffman Tree");
				stage1.setScene(scene1); 
				stage1.show();				
			}//弹出一个新窗口显示Hfm树
			else{
				BorderPane pane1=new BorderPane();
				pane1.setCenter(new Label("Please Enter a text!"));
				Stage stage1=new Stage();
				Scene scene1=new Scene(pane1,300,100);
				stage1.setTitle("Remind!");
				stage1.setScene(scene1); 
				stage1.show();
			}
			//当输入为空时弹出提示！
		});
		
		
		str_button.setOnAction(e->{
		if(code_str.getText().compareTo("")!=0&&tf.getText().compareTo("")!=0){
			int[] counts=Get_characterCount_hafmantree_codes.getCharacterFrequency(tf.getText());
			Get_characterCount_hafmantree_codes.Tree tree=Get_characterCount_hafmantree_codes.getHuffmanTree(counts);
			String[] codes=Get_characterCount_hafmantree_codes.getCode(tree.root);
			
			for(int i=0;i<code_str.getText().length();i++){
				inorder(tree.root,code_str.getText().charAt(i));
			}
			if(str_codes==""){
				str_codes=null;
			}
			BorderPane pane1=new BorderPane();
			Stage stage1=new Stage();
			Text text=new Text(code_str.getText()+" is encode to "+str_codes);
			
			File codefile=new File("codefile.txt");
			try {
				FileWriter fw=new FileWriter(codefile,true);
				fw.write(str_codes+"\r\n");
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			pane1.setCenter(text);
			str_codes="";
			Scene scene1=new Scene(pane1,300,100);
		 	stage1.setTitle("string_encode");
			stage1.setScene(scene1); 
			stage1.show(); 
			}
			else{
				BorderPane pane1=new BorderPane();
				pane1.setCenter(new Label("Please Enter a string and ensure text is not null!"));
				Stage stage1=new Stage();
				Scene scene1=new Scene(pane1,300,100);
				stage1.setTitle("Remind!");
				stage1.setScene(scene1); 
				stage1.show();
			}
			//若输入为空弹出提醒
		});
		
		
		bits_button.setOnAction(e->{
		if(bits.getText().compareTo("")!=0&&tf.getText().compareTo("")!=0){
			int k=0;//判断输入的字符串是否为比特流
			for(int i=0;i<bits.getText().length();i++){
				if(bits.getText().charAt(i)!='0'&&bits.getText().charAt(i)!='1'){
					k=1;
					break;
				}
			}
			if(k==0){
				int[] counts=Get_characterCount_hafmantree_codes.getCharacterFrequency(tf.getText());
				Get_characterCount_hafmantree_codes.Tree tree=Get_characterCount_hafmantree_codes.getHuffmanTree(counts);
				String[] codes=Get_characterCount_hafmantree_codes.getCode(tree.root);
				
				Get_characterCount_hafmantree_codes.Tree.Node current=tree.root;//建立一个Node保存当前节点
				for(int i=0;i<bits.getText().length();i++){
					  if(bits.getText().charAt(i)=='1'){
						 if(current.element==0){
							 current=current.right;
							 if(current.element!=0){
								 bits_encode=bits_encode+current.element;
								 current=tree.root;
							 }
							 continue;
						 }
						 
					  }
					  
					  else if(bits.getText().charAt(i)=='0'){
						  
						  if(current.element==0){
								 current=current.left;
								 if(current.element!=0){
									 bits_encode=bits_encode+current.element;
									 current=tree.root;
								 }
								 continue;
							 }
					  }					  
				}
				//对比特流解码并将结果保存在bits_encode
				
				File file=new File("textfile.txt");
				try {
					FileWriter fw=new FileWriter(file);
					fw.write(bits_encode+"\r\n");
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				BorderPane pane1=new BorderPane();
				Stage stage1=new Stage();
				Text text=new Text(bits.getText()+" is encode to "+bits_encode);
				pane1.setCenter(text);
				bits_encode="";
				Scene scene1=new Scene(pane1,300,100);			    
				stage1.setTitle("string_encode");
				stage1.setScene(scene1); 
				stage1.show(); 
			}
			else{
				BorderPane pane1=new BorderPane();
				pane1.setCenter(new Label("Please Enter a bits!"));
				Stage stage1=new Stage();
				Scene scene1=new Scene(pane1,300,100);
				stage1.setTitle("Remind!");
				stage1.setScene(scene1); 
				stage1.show();
			}
			//输入的不是比特流则提醒
		}
		else{
			BorderPane pane1=new BorderPane();
			pane1.setCenter(new Label("Please Enter a bits and ensure text is not null!"));
			Stage stage1=new Stage();
			Scene scene1=new Scene(pane1,300,100);
			stage1.setTitle("Remind!");
			stage1.setScene(scene1); 
			stage1.show();
		}
		});
		
		codefile_print.setOnAction(e->{
			File file=new File("codefile.txt");
			File codeprint=new File("codeprint.txt");
			GridPane bits_pane=new GridPane();

			try {
				Scanner input=new Scanner(file);
				while(input.hasNext()){
					codefile=codefile+input.nextLine();
				}
				FileWriter fw=new FileWriter(codeprint);
				String bits_str="";
				int k=2;
				for(int i=0 ;i<codefile.length();i++){
					  if(i%50==0&&i!=0){
						  System.out.println();
						  fw.write("\r\n");
						  Text text=new Text(bits_str);
						  bits_pane.add(text,4,k++);
						  bits_str="";
					  }
					  bits_str=bits_str+codefile.charAt(i);
					  fw.write(codefile.charAt(i));
				  }
				  Text text=new Text(bits_str);
				  bits_pane.add(text,4,k++);				
				  fw.close();
				  codefile="";
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			BorderPane pane1=new BorderPane();
			pane1.setCenter(bits_pane);
			Stage stage1=new Stage();
			Scene scene1=new Scene(pane1,400,400);
			stage1.setTitle("Codefile print!");
			stage1.setScene(scene1); 
			stage1.show(); 
		});
	    Scene scene=new Scene(pane,650,250); 
	    primaryStage.setTitle("Operating interface!");
	    primaryStage.setScene(scene); 
	    primaryStage.show(); 
	
	}

	public static void inorder(Get_characterCount_hafmantree_codes.Tree.Node root,char ch){
		if(root==null)
			return;
		inorder(root.left,ch);
		if(root.element==ch)
			str_codes=str_codes+root.code;
		inorder(root.right,ch);
	}//对树进行先序遍历寻找字符ch
	
	public static void Clear_file(String file_name) throws Exception{
		File file =new File(file_name);
		if(file.exists()&&file.isFile())
		file.delete();
		file.createNewFile();
	}//清空文件
     
	  public static void main(String[] args) throws Exception{
			  launch(args);
	  }

	

}
