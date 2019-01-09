package 大二课程设计;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FarmerCrossesRiver extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub    
		launch(args);
	}
	List<String> south=new ArrayList<String>();//存放南岸的动物的链表自
	List<String> north=new ArrayList<String>();//存放北岸的动物的链表自
	VBox vboxSouth=new VBox();//存放南岸的动物的竖直面板
	VBox vboxNorth=new VBox();//存放北岸的动物的竖直面板
	VBox vboxStep=new VBox();//存放步骤的竖直面板
	int count=1;//存放步骤的变量
	public void initial_wolf_cabbage_sheep(){			
		south.add("wolf");
		south.add("cabbage");
		south.add("sheep");
	}
	public void initial_cabbage_wolf_sheep(){
		south.add("cabbage");
		south.add("wolf");
		south.add("sheep");
	}//两种顺序不同的初始化方法
	public boolean isFrendly(List<String> list){
		if(list.contains("wolf")&&list.contains("sheep")||list.contains("cabbage")&&list.contains("sheep"))
			return false;
		else
			return true;
	}
	public void southTonorth(){
		String str1=south.get(0);		
		south.remove(str1);
		if(isFrendly(south)){
			north.add(str1);
			TextField tfsouth=new TextField();
			TextField tfnorth=new TextField();
			tfsouth.setPrefSize(200, 50);
			tfnorth.setPrefSize(200, 50);
			String strSouth="";
			String strNorth="";
			for(int i=0;i<north.size();i++)
				strNorth=strNorth+" "+north.get(i);
			strNorth=strNorth+" "+"farmer";
			for(int i=0;i<south.size();i++)
				strSouth=strSouth+" "+south.get(i);
			
			tfsouth.setText(strSouth);
			tfnorth.setText(strNorth);
			vboxSouth.getChildren().add(tfsouth);
			vboxNorth.getChildren().add(tfnorth);
			
			String step=count+"";
			count++;
			Button button=new Button(step);
			button.setPrefSize(80, 50);
			vboxStep.getChildren().add(button);	
			northTosouth();
		}
		else{
			south.add(str1);
			southTonorth();//若不能带str1过河则递归
		}
	}//从南岸过河到北岸的方法
	
	public void northTosouth(){
		if(south.isEmpty()){
			return;
		}//全部带到北岸时停止递归
		if(isFrendly(north)){
			TextField tfsouth=new TextField();
			TextField tfnorth=new TextField();
			tfsouth.setPrefSize(200, 50);
			
			tfnorth.setPrefSize(200, 50);
			String strSouth="";
			String strNorth="";
			for(int i=0;i<north.size();i++)
				strNorth=strNorth+" "+north.get(i);
			for(int i=0;i<south.size();i++)
				strSouth=strSouth+" "+south.get(i);
			strSouth=strSouth+" "+"farmer";
			tfsouth.setText(strSouth);
			tfnorth.setText(strNorth);
			vboxSouth.getChildren().add(tfsouth);
			vboxNorth.getChildren().add(tfnorth);
			
			
			String step=count+"";
			count++;
			Button button=new Button(step);
			button.setPrefSize(80, 50);
			vboxStep.getChildren().add(button);
			southTonorth();
		}
		else{
			String str=north.get(0);
			north.remove(str);
			if(isFrendly(north)){
				south.add(str);
				TextField tfsouth=new TextField();
				TextField tfnorth=new TextField();
				tfsouth.setPrefSize(200, 50);
				tfnorth.setPrefSize(200, 50);
				String strSouth="";
				String strNorth="";
				for(int i=0;i<north.size();i++)
					strNorth=strNorth+" "+north.get(i);
				for(int i=0;i<south.size();i++)
					strSouth=strSouth+" "+south.get(i);
				strSouth=strSouth+" "+"farmer";
				tfsouth.setText(strSouth);
				tfnorth.setText(strNorth);
				vboxSouth.getChildren().add(tfsouth);
				vboxNorth.getChildren().add(tfnorth);
				
				String step=count+"";
				count++;
				Button button=new Button(step);
				button.setPrefSize(80, 50);
				vboxStep.getChildren().add(button);
				southTonorth();
			}
			else{
				north.add(str);
				northTosouth();
			}//若不能带str过河则递归
		}
	}
	
	int n=0;//使用n%2==0或1来使用不同的初始化方法
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Pane pane=new Pane();
		Text title=new Text(110,50,"Farmer To River Problem！");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));//设置字体为粗体并设置大小		
		Button button=new Button("Cross River");
		button.setPrefSize(150,50);
		button.setLayoutX(170);
		button.setLayoutY(60);		
		Text text=new Text(140,130,"South    Step    North");
		text.setFont(Font.font(null, FontWeight.BOLD, 20));
		pane.getChildren().addAll(title,text,button);
		
		button.setOnAction(e->{
			count=1;
			vboxSouth.getChildren().clear();
			vboxStep.getChildren().clear();
			vboxNorth.getChildren().clear();
			south.clear();
			north.clear();
            pane.getChildren().clear();//每次点击按钮都要清空链表和面板
            if(n%2==0){
            	initial_wolf_cabbage_sheep();//初始化
            	n++;
            }
            else{
            	initial_cabbage_wolf_sheep();
            	n++;
            }		
			southTonorth();//开始过河	
			pane.getChildren().addAll(title,text,button,vboxSouth,vboxStep,vboxNorth);
			vboxSouth.setLayoutX(1);
			vboxSouth.setLayoutY(140);
			vboxStep.setLayoutX(201);
			vboxStep.setLayoutY(140);
			vboxNorth.setLayoutX(280);
			vboxNorth.setLayoutY(140);
			
		});
		Scene scene=new Scene(pane,500,600);
		arg0.setScene(scene);
		arg0.setTitle("农夫过河");
		arg0.show();

	}

}
