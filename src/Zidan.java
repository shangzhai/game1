import java.io.IOException;


import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Zidan {
	double x;
	double y;
	Texture zd;
	int kk;                             //�ӵ�����
	static int dd = 0,ddd=0,h=0;                   //dd�ӵ���ǰ������ddd�ǹ���ʱ�ڵ��ӵ�����,h�������׶κ͹��ɽ׶εĿ���
	
	Zidan(){
		

		
			
	try {
		zd = TextureLoader.getTexture("png", ResourceLoader
				.getResourceAsStream("zd.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	if (Dis.k == 2) {                                          //�������﷽��Ĳ�ͬ�ӵ��ĳ�ʼλ�ò�ͬ
		x= Dis.x + Dis.texture.getImageWidth() / 4;
		y= (Dis.y + Dis.y + Dis.texture.getImageHeight() / 4) * 0.5;
		kk= Dis.k;
	}
	if (Dis.k == 1) {
		x= Dis.x;
		y= (Dis.y + Dis.y +Dis. texture.getImageHeight() / 4) * 0.5;
		kk = Dis.k;
	}
	if (Dis.k == 3) {
		x = (Dis.x + Dis.x + Dis.texture.getImageWidth() / 4) * 0.5;
		y = Dis.y;
		kk = Dis.k;
	}
	if (Dis.k == 0) {
		x = (Dis.x + Dis.x + Dis.texture.getImageWidth() / 4) * 0.5;
		y = Dis.y + Dis.texture.getImageHeight() / 4;
		kk = Dis.k;
	}


		
}
}
