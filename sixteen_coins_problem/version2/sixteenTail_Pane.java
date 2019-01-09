package version2;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import version1.SixteenTailModel;

//16枚硬币反面问题
//author 全京圆

public class sixteenTail_Pane extends Application{

	public static void main(String[] args) {
		launch(args);
		
	 
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Pane pane=new Pane();
		Text text=new Text(20,20,"请输入硬币状态（H正面T反面）：");
		TextField tf=new TextField();
		tf.setLayoutX(20);
		tf.setLayoutY(30);
		tf.setPrefWidth(275);
		Button solve=new Button("solve");
		solve.setLayoutX(20);
		solve.setLayoutY(70);
		solve.setPrefSize(100,50);
		Button start_sovle=new Button("start solve");
		
		start_sovle.setLayoutX(175);
		start_sovle.setLayoutY(70);
		start_sovle.setPrefSize(100,50);
		
		solve.setOnAction(e->{
			if(tf.getText().length()==16){
				int judge=0;//判断字符是否全为H或T。不是则judge赋值1
				for(int i=0;i<tf.getText().length();i++){
					if(tf.getText().charAt(i)!='H'&&tf.getText().charAt(i)!='T'){
						judge=1;
						break;
					}
				}
				if(judge==1){
					Stage stage=new Stage();
					BorderPane borderpane=new BorderPane();
					borderpane.setCenter(new Text("输入有误！"));
					Scene prompt=new Scene(borderpane,200,100);				
					stage.setScene(prompt);
					stage.setTitle("提示！");		
					stage.show();
				}
				else{
					char[] initialNode=tf.getText().toCharArray();
					SixteenTailModel model = new SixteenTailModel();
				    
				    java.util.List<Integer> path =
				      model.getShortestPath(SixteenTailModel.getIndex(initialNode));
				    
				    if(path.size()==1){
				    	Stage stage=new Stage();
				    	BorderPane borderpane=new BorderPane();
				    	borderpane.setCenter(new Text("此情况无法翻转至全部为反面！"));
				    	Scene scene=new Scene(borderpane,200,200);
				    	stage.setScene(scene);
				    	stage.setTitle("提示！");	    	
				    	stage.show();
				    }//返回的列表为1则无法翻转
				    else{
					    GridPane grid=new GridPane();
					    int x,y;//每个字符的位置坐标
					    for(int i=0;i<path.size();i++){
					    	x=10;
					    	y=20;				    	
					    	Pane flipping_pane =new Pane();
					    	for(int j=0;j<16;j++){
					    		if(j!=0&&j%4==0){
					    			x=20;
					    			y=y+10;
					    			if(i!=0&&SixteenTailModel.getNode(path.get(i).intValue())[j]!=SixteenTailModel.getNode(path.get(i-1).intValue())[j]){
					    				String str=SixteenTailModel.getNode(path.get(i).intValue())[j]+"";
							    		Text ch=new Text(x,y,str);
							    		ch.setFill(Color.RED);
							    		flipping_pane.getChildren().add(ch);
							    		continue;
					    			}//每个字符数组与前一个比较，字符不同则变换颜色
					    			 String str=SixteenTailModel.getNode(path.get(i).intValue())[j]+"";
						    		 Text ch=new Text(x,y,str);
						    		 flipping_pane.getChildren().add(ch);
					    			
					    		}//每加入4个字符到面板y坐标都增加10
					    		else{
					    			if(i!=0&&SixteenTailModel.getNode(path.get(i).intValue())[j]!=SixteenTailModel.getNode(path.get(i-1).intValue())[j]){
					    				String str=SixteenTailModel.getNode(path.get(i).intValue())[j]+"";
					    				x=x+10;
							    		Text ch=new Text(x,y,str);
							    		ch.setFill(Color.RED);
							    		flipping_pane.getChildren().add(ch);
							    		continue;
					    			}
							    	String str=SixteenTailModel.getNode(path.get(i).intValue())[j]+"";
							    	x=x+10;//每个字符x轴间隔10
							    	Text ch=new Text(x,y,str);
							    	flipping_pane.getChildren().add(ch);
							    	
					    		}
					    	}
					    	grid.add(flipping_pane, (i+1)*2,2);
					    	
					    	
					    }
					    Stage stage=new Stage();		    
					    Scene Flipping_process=new Scene(grid);
					    stage.setScene(Flipping_process);
					    stage.setTitle("翻转界面！");
					    stage.show();
				    }  
				}
				
			}
			else{
				Stage stage=new Stage();
				BorderPane borderpane=new BorderPane();
				borderpane.setCenter(new Text("输入有误请重新输入！"));
				Scene prompt=new Scene(borderpane,200,100);
				stage.setScene(prompt);
				stage.setTitle("提示！");
				stage.show();
			}
		});
		
		start_sovle.setOnAction(e->{
			tf.setText("");
		});
		
		pane.getChildren().addAll(text,tf,solve,start_sovle);
		Scene scene=new Scene(pane);
		arg0.setTitle("初始化界面！");
		arg0.setScene(scene);
		arg0.show();
		
	}
	}
