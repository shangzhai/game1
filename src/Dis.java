import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Dis implements Runnable {

	static Texture texture;
	Texture bg, logo,bg2,winner,texture1,pause;
	Audio m1, m2,m3;
	Thread one = new Thread(this);
	Zidan []bt=new Zidan[1000];
    Monster []ms=new Monster[1000];
	static int  n = 400, m = 300, k = 0, j = 0,jj=0;
	static double x = 700, y = 515;
	int win=0,rol=1;
	int limt=3;
	int ok=0,ok2=0;
	int start=0;
    int wait=1;
	double i[] = { 1 };
	double ii[] = { 1 };
	Date date;
	int hour1,min1,sec1,hour2,min2,sec2,result;
	String str;

	public void start() {

		initGL(800, 600);
      
		init();
		one.start();     //执行计时线程
		
		
		while (true) {

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			  if(ok==0&&start==1)
				{m1.playAsMusic(1.0f, 1.0f,true);    //背景音乐控制
				
				ok=1;}
			  if(ok2==0)
			  { 
				  m3.playAsMusic(1.0f, 1.0f, false);   //开局音乐和胜利音乐控制
				  ok2=1;
			  }
			if(start==0)
			{
				background(0.78125,0.58,logo);     //logo显示
				begin();
			}
			if(wait==0)
				{background(0.78125,0.58,pause);    //暂停界面显示
				waiting();}
		  
			if(rol==1&&!(win==5&&Monster.num==0)&&start==1&&wait==1){
				hit();
				background(0.625,0.9375,bg);                //游戏战斗
				monster();
			    render();
			    update();
		        fire();
		        }
			if(rol==0){
				background(1,0.75,bg2);                //失败界面显示
				
				reset();
				
				
			
			}
			if(rol==1&&win==5&&Monster.num==0)              //胜利界面显示
			
				{background(0.92,0.69,winner);
			reset();}
		
		   
			
		

			Display.update();

			Display.sync(100);

			if (Display.isCloseRequested()) {

				Display.destroy();
				AL.destroy();

				System.exit(0);

			}

		}

	}

	private void initGL(int width, int height) {                          //初始化窗口

		try {

			Display.setDisplayMode(new DisplayMode(width, height));  //设置大小

			Display.create();
			Display.setTitle("Fire");   //设置标题

			Display.setVSyncEnabled(true);

		} catch (LWJGLException e) {

			e.printStackTrace();

			System.exit(0);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glEnable(GL11.GL_BLEND);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);

		GL11.glLoadIdentity();

		GL11.glOrtho(0, width, height, 0, 1, -1);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	public void init() {

		try {
			winner=TextureLoader.getTexture("jpg", ResourceLoader    //初始化图片、音乐
					.getResourceAsStream("winners.jpg"));
            bg2=TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("death.jpg"));
 
			initmusic();
			texture = TextureLoader.getTexture("png", ResourceLoader
					.getResourceAsStream("狐狸.png"));
			texture1 = TextureLoader.getTexture("png", ResourceLoader
					.getResourceAsStream("狐狸_W.png"));
			logo= TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("logo.jpg"));
			pause= TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("pause.jpg"));
			m2 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("shot.wav"));
			m3 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/start.wav"));

			System.out.println("Texture loaded: " + texture);

			System.out.println(">> Image width: " + logo.getImageWidth()
					+ "---");

			System.out.println(">> Image height: " + logo.getImageHeight()
					+ "---");

			System.out.println(">> Texture width: " +logo.getTextureWidth()
					+ "---");

			System.out.println(">> Texture height: "
					+logo.getTextureHeight() + "---");

			System.out.println(">> Texture ID: " + texture.getTextureID());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public void render() {
		                                                          //主角显示、控制
		
			
			if (Keyboard.isKeyDown( Keyboard.KEY_A)) {
				j++;
				if (j == 5) {
					draw(x = x - 4, y, 0.235, 0.625,0.9375,texture, i);
					j = 0;

				} else
					dra(x = x - 4, y, 0.235, 0.625,0.9375, texture, i);

				k = 1;
			}

			if (Keyboard.isKeyDown( Keyboard.KEY_D) ){
				j++;
				if (j == 5) {
					draw(x = x + 4, y, 0.47, 0.625,0.9375, texture, i);
					j = 0;
				} else
					dra(x = x + 4, y, 0.47,0.625,0.9375, texture, i);
				k = 2;
			}
			if (Keyboard.isKeyDown( Keyboard.KEY_W) ){
				j++;
				if (j == 5) {
					draw(x, y = y - 4, 0.73, 0.625,0.9375, texture, i);
					j = 0;
				} else
					dra(x, y = y - 4, 0.73, 0.625,0.9375,texture, i);
				k = 3;
			}
			if (Keyboard.isKeyDown( Keyboard.KEY_S) ){
				j++;
				if (j == 5) {
					draw(x, y = y + 4, 0, 0.625,0.9375, texture, i);
					j = 0;
				} else
					dra(x, y = y + 4, 0, 0.625,0.9375,texture, i);
				k = 0;
			}
			

		

			if (k == 0)
				dra(x, y, 0, 0.625,0.9375, texture, i);
			if (k == 1)
				dra(x, y, 0.235, 0.625,0.9375,texture, i);
			if (k == 2)
				dra(x, y, 0.47, 0.625,0.9375,texture, i);
			if (k == 3)
				dra(x, y, 0.73, 0.625,0.9375,texture, i);
		

	}
	

	void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {                       //游戏战斗过程中的键盘侦听
				if (Keyboard.getEventKey() == Keyboard.KEY_Q)
					m1.playAsMusic(1.0f, 1.0f, false);
				if (Keyboard.getEventKey() == Keyboard.KEY_P)
					wait=0;
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE)
					{m2.playAsSoundEffect(1.0f, 1.0f, false);
					
					
               if(Zidan.h==0) {bt[Zidan.dd]=new Zidan();Zidan.dd++;}
               if(Zidan.h==1) {bt[Zidan.ddd]=new Zidan();Zidan.ddd++;}  //新建子弹
                }
			}
		}
	

		SoundStore.get().poll(0);

	}
void fire(){                                     //发射子弹方法
	

		for (int i = 0 ; i <Zidan.dd ; i++ ) {        
			if (bt[i].kk == 2)
				bullet(bt[i].x= bt[i].x + 5, bt[i].y, bt[i].zd);    //根据子弹方向的不同画子弹
			if (bt[i].kk == 1)
				bullet(bt[i].x =bt[i].x - 5,  bt[i].y,bt[i].zd);
			if (bt[i].kk == 3)
				bullet(bt[i].x, bt[i].y =  bt[i].y - 5, bt[i].zd);
			if (bt[i].kk == 0)
				bullet(bt[i].x, bt[i].y =  bt[i].y+ 5, bt[i].zd);
			if(Zidan.h==0)if(Zidan.dd==100)Zidan.h=1;
			if(Zidan.h==1)if(bt[99].x>800||bt[99].x<0||bt[99].y<0||bt[99].y>600)
			{Zidan.dd=Zidan.ddd;
			Zidan.ddd=0;
			Zidan.h=0;}
		}
		
		
	
	
}
	public void draw(double x, double y, double z, double c,double cc, Texture img, double[] i) {
		Color.white.bind();                                        //画图方法，用于画人物和怪兽
		img.bind();
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d((i[0] / 4 - 0.25) * c, z * cc);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(i[0] / 4 * c, z * cc);
		GL11.glVertex2d(x + img.getImageWidth() / 4, y);
		GL11.glTexCoord2d(i[0] / 4 * c, (z + 0.25) * cc);
		GL11.glVertex2d(x + img.getImageWidth() / 4, y
				+ img.getImageHeight() / 4);
		GL11.glTexCoord2d((i[0] / 4 - 0.25) * c, (z + 0.25) * cc);
		GL11.glVertex2d(x, y +img.getImageHeight() / 4);

		i[0]++;

		if (i[0] == 5)
			i[0] = 1;
		GL11.glEnd();
	}

	public void dra(double x, double y, double z, double c,double cc,Texture img, double[] i) {
		Color.white.bind();
		img.bind();                                           //画图方法，用于画人物和怪兽
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d((i[0] / 4 - 0.25) * c, z * cc);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(i[0] / 4 * c, z * cc);
		GL11.glVertex2d(x + img.getImageWidth() / 4, y);
		GL11.glTexCoord2d(i[0] / 4 * c, (z + 0.25) * cc);
		GL11.glVertex2d(x + img.getImageWidth() / 4, y
				+ img.getImageHeight() / 4);
		GL11.glTexCoord2d((i[0] / 4 - 0.25) * c, (z + 0.25) * cc);
		GL11.glVertex2d(x, y + img.getImageHeight() / 4);
		GL11.glEnd();
	}

	void background(double c,double cc,Texture img) {  //设背景图片
		Color.white.bind();
		img.bind();
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2f(0, 0);
		GL11.glTexCoord2d(c, 0);
		GL11.glVertex2f(800, 0);
		GL11.glTexCoord2d(c, cc);
		GL11.glVertex2f(800, 600);
		GL11.glTexCoord2d(0, cc);
		GL11.glVertex2f(0, 600);
		GL11.glEnd();
	}

	public void monster() {                        //怪兽行走控制
		for(int i=0;i<Monster.num;i++)
		{ if (ms[i].n < 0)
				ms[i].k = 1;
			if (ms[i].n > 800-ms[i].boss.getImageWidth()/4.0)
				ms[i].k = 0;
			if(ms[i].m<0)
				ms[i].k=2;
			if(ms[i].m>600-ms[i].boss.getImageHeight()/4.0)
				ms[i].k=3;
			
			if (ms[i].k == 0) {
				ms[i].j++;
				if (ms[i].j == 5) {
					draw(ms[i].n = ms[i].n - 3, ms[i].m, 0.25, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
					ms[i].j = 0;

				} else
					dra(ms[i].n = ms[i].n - 3, ms[i].m, 0.25, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
			}
			if (ms[i].k == 1) {
				ms[i].j++;
				if (ms[i].j == 5) {
					draw(ms[i].n = ms[i].n +3, ms[i].m, 0.5, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
					ms[i].j = 0;

				} else
					dra(ms[i].n = ms[i].n +3, ms[i].m, 0.5, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
			}
			if (ms[i].k == 2) {
				ms[i].j++;
				if (ms[i].j == 5) {
					draw(ms[i].n, ms[i].m=ms[i].m+3, 0, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
					ms[i].j = 0;

				} else
					dra(ms[i].n, ms[i].m=ms[i].m+3, 0, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
			}
			if (ms[i].k == 3) {
				ms[i].j++;
				if (ms[i].j == 5) {
					draw(ms[i].n, ms[i].m=ms[i].m-3, 0.75, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
					ms[i].j = 0;

				} else
					dra(ms[i].n, ms[i].m=ms[i].m-3, 0.75, ms[i].c,ms[i].cc, ms[i].boss, ms[i].i);
			}
			
			}

	}

	void bullet(double x, double y, Texture img) {       //子弹专门的画图方法
		Color.white.bind();
		img.bind();
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(x + img.getImageWidth(), y);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(x + img.getImageWidth(), y + img.getImageHeight());
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(x, y + img.getImageHeight());
		GL11.glEnd();

	}
	
	void hit(){                                    //碰撞检测
		double a,b,c,d;
		for(int i=0;i<Monster.num;i++){
		a=x+texture.getImageWidth()/8.0-ms[i].n-ms[i].boss.getImageWidth()/8.0;
		b=y+texture.getImageHeight()/8.0-ms[i].m-ms[i].boss.getImageHeight()/8.0;
		c=texture.getImageWidth()/8.0+ms[i].boss.getImageWidth()/8.0-50;
		d=texture.getImageHeight()/8.0+ms[i].boss.getImageHeight()/8.0-50;
		if(a<c&&a>-c&&b<d&&b>-d){                      //判断人物和怪兽是否相撞
			rol=0;ok=0;
			try {
				m1 = AudioLoader.getAudio("WAV", ResourceLoader
						.getResourceAsStream("sounds/if.wav"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;}}
		for(int i=0;i<Zidan.dd;i++)
		{   for(int j=0;j<Monster.num;j++)          //判断怪物和子弹是否相遇
			if(bt[i].x>ms[j].n&&bt[i].x<ms[j].n+ms[j].boss.getImageWidth()/4.0&&bt[i].y>ms[j].m&&bt[i].y<ms[j].m+ms[j].boss.getImageHeight()/4.0)
				{Monster.num--;
				for(int n=j;n<Monster.num;n++)          
					ms[n]=ms[n+1];                      //如果相遇怪物数量减1
				Zidan.dd--;
				for(int z=i;z<Zidan.dd;z++)
					bt[z]=bt[z+1];}                   //如果相遇子弹数量减1
				
			
		}
		if(Monster.num==0&&win<5)
		{	if(win==0)
			{hour1=date.getHours();                   //记录开始游戏时的时间
		min1=date.getMinutes();
		sec1=date.getSeconds();
	}
			for(int i=0;i<limt;i++)
				ms[i]=new Monster();              //生成怪兽
		      win++;
		       limt++;
		       initbg();                         //加载背景图片
		       Zidan.h=0;                       //子弹相关变量清零，使得每局开始时前一局快结束时发的子弹都会从屏幕上消失
				Zidan.dd=0;
				Zidan.ddd=0;
				
		   
		       }
		if(Monster.num==0&&win==5)
		   {ok2=0;
		   

		   try {
			m3 = AudioLoader.getAudio("WAV", ResourceLoader
						.getResourceAsStream("sounds/win.wav"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hour2=date.getHours();
		min2=date.getMinutes();
		sec2=date.getSeconds();                                     //游戏胜利后记录当前时间
		
		str=date.toString();
		result=(hour2-hour1)*3600+(min2-min1)*60+sec2-sec1;           //计算游戏经过的时间
		
		write();
		read();
		writing();
		   }
			
		
		
	
	}
	
	void reset(){                                             //重置相关变量，用于重新开始游戏
		while(Keyboard.next())
			if(Keyboard.getEventKeyState())
				if(Keyboard.getEventKey()==Keyboard.KEY_ESCAPE)
					{rol=1;
					x=700;
					y=515;
					win=0;
					limt=3;
					Monster.num=0;
					Zidan.h=0;
					Zidan.dd=0;
					Zidan.ddd=0;
					ok=0;
				   
					initmusic();}
	}
	void begin(){                                                   //接收回车键，用于开始游戏
		while(Keyboard.next())
			if(Keyboard.getEventKeyState())
				if(Keyboard.getEventKey()==Keyboard.KEY_RETURN )
					start=1;
					
		
	}
	void waiting(){                                            //接收P按键，用于暂停游戏
		while(Keyboard.next())
		if(Keyboard.getEventKeyState())
			if(Keyboard.getEventKey()==Keyboard.KEY_P )
				wait=1;
		
	}
	void initbg(){                                                  //载入场景图片，随机载入
		try {
			int i=(int) (Math.random()*22);
			if(i==0)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/xy0000.jpg"));
			if(i==1)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/xy0001.jpg"));
			if(i==2)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/xy0004.jpg"));
			if(i==3)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/xy0006.jpg"));
			if(i==4)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/xy0007.jpg"));
			if(i==5)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/xy0008.jpg"));
			if(i==6)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/傲来国战斗背景.jpg"));
			if(i==7)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/北惧.jpg"));
			if(i==8)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/长安西.jpg"));
			if(i==9)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/长寿村外战斗背景.jpg"));
			if(i==10)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/大唐边境.jpg"));
			if(i==11)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/大雁塔.jpg"));
			if(i==12)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/地府.jpg"));
			if(i==13)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/凤2.jpg"));
			if(i==14)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/凤5.jpg"));
			if(i==15)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/凤6.jpg"));
			if(i==16)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/海底.jpg"));
			if(i==17)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/蟠桃园.jpg"));
			if(i==18)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/狮驼岭.jpg"));
			if(i==19)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/五指山.jpg"));
			if(i==20)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/瑶池1.jpg"));
			if(i==21)
			bg = TextureLoader.getTexture("jpg", ResourceLoader
					.getResourceAsStream("bg/瑶池.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void initmusic(){                                      //载入音乐，随机载入
		int i=(int) (Math.random()*10);
		try {
			if(i==0)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1017.wav"));
			if(i==1)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1018.wav"));
			if(i==2)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1021.wav"));
			if(i==3)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1022.wav"));
			if(i==4)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1024.wav"));
			if(i==5)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1025.wav"));
			if(i==6)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1026.wav"));
			if(i==7)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1027.wav"));
			if(i==8)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/1042.wav"));
			if(i==9)
			m1 = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("sounds/dntg_zyc.wav"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	void write(){                                               //写入成绩
		File f=new File("grade.txt");
		try {
			FileWriter out=new FileWriter(f,true);
			BufferedWriter ot=new BufferedWriter(out);
			
			ot.write(result+"#"+str);
			ot.newLine();
			ot.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 String[] read(){                                              //读取刚才的成绩
		 String s[]=new String [100];int i=0;
		 String ss[] = null;
		File f=new File("grade.txt");
	
			
			try {FileReader in=new FileReader(f);
			BufferedReader inn=new BufferedReader(in);
				while((s[i]=inn.readLine())!=null)
				{   
					
					i++;
				}
				ss=new String[i];
				for(int n=0;n<i;n++)
				ss[n]=s[n];
				in.close();
				inn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			  return ss;
	}
	 void writing(){                                         //排序后再次写入成绩
		 File f=new File("grade.txt");
		 String b[]=read();
		 String num[]=new String[b.length];
		 String or[]=new String[b.length];
		String c[];String temp;
		for(int i=0;i<b.length;i++)
		{
			c=b[i].split("#");
			num[i]=c[0];
			or[i]=c[0];
		}
		for(int i=0;i<num.length;i++)
			for(int j=i+1;j<num.length;j++)
			if(Integer.parseInt(num[i])>Integer.parseInt(num[j]))
			{	temp=num[i];
			     num[i]=num[j];
			     num[j]=temp;}
				
			
		try {
			 FileWriter out = new FileWriter(f);
			BufferedWriter ot=new BufferedWriter(out);
			for(int i=0;i<num.length;i++)
				for(int j=0;j<or.length;j++)
					if(num[i].equals(or[j]))
						{
						ot.write(b[j]);
						ot.newLine();}
			ot.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 
	 }

	public static void main(String[] argv) {
		Dis textureExample = new Dis();

		textureExample.start();

	}

	@Override
	public void run() {                                      
		if (Thread.currentThread() == one) {                       //时间线程
			while(true){
				date=new Date();
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			}

	}
}