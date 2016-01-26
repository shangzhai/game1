import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Monster {
double  n=Math.random()*600;
double  m=Math.random()*500;
Texture boss;
static int num=0;
int k=(int)(Math.random()*4),j=0;                //怪兽方向，随机生成
double i[]={1};
int f=(int) (Math.random()*4);
double c,cc;                                      //怪兽四向图长和宽的系数

Monster()
{   try {                                                      //随机载入怪兽图片
	if(f==0)
		{boss= TextureLoader.getTexture("png", ResourceLoader
			.getResourceAsStream("财主.png"));
		c=0.7;
	    cc=0.6875;}
	if(f==1)
		{boss= TextureLoader.getTexture("png", ResourceLoader
				.getResourceAsStream("牛魔王.png"));
		c=0.6;
	cc=0.5625;}
	if(f==2)
		{boss= TextureLoader.getTexture("png", ResourceLoader
				.getResourceAsStream("程咬金.png"));
		c=0.9;
		cc=0.875;}
	if(f==3)
	{	boss= TextureLoader.getTexture("png", ResourceLoader
				.getResourceAsStream("黑熊精.png"));
	c=0.5625;
	cc=0.95;
	}
	
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
    num++;
	}
}
