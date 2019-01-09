package version3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import version1.SixteenTailModel;

public class sixteenChange_Pane extends Application{

	public static void main(String[] args) {
		launch(args);
		
	 
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Pane pane=new Pane();
		Text text=new Text(20,20,"������Ӳ��״̬��H����T���棩��");
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
				int judge=0;//�ж��ַ��Ƿ�ȫΪH��T��������judge��ֵ1
				for(int i=0;i<tf.getText().length();i++){
					if(tf.getText().charAt(i)!='H'&&tf.getText().charAt(i)!='T'){
						judge=1;
						break;
					}
				}
				if(judge==1){
					Stage stage=new Stage();
					BorderPane borderpane=new BorderPane();
					borderpane.setCenter(new Text("��������"));
					Scene prompt=new Scene(borderpane,200,100);				
					stage.setScene(prompt);
					stage.setTitle("��ʾ��");		
					stage.show();
				}
				else{
					char[] initialNode=tf.getText().toCharArray();
					SixteenTailModel_Change model = new SixteenTailModel_Change();
				    
				    java.util.List<Integer> path =
				      model.getShortestPath(SixteenTailModel.getIndex(initialNode));
				    
				    if(path.size()==1){
				    	Stage stage=new Stage();
				    	BorderPane borderpane=new BorderPane();
				    	borderpane.setCenter(new Text("������޷���ת��ȫ��Ϊ���棡"));
				    	Scene scene=new Scene(borderpane,200,200);
				    	stage.setScene(scene);
				    	stage.setTitle("��ʾ��");	    	
				    	stage.show();
				    }//���ص��б�Ϊ1���޷���ת
				    else{
					    GridPane grid=new GridPane();
					    int x,y;//ÿ���ַ���λ������
					    for(int i=0;i<path.size();i++){
					    	x=10;
					    	y=10;				    	
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
					    			}//ÿ���ַ�������ǰһ���Ƚϣ��ַ���ͬ��任��ɫ
					    			 String str=SixteenTailModel.getNode(path.get(i).intValue())[j]+"";
						    		 Text ch=new Text(x,y,str);
						    		 flipping_pane.getChildren().add(ch);
					    			
					    		}//ÿ����4���ַ������y���궼����10
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
							    	x=x+10;//ÿ���ַ�x����10
							    	Text ch=new Text(x,y,str);
							    	flipping_pane.getChildren().add(ch);
							    	
					    		}
					    	}
					    	grid.add(flipping_pane, (i+1)*2,2);
					    	
					    	
					    }
					    Stage stage=new Stage();		    
					    Scene Flipping_process=new Scene(grid);
					    stage.setScene(Flipping_process);
					    stage.setTitle("��ת���棡");
					    stage.show();
				    }  
				}
				
			}
			else{
				Stage stage=new Stage();
				BorderPane borderpane=new BorderPane();
				borderpane.setCenter(new Text("�����������������룡"));
				Scene prompt=new Scene(borderpane,200,100);
				stage.setScene(prompt);
				stage.setTitle("��ʾ��");
				stage.show();
			}
		});
		
		start_sovle.setOnAction(e->{
			tf.setText("");
		});
		
		pane.getChildren().addAll(text,tf,solve,start_sovle);
		Scene scene=new Scene(pane);
		arg0.setTitle("��ʼ�����棡");
		arg0.setScene(scene);
		arg0.show();
		
	}
	}
