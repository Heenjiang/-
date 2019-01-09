package data_compressionAnddecompression;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TreeView {
	private static int radius=20;//圆的半径
	private static int vGap=50;//每个节点的竖直距离
//root 是hfm树，x，y为根节点圆心，hGap每个节点的水平距离    
public static  Pane displayTree(Pane pane,Get_characterCount_hafmantree_codes.Tree.Node root,int x,int y,int hGap){

	Circle circle=new Circle(x,y,radius);
	circle.setFill(Color.WHITE);
	circle.setStroke(Color.BLACK);
	
	String str=root.weight+"";
	Text text=new Text(x-5,y+2,str);
	Text text_element=new Text(x-5,y+33,root.element+"");
    pane.getChildren().addAll(circle,text,text_element);	
    if(root.left!=null){
    	Text element_road=new Text((x-hGap+x)/2-5,(y+vGap+y)/2,"0");
    	pane.getChildren().addAll(connectLeft(x-hGap,y+vGap,x,y),element_road);
    	displayTree(pane,root.left,x-hGap,y+vGap,hGap/2);
    }
    if(root.right!=null){
    	Text element_road=new Text((x+hGap+x)/2,(y+vGap+y)/2,"1");
    	pane.getChildren().addAll(connectRight(x+hGap,y+vGap,x,y),element_road);
    	displayTree(pane,root.right,x+hGap,y+vGap,hGap/2);
    }
   
    return pane;
	}

private static Line connectLeft(int x1,int y1,int x2,int y2){
	double d=Math.sqrt(vGap*vGap+(x2-x1)*(x2-x1));
	int x11=(int)(x1+radius*(x2-x1)/d);
	int y11=(int)(y1-radius*vGap/d);
	int x21=(int)(x2-radius*(x2-x1)/d);
	int y21=(int)(y2+radius*vGap/d);
	Line line=new Line(x11,y11,x21,y21);
	return line;
}
//x1,y1是圆心x2,y2的左节点的圆心，line是圆心之间的连线
private static Line connectRight(int x1,int y1,int x2,int y2){
	double d=Math.sqrt(vGap*vGap+(x2-x1)*(x2-x1));
	int x11=(int)(x1-radius*(x1-x2)/d);
	int y11=(int)(y1-radius*vGap/d);
	int x21=(int)(x2+radius*(x1-x2)/d);
	int y21=(int)(y2+radius*vGap/d);
	Line line=new Line(x11,y11,x21,y21);
	return line;
}
//x1,y1是圆心x2,y2的右节点的圆心，line是圆心之间的连线
}
